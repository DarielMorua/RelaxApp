package com.example.relaxapp.views.signup.views

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.relaxapp.R
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.TextStyle
import androidx.navigation.NavController
import com.example.relaxapp.bottomnavigationbar.Routes
import com.example.relaxapp.views.signup.viewmodels.SignUpViewModel
import java.text.Normalizer

val MintGreen = Color(185, 218, 212) // #B9DAD4
val asd = Color(26, 204, 181, 255) //
val CarolinaBlue = Color(139, 172, 205) // #8BACCD
val CambridgeBlue = Color(144, 181, 179) // #90B5B3
val AshGray = Color(177, 188, 177) // #B1BCB1
val BlueGray = Color(113, 156, 195) // #719CC3

val Nunito = FontFamily(
    Font(R.font.nunito_regular, FontWeight.Normal),
    Font(R.font.nunito_bold, FontWeight.Bold)
)

val Oxygen = FontFamily(
    Font(R.font.oxygen_regular, FontWeight.Normal)
)

val CustomTypography = Typography(
    headlineLarge = TextStyle(
        fontFamily = Nunito,
        fontWeight = FontWeight.Bold,
        fontSize = 30.sp,
        color = CarolinaBlue
    ),
    headlineMedium = TextStyle(
        fontFamily = Nunito,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        color = Color.White
    ),
    headlineSmall = TextStyle(
        fontFamily = Oxygen,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        color = CambridgeBlue
    ),
    labelSmall = TextStyle(
        fontFamily = Oxygen,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        color = AshGray
    )
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpView(signUpViewModel: SignUpViewModel, navController: NavController) {
    val email by signUpViewModel.email.observeAsState("")
    val telephone by signUpViewModel.telephone.observeAsState("")
    val name by signUpViewModel.name.observeAsState("")
    val lastname by signUpViewModel.lastname.observeAsState("")
    val password by signUpViewModel.password.observeAsState("")
    val passwordConf by signUpViewModel.passwordConf.observeAsState("")
    val country by signUpViewModel.country.observeAsState("")
    var expanded by remember { mutableStateOf(false) }
    val selectedRole by signUpViewModel.rol.observeAsState("Selecciona un rol")
    val roles = listOf("Profesional", "User")
    val rol by signUpViewModel.rol.observeAsState("User")
    val context = LocalContext.current

    fun isValidForm(): Boolean {
        if (name.length <= 1) {
            Toast.makeText(
                context,
                "El nombre debe tener más de 1 carácter.",
                Toast.LENGTH_SHORT
            ).show()
            return false
        }

        if (email.length <= 5) {
            Toast.makeText(
                context,
                "El correo electrónico debe tener más de 5 caracteres.",
                Toast.LENGTH_SHORT
            ).show()
            return false
        }

        if (telephone.length != 10 || !telephone.all { it.isDigit() }) {
            Toast.makeText(
                context,
                "El número de teléfono debe tener exactamente 10 dígitos.",
                Toast.LENGTH_SHORT
            ).show()
            return false
        }

        if (password.length <= 6) {
            Toast.makeText(
                context,
                "La contraseña debe tener más de 6 caracteres.",
                Toast.LENGTH_SHORT
            ).show()
            return false
        }

        if (password != passwordConf) {
            Toast.makeText(
                context,
                "Las contraseñas no coinciden.",
                Toast.LENGTH_SHORT
            ).show()
            return false
        }
        fun String.removeAccents(): String {
            val normalized = Normalizer.normalize(this, Normalizer.Form.NFD)
            return normalized.replace("[\\p{InCombiningDiacriticalMarks}]".toRegex(), "")
        }
        val formattedCountry = country.removeAccents().uppercase()
        signUpViewModel.onCountryChange(formattedCountry)

        if (rol.isEmpty()) {
            Toast.makeText(context, "Por favor selecciona un rol.", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }



    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_logo),
            contentDescription = stringResource(id = R.string.app_name),
            modifier = Modifier.size(100.dp)
        )

        Text(
            text = stringResource(id = R.string.sign_up_title),
            style = MaterialTheme.typography.headlineLarge,
            color = CarolinaBlue
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = name,
            onValueChange = { signUpViewModel.onUsernameChange(it) },
            label = {
                Text(
                    text = stringResource(id = R.string.name),
                    style = MaterialTheme.typography.headlineSmall
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, shape = MaterialTheme.shapes.small)
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = lastname,
            onValueChange = { signUpViewModel.onLastnameChange(it) },
            label = {
                Text(
                    text = stringResource(id = R.string.lastname),
                    style = MaterialTheme.typography.headlineSmall
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, shape = MaterialTheme.shapes.small)
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = email,
            onValueChange = { signUpViewModel.onEmailChange(it) },
            label = {
                Text(
                    text = stringResource(id = R.string.email),
                    style = MaterialTheme.typography.headlineSmall
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, shape = MaterialTheme.shapes.small)
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = country,
            onValueChange = { signUpViewModel.onCountryChange(it) },
            label = {
                Text(
                    text = stringResource(id = R.string.country),
                    style = MaterialTheme.typography.headlineSmall
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, shape = MaterialTheme.shapes.small)
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = telephone,
            onValueChange = { signUpViewModel.onTelephoneChange(it) },
            label = {
                Text(
                    text = stringResource(id = R.string.phone_number),
                    style = MaterialTheme.typography.headlineSmall
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, shape = MaterialTheme.shapes.small)
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = password,
            onValueChange = { signUpViewModel.onPasswordChange(it) },
            label = {
                Text(
                    text = stringResource(id = R.string.password),
                    style = MaterialTheme.typography.headlineSmall
                )
            },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, shape = MaterialTheme.shapes.small)
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = passwordConf,
            onValueChange = { signUpViewModel.onPasswordConfChange(it) },
            label = {
                Text(
                    text = stringResource(id = R.string.sign_up_confirm_password),
                    style = MaterialTheme.typography.headlineSmall
                )
            },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, shape = MaterialTheme.shapes.small)
        )
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            TextField(
                value = selectedRole,
                onValueChange = {},
                readOnly = true,
                label = { Text("Rol") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                roles.forEach { role ->
                    DropdownMenuItem(
                        text = { Text(role)},
                        onClick = {
                            signUpViewModel.onRolChange(role)
                            expanded = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (isValidForm()) {
                    signUpViewModel.onRegisterClicked()
                    Toast.makeText(
                        context,
                        "Usuario registrado con éxito",
                        Toast.LENGTH_LONG
                    ).show()
                    navController.navigate(Routes.LoginView)
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = asd)
        ) {
            Text(
                text = stringResource(id = R.string.sign_up_button),
                style = MaterialTheme.typography.headlineMedium
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Text(
                text = stringResource(id = R.string.sign_up_already_have_account),
                style = MaterialTheme.typography.headlineSmall
            )
            ClickableText(
                text = AnnotatedString(" Iniciar sesión"),
                onClick = {
                    navController.navigate(Routes.LoginView)
                },
                style = TextStyle(color = CarolinaBlue)
            )
        }
    }
}
