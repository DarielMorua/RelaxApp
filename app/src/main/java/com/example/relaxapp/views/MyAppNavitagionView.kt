package com.example.relaxapp.views


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.relaxapp.bottomnavigationbar.Routes
import com.example.relaxapp.views.login.LogInView
import com.example.relaxapp.views.login.LogInViewModel
import com.example.relaxapp.views.mainmenu.MainMenu
import com.example.relaxapp.views.mainmenu.MainMenuViewModel
import com.example.relaxapp.views.onboarding.OnboardingView
import com.example.relaxapp.views.profile.ProfileView
import com.example.relaxapp.views.signup.SignUpView
import com.example.relaxapp.views.signup.SignUpViewModel

@Composable
fun MyAppNavigationView() {
    val navContoller = rememberNavController()
    NavHost(navController = navContoller, startDestination = Routes.OnboardingView, builder =  {

        composable(Routes.OnboardingView) {
            OnboardingView(navContoller)
        }
        composable(Routes.SignUpView) {
            SignUpView(SignUpViewModel(), navContoller)
        }
        composable(Routes.LoginView) {
            LogInView(LogInViewModel(), navContoller)
        }
        composable(Routes.MainMenuView) {
            MainMenu(MainMenuViewModel(), navContoller)
        }
        composable(Routes.ProfileView) {
            ProfileView(navContoller)
        }


    })
}
