package com.example.relaxapp.views.login.views

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
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
import androidx.compose.ui.text.input.VisualTransformation
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.relaxapp.R
import com.example.relaxapp.bottomnavigationbar.Routes
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import com.example.relaxapp.views.login.viewmodels.LogInViewModel
import com.example.relaxapp.views.login.viewmodels.LoginViewModelFactory


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

@Composable
fun loadingOverlay(isLoading: Boolean, content: @Composable () -> Unit) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.loading))
    val progress by animateLottieCompositionAsState(composition, isPlaying = true)


    Box(modifier = Modifier.fillMaxSize()) {
        content()
        if (isLoading) {
            LottieAnimation(
                composition = composition,
                progress = progress,
                modifier = Modifier.size(100.dp)
            )
        }
    }

}

@Composable
fun LogInView(viewModel: LogInViewModel, navController: NavController) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var checked by remember { mutableStateOf(false) }
    var passwordVisible by remember { mutableStateOf(false) }
    val loginViewModel: LogInViewModel = viewModel(factory = LoginViewModelFactory(context = LocalContext.current))
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current
    var backButtonEnabled by remember { mutableStateOf(false) }

    BackHandler(enabled = !backButtonEnabled) {}



    if (loginViewModel.state != 0) {
        if (loginViewModel.loginResponse.isSuccess) {
            navController.navigate("MainMenuView")
            loginViewModel.state = 0
        } else {
            Toast.makeText(context, "Contraseña o Usuario Incorrecto", Toast.LENGTH_SHORT).show()
            loginViewModel.state = 0
        }
    }



    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
        detectTapGestures(onTap = {
            focusManager.clearFocus()
        })
    })
     {
        Spacer(modifier = Modifier.height(16.dp))
        Image(
            painter = painterResource(id = R.drawable.relaxlogo),
            contentDescription = stringResource(id = R.string.app_name),
            modifier = Modifier.size(100.dp)
        )


        Text(
            text = stringResource(id = R.string.log_in_title),
            style = MaterialTheme.typography.headlineLarge, color = Color(26, 204, 181, 255)
        )

        Spacer(modifier = Modifier.height(8.dp))

         TextField(
             value = username,
             onValueChange = { username = it },
             modifier = Modifier
                 .padding(start = 16.dp, end = 16.dp)
                 .fillMaxWidth()
                 .background(Color.White, shape = MaterialTheme.shapes.small),
             label = {
                 Text(
                     text = stringResource(id = R.string.email),
                     style = MaterialTheme.typography.headlineSmall
                 )
             },
             keyboardOptions = KeyboardOptions(
                 keyboardType = KeyboardType.Text,
                 imeAction = ImeAction.Done
             ),
             maxLines = 1,
             singleLine = true,
             keyboardActions = KeyboardActions(
                 onDone = {
                     keyboardController?.hide()
                 }
             ),
             colors = TextFieldDefaults.colors(
                 unfocusedContainerColor = Color.Transparent,
                 focusedContainerColor = Color.Transparent,
             )
         )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = password,
            onValueChange = { password = it },
            label = {
                Text(
                    text = stringResource(id = R.string.password),
                    style = MaterialTheme.typography.headlineSmall
                )
            },
            trailingIcon = {
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    if (passwordVisible) {
                        Icon(imageVector = Icons.Filled.VisibilityOff, contentDescription = null)
                    } else {
                        Icon(imageVector = Icons.Filled.Visibility, contentDescription = null)
                    }
                }
            },
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    keyboardController?.hide()
                }
            ),
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
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
          /*  Text(
                stringResource(id = R.string.remember_me),
                style = MaterialTheme.typography.labelSmall,
                color = BlueGray,
                fontSize = 16.sp,
                modifier = Modifier.padding(start = 16.dp).align(Alignment.CenterVertically)
            )
            Switch(
                checked = checked,
                onCheckedChange = {
                    checked = it
                },
                modifier = Modifier.align(Alignment.CenterVertically)
            ) */
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                loginViewModel.doLogin(username, password)
            },
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp)
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(26, 204, 181, 255)
            )
        ) {
            Text(
                text = stringResource(id = R.string.log_in_button),
                style = MaterialTheme.typography.headlineMedium,
                color = Color.Black
            )
        }


        Spacer(modifier = Modifier.height(8.dp))

        Column {
            Button(
                onClick = { navController.navigate(Routes.SignUpView) },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
            ) {
                Text(
                    stringResource(id = R.string.sign_in_dont_have_account),
                    color = Color.Black
                )

            }
        }
    }
    //loadingOverlay(isLoading = loginViewModel.isLoading) {
       // LogInView(LogInViewModel(userRepository = UserRepository), navController)
    //
//
// }
}






