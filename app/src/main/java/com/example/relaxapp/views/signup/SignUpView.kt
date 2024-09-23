package com.example.relaxapp.views.signup

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
import androidx.compose.material3.Checkbox
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

//colores
    val MintGreen = Color(185, 218, 212) // #B9DAD4
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

// las tipograifas
val CustomTypography = Typography(

// titulo
    headlineLarge = TextStyle(
        fontFamily = Nunito,
        fontWeight = FontWeight.Bold,
        fontSize = 30.sp,
        color = CarolinaBlue
    ),
    // boton
    headlineMedium = TextStyle(
        fontFamily = Nunito,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        color = Color.White
    ),
    // cuerpo
    headlineSmall = TextStyle(
        fontFamily = Oxygen,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        color = CambridgeBlue
    ),
    // caption
    labelSmall = TextStyle(
        fontFamily = Oxygen,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        color = AshGray
    )
)

@Composable
fun SignUpView(signUpViewModel: SignUpViewModel) {
    val email by signUpViewModel.email.observeAsState("")
    val telephone by signUpViewModel.telephone.observeAsState("")
    val username by signUpViewModel.username.observeAsState("")
    val password by signUpViewModel.password.observeAsState("")
    val passwordConf by signUpViewModel.passwordConf.observeAsState("")
    val termsAccepted by signUpViewModel.termsAccepted.observeAsState(false)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(MintGreen),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        // logo
        Image(
            painter = painterResource(id = R.drawable.ic_logo),
            contentDescription = stringResource(id = R.string.app_name),
            modifier = Modifier.size(100.dp)
        )

        // título
        Text(
            text = stringResource(id = R.string.sign_up_title),
            style = MaterialTheme.typography.headlineLarge
        )

        Spacer(modifier = Modifier.height(16.dp))

        // ccorreo
        TextField(
            value = email,
            onValueChange = { signUpViewModel.onEmailChange(it) },
            label = { Text(text = stringResource(id = R.string.email), style = MaterialTheme.typography.headlineSmall) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, shape = MaterialTheme.shapes.small)
        )

        Spacer(modifier = Modifier.height(8.dp))

        // num telefono
        TextField(
            value = telephone,
            onValueChange = { signUpViewModel.onTelephoneChange(it) },
            label = { Text(text = stringResource(id = R.string.phone_number), style = MaterialTheme.typography.headlineSmall) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, shape = MaterialTheme.shapes.small)
        )

        Spacer(modifier = Modifier.height(8.dp))

        // usuario
        TextField(
            value = username,
            onValueChange = { signUpViewModel.onUsernameChange(it) },
            label = { Text(text = stringResource(id = R.string.username), style = MaterialTheme.typography.headlineSmall) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, shape = MaterialTheme.shapes.small)
        )

        Spacer(modifier = Modifier.height(8.dp))

        // password
        TextField(
            value = password,
            onValueChange = { signUpViewModel.onPasswordChange(it) },
            label = { Text(text = stringResource(id = R.string.password), style = MaterialTheme.typography.headlineSmall) },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, shape = MaterialTheme.shapes.small)
        )

        Spacer(modifier = Modifier.height(8.dp))

        // confirmar passw
        TextField(
            value = passwordConf,
            onValueChange = { signUpViewModel.onPasswordConfChange(it) },
            label = { Text(text = stringResource(id = R.string.sign_up_confirm_password), style = MaterialTheme.typography.headlineSmall) },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, shape = MaterialTheme.shapes.small)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // terminos y condiciones
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(checked = termsAccepted, onCheckedChange = { signUpViewModel.onTermsAcceptedChange(it) })
            Text(text = stringResource(id = R.string.sign_up_accept_terms), style = MaterialTheme.typography.labelSmall)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // boton registro
        Button(
            onClick = { signUpViewModel.onRegisterClicked() },
            modifier = Modifier
                .fillMaxWidth()
                .background(BlueGray)
        ) {
            Text(text = stringResource(id = R.string.sign_up_button), style = MaterialTheme.typography.headlineMedium)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // continuar con google
        Button(
            onClick = { signUpViewModel.onGoogleSignUp() },
            modifier = Modifier
                .fillMaxWidth()
                .background(BlueGray)
        ) {
            Text(text = stringResource(id = R.string.continue_google), style = MaterialTheme.typography.headlineMedium, color = Color.Black)
        }

        Spacer(modifier = Modifier.height(8.dp))

        // continuar con fb
        Button(
            onClick = { signUpViewModel.onFacebookSignUp() },
            modifier = Modifier
                .fillMaxWidth()
                .background(BlueGray)
        ) {
            Text(text = stringResource(id = R.string.continue_facebook), style = MaterialTheme.typography.headlineMedium, color = Color.Black)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // volver sign in
        ClickableText(
            text = AnnotatedString(stringResource(id = R.string.sign_up_already_have_account)),
            onClick = { /* TODO */ },
            modifier = Modifier.align(Alignment.CenterHorizontally),
            style = MaterialTheme.typography.labelSmall
        )
    }
}