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
import androidx.compose.material3.ButtonDefaults
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
import androidx.navigation.NavController
import com.example.relaxapp.bottomnavigationbar.Routes
import com.example.relaxapp.views.login.LogInView

//colores
    val MintGreen = Color(185, 218, 212) // #B9DAD4
    val asd = Color(26, 204, 181,255) //
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
fun SignUpView(signUpViewModel: SignUpViewModel, navController: NavController) {
    val email by signUpViewModel.email.observeAsState("")
    val telephone by signUpViewModel.telephone.observeAsState("")
    val name by signUpViewModel.name.observeAsState("")
    val lastname by signUpViewModel.name.observeAsState("")
    val password by signUpViewModel.password.observeAsState("")
    val passwordConf by signUpViewModel.passwordConf.observeAsState("")
    val termsAccepted by signUpViewModel.termsAccepted.observeAsState(false)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
,        horizontalAlignment = Alignment.CenterHorizontally,
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
            style = MaterialTheme.typography.headlineLarge,
            color=Color(26, 204, 181,255)
        )

        Spacer(modifier = Modifier.height(16.dp))
        // nombre
        TextField(
            value = name,
            onValueChange = { signUpViewModel.onUsernameChange(it) },
            label = { Text(text = stringResource(id = R.string.name) , style = MaterialTheme.typography.headlineSmall) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, shape = MaterialTheme.shapes.small)
        )

        Spacer(modifier = Modifier.height(8.dp))

        // apellido
        TextField(
            value = lastname,
            onValueChange = { signUpViewModel.onLastnameChange(it) },
            label = { Text(text = stringResource(id = R.string.lastname) , style = MaterialTheme.typography.headlineSmall) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, shape = MaterialTheme.shapes.small)
        )
        Spacer(modifier = Modifier.height(8.dp))

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

        // ccorreo
        TextField(
            value = email,
            onValueChange = {  },
            label = { Text(text = stringResource(id = R.string.country), style = MaterialTheme.typography.headlineSmall) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
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


        // boton registro
        Button(
            onClick = { //signUpViewModel.onRegisterClicked()
                navController.navigate(Routes.MainMenuView) },
            modifier = Modifier
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(26, 204, 181,255))
        ) {
            Text(text = stringResource(id = R.string.sign_up_button), style = MaterialTheme.typography.headlineMedium)

        }

        Spacer(modifier = Modifier.height(16.dp))

        // continuar con google
        Button(
            onClick = { signUpViewModel.onGoogleSignUp() },
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(text = stringResource(id = R.string.continue_google), style = MaterialTheme.typography.headlineMedium, color = Color.Black)
        }

        Spacer(modifier = Modifier.height(8.dp))



        // volver sign in
        ClickableText(
            text = AnnotatedString(stringResource(id = R.string.sign_up_already_have_account)),
            onClick = { navController.navigate(Routes.LoginView) },
            modifier = Modifier.align(Alignment.CenterHorizontally),
            style = MaterialTheme.typography.labelSmall
        )
    }
}
