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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavigationBar(navController: NavController) {
    BottomAppBar(containerColor =  Color(144, 181, 179) ) {
        val backStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = backStackEntry?.destination?.route

        NavBarItems.forEach { navItem ->
            NavigationBarItem(
                selected = currentRoute == navItem.route,
                onClick = {
                    navController.navigate(navItem.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    // Aquí es donde manejamos tanto ImageVector como Int
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
                    Text(text = stringResource(navItem.title),
                        color = Color.White, fontSize = 10.sp
                    )
                }
            )
        }
    }
}