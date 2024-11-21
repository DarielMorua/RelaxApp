package com.example.relaxapp.views


import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.relaxapp.bottomnavigationbar.Routes
import com.example.relaxapp.views.FAQ.FAQView
import com.example.relaxapp.views.calendar.CalendarView

import com.example.relaxapp.views.chat.ChatView
import com.example.relaxapp.views.doctordetails.DoctorDetailView
import com.example.relaxapp.views.doctorschedule.DoctorScheduleView
import com.example.relaxapp.views.exercises.ExerciseView
import com.example.relaxapp.views.exercises.excerciseDetails.ExerciseDetailView
import com.example.relaxapp.views.login.LogInView
import com.example.relaxapp.views.login.LogInViewModel
import com.example.relaxapp.views.login.UserRepository
import com.example.relaxapp.views.mainmenu.ExerciseRepository
import com.example.relaxapp.views.mainmenu.MainMenu
import com.example.relaxapp.views.mainmenu.MainMenuViewModel
import com.example.relaxapp.views.needhelp.HelpView
import com.example.relaxapp.views.notifications.NotificationView
import com.example.relaxapp.views.onboarding.OnboardingView
import com.example.relaxapp.views.profesionales.ProfessionalView
import com.example.relaxapp.views.profile.ProfileView
import com.example.relaxapp.views.profile.favorites.FavoriteView
import com.example.relaxapp.views.profile.personaldata.PersonalDataView
import com.example.relaxapp.views.signup.SignUpView
import com.example.relaxapp.views.signup.SignUpViewModel

@Composable
fun MyAppNavigationView() {
    val context = LocalContext.current
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.OnboardingView) {

        composable(Routes.OnboardingView) {
            OnboardingView(navController, onboardingViewModel = viewModel())
        }
        composable(Routes.SignUpView) {
            SignUpView(SignUpViewModel(), navController)
        }
        composable(Routes.LoginView) {
            LogInView(LogInViewModel(userRepository = UserRepository, context), navController)
        }
        composable(Routes.MainMenuView) {
            MainMenu(MainMenuViewModel(excerciseRepository = ExerciseRepository, context), navController)
        }
        composable(Routes.ProfileView) {
            ProfileView(navController)
        }
        composable(Routes.PersonalDataView) {
            PersonalDataView(navController)
        }
        composable(Routes.CalendarDataView) {
            CalendarView(navController)
        }
        composable(Routes.ChatView) {
            ChatView(navController)
        }
        composable(Routes.FAQView) {
            FAQView(navController)
        }
        composable(Routes.HelpView) {
            HelpView(navController)
        }
        composable(Routes.FavoriteView) {
            FavoriteView(navController)
        }
        composable(Routes.ExerciseView) {
            ExerciseView(navController)
        }
        composable(Routes.NotificationView) {
            NotificationView(navController)
        }

        // Ruta para ExerciseDetailView con el parámetro exerciseId
        composable(
            route = "exerciseDetail/{exerciseId}",
            arguments = listOf(navArgument("exerciseId") { type = NavType.StringType })
        ) { backStackEntry ->
            val exerciseId = backStackEntry.arguments?.getString("exerciseId")
            Log.d("ExerciseDetail", "Recibido exerciseId: $exerciseId")

            val mainMenuViewModel: MainMenuViewModel = navController.getBackStackEntry("MainMenuView")
                .let { ViewModelProvider(it)[MainMenuViewModel::class.java] }
            if (mainMenuViewModel.exercises.isEmpty()) {
                CircularProgressIndicator(modifier = Modifier.fillMaxSize().padding(16.dp))
            } else {
                val selectedExercise = mainMenuViewModel.exercises.find { it.id == exerciseId }
                if (selectedExercise != null) {
                    ExerciseDetailView(navController = navController, exercise = selectedExercise)
                } else {
                    Text("Exercise not found", style = MaterialTheme.typography.bodyLarge)
                }
            }
        }

      composable(Routes.ProfessionalView){
            ProfessionalView(navController)
        }
        composable(Routes.DoctorScheduleView){
            DoctorScheduleView(navController)
        }
        composable(Routes.DoctorDetailView){
            DoctorDetailView(navController)
        }

    }

}

