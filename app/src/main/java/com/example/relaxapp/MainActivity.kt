package com.example.relaxapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.relaxapp.views.login.LogInView
import com.example.relaxapp.views.login.LogInViewModel
import com.example.relaxapp.ui.theme.RelaxAppTheme
import com.example.relaxapp.views.profile.ProfileView

//Prueba dev commit
//Prueba dev commit 2
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            RelaxAppTheme {
                //Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LogInView(LogInViewModel())
                    //ProfileView()
                }
            }
        }
    }
//}

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