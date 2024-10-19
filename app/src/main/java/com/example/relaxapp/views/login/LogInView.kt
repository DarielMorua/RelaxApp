package com.example.relaxapp.views.login

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.TextStyle
import androidx.navigation.NavController
import com.example.relaxapp.R
import com.example.relaxapp.bottomnavigationbar.Routes
import com.example.relaxapp.views.onboarding.OnboardingViewModel

val MintGreen = Color(26, 204, 181, 255) // #B9DAD4
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

// Define the typography
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
fun LogInView(viewModel: LogInViewModel, navController: NavController) {
    val username by viewModel.username.observeAsState("")
    val password by viewModel.password.observeAsState("")
    var checked by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Logo
        Image(
            painter = painterResource(id = R.drawable.relaxlogo),
            contentDescription = stringResource(id = R.string.app_name),  // Usa el nombre de la app
            modifier = Modifier.size(100.dp)
        )

        // Título
        Text(
            text = stringResource(id = R.string.log_in_title),
            style = MaterialTheme.typography.headlineLarge, color = Color(26, 204, 181, 255)
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Usuario
        TextField(
            value = username,
            onValueChange = { viewModel.onUsernameChange(it) },
            label = { Text(text = stringResource(id = R.string.username), style = MaterialTheme.typography.headlineSmall)},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp)
                .fillMaxWidth()
                .background(Color.White, shape = MaterialTheme.shapes.small),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
            )
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Contraseña
        TextField(
            value = password,
            onValueChange = { viewModel.onPasswordChange(it) },
            label = { Text(text = stringResource(id = R.string.password), style = MaterialTheme.typography.headlineSmall) },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp)
                .fillMaxWidth()
                .background(Color.White, shape = MaterialTheme.shapes.small),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween ) {
            Text( stringResource(id = R.string.remember_me), style = MaterialTheme.typography.labelSmall, color = BlueGray, fontSize = 16.sp,
                modifier = Modifier.padding(start = 16.dp).align(Alignment.CenterVertically))
            Switch(
                checked = checked,
                onCheckedChange = {
                    checked = it
                },
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botón Registro
        Button(
            onClick = { //viewModel.onRegisterClicked()
                 navController.navigate(Routes.MainMenuView)},
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp)
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(26, 204, 181, 255))
        ) {
            Text(text = stringResource(id = R.string.log_in_button), style = MaterialTheme.typography.headlineMedium, color = Color.Black)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Registro con Google
        Button(
            onClick = { viewModel.onGoogleSignUp() },
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp)
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White)

        ) {
            Image(
                painter = painterResource(id = R.drawable.googlelogo),
                contentDescription = stringResource(id = R.string.continue_google),
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = stringResource(id = R.string.continue_google), style = MaterialTheme.typography.headlineMedium, color = Color.Black,)
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Registro con Facebook
        Button(
            onClick = { viewModel.onFacebookSignUp() },
            modifier = Modifier
                .padding(start = 8.dp, end = 8.dp)
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White)
        ) {
            Image(
                painter = painterResource(id = R.drawable.facebooklogo),
                contentDescription = stringResource(id = R.string.continue_facebook),
                modifier = Modifier.size(30.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = stringResource(id = R.string.continue_facebook), style = MaterialTheme.typography.headlineMedium, color = Color.Black)
        }
        Column {
            Button(onClick = {navController.navigate(Routes.SignUpView)},
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)) {
                Text( stringResource(id = R.string.sign_in_dont_have_account))
            }
        }
    }

}