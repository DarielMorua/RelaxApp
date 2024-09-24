package com.example.relaxapp.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.TextStyle
import com.example.relaxapp.R

val MintGreen = Color(185, 218, 212) // #B9DAD4
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
fun LogInView(viewModel: LogInViewModel) {
    val username by viewModel.username.observeAsState("")
    val password by viewModel.password.observeAsState("")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(MintGreen), // Fondo de la pantalla principal
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Logo
        Image(
            painter = painterResource(id = R.drawable.ic_logo),
            contentDescription = stringResource(id = R.string.app_name),  // Usa el nombre de la app
            modifier = Modifier.size(100.dp)
        )

        // Título
        Text(
            text = stringResource(id = R.string.app_name),
            style = MaterialTheme.typography.headlineLarge
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Usuario
        TextField(
            value = username,
            onValueChange = { viewModel.onUsernameChange(it) },
            label = { Text(text = stringResource(id = R.string.username), style = MaterialTheme.typography.headlineSmall) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, shape = MaterialTheme.shapes.small)
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
                .fillMaxWidth()
                .background(Color.White, shape = MaterialTheme.shapes.small)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Botón Registro
        Button(
            onClick = { viewModel.onRegisterClicked() },
            modifier = Modifier
                .fillMaxWidth()
                .background(BlueGray)
        ) {
            Text(text = stringResource(id = R.string.), style = MaterialTheme.typography.headlineMedium)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Registro con Google
        Button(
            onClick = { viewModel.onGoogleSignUp() },
            modifier = Modifier
                .fillMaxWidth()
                .background(BlueGray)
        ) {
            Text(text = stringResource(id = R.string.continue_google), style = MaterialTheme.typography.headlineMedium, color = Color.Black)
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Registro con Facebook
        Button(
            onClick = { viewModel.onFacebookSignUp() },
            modifier = Modifier
                .fillMaxWidth()
                .background(BlueGray)
        ) {
            Text(text = stringResource(id = R.string.continue_facebook), style = MaterialTheme.typography.headlineMedium, color = Color.Black)
        }
    }
}