package com.example.relaxapp.views




import ImageViewModel



import android.os.Build


import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.relaxapp.bottomnavigationbar.Routes
import com.example.relaxapp.views.FAQ.FAQView
import com.example.relaxapp.views.aboutus.views.AboutUsView
import com.example.relaxapp.views.calendar.views.CalendarView

import com.example.relaxapp.views.chat.views.ChatView
import com.example.relaxapp.views.doctordetails.views.DoctorDetailView
import com.example.relaxapp.views.doctordetails.viewmodels.DoctorDetailViewModel
import com.example.relaxapp.views.doctorschedule.views.DoctorScheduleView
import com.example.relaxapp.views.exercises.views.ExerciseView
import com.example.relaxapp.views.exercises.models.ExerciseCategoriesRepository
import com.example.relaxapp.views.exercises.views.ExerciseDetailCategoryView
import com.example.relaxapp.views.exercises.models.ExerciseDetailView
import com.example.relaxapp.views.exercises.viewmodel.ExerciseViewModel
import com.example.relaxapp.views.login.views.LogInView
import com.example.relaxapp.views.login.viewmodels.LogInViewModel
import com.example.relaxapp.views.login.models.UserRepository
import com.example.relaxapp.views.mainmenu.models.ExerciseRepository
import com.example.relaxapp.views.mainmenu.views.MainMenu
import com.example.relaxapp.views.mainmenu.viewmodels.MainMenuViewModel
import com.example.relaxapp.views.needhelp.views.HelpView
import com.example.relaxapp.views.notifications.models.NotificationRepository
import com.example.relaxapp.views.notifications.views.NotificationView
import com.example.relaxapp.views.onboarding.views.OnboardingView
import com.example.relaxapp.views.profesionales.models.ProfessionalRepository
import com.example.relaxapp.views.profesionales.views.ProfessionalView
import com.example.relaxapp.views.profesionales.viewmodels.ProfessionalViewModel
import com.example.relaxapp.views.profesionalesfav.FavoriteProfessionalsView
import com.example.relaxapp.views.profile.views.ProfileView
import com.example.relaxapp.views.profile.favorites.views.FavoriteView
import com.example.relaxapp.views.profile.images.ImageSelectionView
import com.example.relaxapp.views.profile.personaldata.PersonalDataView
import com.example.relaxapp.views.signup.views.SignUpView
import com.example.relaxapp.views.signup.viewmodels.SignUpViewModel

@RequiresApi(Build.VERSION_CODES.O)
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
            MainMenu(
                MainMenuViewModel(excerciseRepository = ExerciseRepository, context),
                navController
            )
        }
        composable(
            route = "profileView/{userId}",
            arguments = listOf(navArgument("userId") { type = NavType.StringType })
        ) { backStackEntry ->
            val userId = backStackEntry.arguments?.getString("userId") ?: ""
            ProfileView(navController = navController, userId = userId)
        }
        composable(
            route = "personalDataView/{userId}",
            arguments = listOf(navArgument("userId") { type = NavType.StringType })
        ) { backStackEntry ->
            val userId = backStackEntry.arguments?.getString("userId") ?: ""
            PersonalDataView(navController = navController, userId = userId)
        }
        composable(
            route = "calendarDataView/{userId}",
            arguments = listOf(navArgument("userId") { type = NavType.StringType })
        ) { backStackEntry ->
            val userId = backStackEntry.arguments?.getString("userId") ?: ""
            CalendarView(navController = navController, context = context, userId = userId)
        }
        composable(
            route = "chatView/{chatId}/{userRole}",
            arguments = listOf(
                navArgument("chatId") { type = NavType.StringType },
                navArgument("userRole") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val chatId = backStackEntry.arguments?.getString("chatId") ?: ""
            val userRole = backStackEntry.arguments?.getString("userRole") ?: ""
            ChatView(navController = navController, chatId = chatId, userRole = userRole)
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
        composable(Routes.AboutUsView){
            AboutUsView(navController)
        }
        composable(Routes.ImageSelectionView) {
            ImageSelectionView(
                navController = navController,
                onImageSelected = { imageUrl ->
                    Log.d("ImageSelectionView", "Image selected: $imageUrl")
                },
                ImageViewModel(userRepository = UserRepository, context)
            )
        }


        composable(Routes.ExerciseView) {
            ExerciseView(
                ExerciseViewModel(
                    exerciseCategory = ExerciseCategoriesRepository,
                    context
                ), navController
            )
        }
        composable(Routes.NotificationView) {
            NotificationView(navController, NotificationRepository)
        }
        composable(
            route = "exerciseDetail/{exerciseId}",
            arguments = listOf(navArgument("exerciseId") { type = NavType.StringType })
        ) { backStackEntry ->
            val exerciseId = backStackEntry.arguments?.getString("exerciseId")
            Log.d("ExerciseDetail", "Recibido exerciseId: $exerciseId")

            val mainMenuViewModel: MainMenuViewModel =
                navController.getBackStackEntry("MainMenuView")
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

        composable(
            route = "exerciseDetailCategory/{exerciseId}",
            arguments = listOf(navArgument("exerciseId") { type = NavType.StringType })
        ) { backStackEntry ->
            val exerciseId = backStackEntry.arguments?.getString("exerciseId")
            Log.d("ExerciseDetail", "Recibido exerciseId: $exerciseId")

            val exerciseViewModel: ExerciseViewModel = navController.getBackStackEntry("ExerciseView")
                .let { ViewModelProvider(it)[ExerciseViewModel::class.java] }

            if (exerciseViewModel.categories.isEmpty()) {
                CircularProgressIndicator(modifier = Modifier.fillMaxSize().padding(16.dp))
            } else {
                val selectedExerciseCategory = exerciseViewModel.categories
                    .flatMap { it.exercises }
                    .find { it.id == exerciseId }

                if (selectedExerciseCategory != null) {
                    ExerciseDetailCategoryView(
                        navController = navController,
                        exercise = selectedExerciseCategory
                    )
                } else {
                    Text("Exercise not found", style = MaterialTheme.typography.bodyLarge)
                }
            }



        }
        composable(Routes.ProfessionalView) {
            ProfessionalView(navController, ProfessionalViewModel(professionalRepository= ProfessionalRepository,context))
        }
        composable(Routes.DoctorScheduleView) {
            DoctorScheduleView(navController)
        }

        composable("doctor_detail/{professionalId}") { backStackEntry ->
            val professionalId = backStackEntry.arguments?.getString("professionalId") ?: ""
            val viewModel: DoctorDetailViewModel = viewModel(factory = DoctorDetailViewModel.DoctorDetailViewModelFactory(context))

            DoctorDetailView(
                navController = navController,
                viewModel = viewModel,
                professionalId = professionalId
            )
        }

        composable(
            route = "favorites/{userId}",
            arguments = listOf(navArgument("userId") { type = NavType.StringType })
        ) { backStackEntry ->
            val userId = backStackEntry.arguments?.getString("userId") ?: ""
            if (userId.isNotEmpty()) {
                val viewModel: ProfessionalViewModel = viewModel(
                    factory = ProfessionalViewModel.ProfessionalViewModelFactory(LocalContext.current)
                )
                FavoriteProfessionalsView(
                    navController = navController,
                    viewModel = viewModel,
                    userId = userId
                )
            } else {
                Text("Error: User ID is missing", color = Color.Red)
            }
        }






    }

}


