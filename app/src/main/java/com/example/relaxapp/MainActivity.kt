package com.example.relaxapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.relaxapp.ui.theme.RelaxAppTheme
import com.example.relaxapp.views.MyAppNavigationView
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.ktx.messaging


//Prueba dev commit
//Prueba dev commit 2
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Firebase.messaging.isAutoInitEnabled = true

        enableEdgeToEdge()
        setContent {

            RelaxAppTheme {

                //    SignUpView(SignUpViewModel())
                val navContoller = rememberNavController()
                MyAppNavigationView()
                    //ProfileView(NavController(context = this))
                    //PersonalDataView(navContoller)
                //MainMenu(viewModel = MainMenuViewModel(), navContoller)
                    //CalendarView(navContoller)
                //ExerciseView(navContoller)
                //ExerciseDetailView(navContoller)
                }
            }
        }
    }
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RelaxAppTheme {
        Greeting("Android")
    }
}