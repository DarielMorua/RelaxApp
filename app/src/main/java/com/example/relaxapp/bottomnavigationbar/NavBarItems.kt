package com.example.relaxapp.bottomnavigationbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import com.example.relaxapp.R


val NavBarItems = listOf(
    BarItem(
        title = "Home",
        image = Icons.Filled.Home,
        route = Routes.MainMenuView
    ),
    BarItem(
        title = "Dr.Chat",
        image = Icons.Filled.Face,
        route = Routes.DoctorChatView
    ),
    BarItem(
        title = "",
        image = R.drawable.ic_logo,
        route = Routes.IAChatView
    ),
    BarItem(
        title = "Calendar",
        image = Icons.Filled.DateRange,
        route = Routes.CalendarView
    ),
    BarItem(
        title = "Notification",
        image = Icons.Filled.Notifications,
        route = Routes.NotificationsView
    ),

)
