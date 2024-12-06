package com.example.relaxapp.views.signup

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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
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
import java.text.Normalizer

// Colores
val MintGreen = Color(185, 218, 212) // #B9DAD4
val asd = Color(26, 204, 181, 255) //
val CarolinaBlue = Color(139, 172, 205) // #8BACCD
val CambridgeBlue = Color(144, 181, 179) // #90B5B3
val AshGray = Color(177, 188, 177) // #B1BCB1
val BlueGray = Color(113, 156, 195) // #719CC3

// Fuentes
val Nunito = FontFamily(
    Font(R.font.nunito_regular, FontWeight.Normal),
    Font(R.font.nunito_bold, FontWeight.Bold)
)

val Oxygen = FontFamily(
    Font(R.font.oxygen_regular, FontWeight.Normal)
)

// Tipografías personalizadas
val CustomTypography = Typography(
    // Título
    headlineLarge = TextStyle(
        fontFamily = Nunito,
        fontWeight = FontWeight.Bold,
        fontSize = 30.sp,
        color = CarolinaBlue
    ),
    // Botón
    headlineMedium = TextStyle(
        fontFamily = Nunito,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        color = Color.White
    ),
    // Cuerpo
    headlineSmall = TextStyle(
        fontFamily = Oxygen,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        color = CambridgeBlue
    ),
    // Caption
    labelSmall = TextStyle(
        fontFamily = Oxygen,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        color = AshGray
    )
)

@Composable
fun SignUpView(signUpViewModel: SignUpViewModel, navController: NavController) {
    val email by signUpViewModel.email.observeAsState("")
    val telephone by signUpViewModel.telephone.observeAsState("")
    val name by signUpViewModel.name.observeAsState("")
    val lastname by signUpViewModel.lastname.observeAsState("") // Corregí el error aquí, usabas 'name' en lugar de 'lastname'
    val password by signUpViewModel.password.observeAsState("")
    val passwordConf by signUpViewModel.passwordConf.observeAsState("")
    val registerStatus by signUpViewModel.registerStatus.observeAsState("")
    val country by signUpViewModel.country.observeAsState("")

    val context = LocalContext.current

    // Funcion para validar el formulario
    fun isValidForm(): Boolean {
        //  nombre mayor a 1 caracter
        if (name.length <= 1) {
            Toast.makeText(
                context,
                "El nombre debe tener más de 1 carácter.",
                Toast.LENGTH_SHORT
            ).show()
            return false
        }

        // email mayor a 5 caractere
        if (email.length <= 5) {
            Toast.makeText(
                context,
                "El correo electrónico debe tener más de 5 caracteres.",
                Toast.LENGTH_SHORT
            ).show()
            return false
        }

        // teléfono exactamente 10 dígitos
        if (telephone.length != 10 || !telephone.all { it.isDigit() }) {
            Toast.makeText(
                context,
                "El número de teléfono debe tener exactamente 10 dígitos.",
                Toast.LENGTH_SHORT
            ).show()
            return false
        }

        // contraseña mayor a 6 caracteres
        if (password.length <= 6) {
            Toast.makeText(
                context,
                "La contraseña debe tener más de 6 caracteres.",
                Toast.LENGTH_SHORT
            ).show()
            return false
        }

        // confirmacion de contraseña
        if (password != passwordConf) {
            Toast.makeText(
                context,
                "Las contraseñas no coinciden.",
                Toast.LENGTH_SHORT
            ).show()
            return false
        }
        // Funcion para eliminar acentos
        fun String.removeAccents(): String {
            val normalized = Normalizer.normalize(this, Normalizer.Form.NFD)
            return normalized.replace("[\\p{InCombiningDiacriticalMarks}]".toRegex(), "")
        }
        // Validación del país (convertir a mayúsculas y quitar acentos)
        val formattedCountry = country.removeAccents().uppercase()
        signUpViewModel.onCountryChange(formattedCountry)

        return true
    }



    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Logo
        Image(
            painter = painterResource(id = R.drawable.ic_logo),
            contentDescription = stringResource(id = R.string.app_name),
            modifier = Modifier.size(100.dp)
        )

        // Titulo
        Text(
            text = stringResource(id = R.string.sign_up_title),
            style = MaterialTheme.typography.headlineLarge,
            color = CarolinaBlue
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Nombre
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

        // Apellido
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

        // Correo
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

        // pais
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

        // telfono
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

        // Contraseña
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

        // Confirmar contraseña
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

        Spacer(modifier = Modifier.height(16.dp))

        // Boton de registro
        Button(
            onClick = {
                if (isValidForm()) {
                    signUpViewModel.onRegisterClicked()
                    Toast.makeText(
                        context,
                        "Usuario registrado con éxito",
                        Toast.LENGTH_LONG
                    ).show()
                    // Navegar al login
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
