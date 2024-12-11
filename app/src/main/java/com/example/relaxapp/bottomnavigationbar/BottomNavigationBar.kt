package com.example.relaxapp.bottomnavigationbar


import androidx.compose.foundation.Image
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.relaxapp.TokenManager

@Composable
fun BottomNavigationBar(navController: NavController) {
    BottomAppBar(containerColor = Color(144, 181, 179)) {
        val backStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = backStackEntry?.destination?.route
        val tokenManager = TokenManager(LocalContext.current)

        NavBarItems.forEach { navItem ->
            NavigationBarItem(
                selected = currentRoute == navItem.route,
                onClick = {
                    if (navItem.route == "calendarDataView/{userId}") {
                        val userId = tokenManager.getUserId()
                        navController.navigate("calendarDataView/$userId")
                    } else {
                        navController.navigate(navItem.route) {
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
                icon = {
                    when (val image = navItem.image) {
                        is ImageVector -> {
                            Icon(
                                imageVector = image,
                                contentDescription = "",
                                tint = Color.White
                            )
                        }
                        is Int -> {
                            Image(
                                painter = painterResource(id = image),
                                contentDescription = ""
                            )
                        }
                    }
                },
                label = {
                    Text(
                        text = stringResource(navItem.title),
                        color = Color.White,
                        fontSize = 10.sp
                    )
                }
            )
        }
    }
}