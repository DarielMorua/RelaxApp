package com.example.relaxapp.bottomnavigationbar

import android.content.Context
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.example.relaxapp.R

val NavBarItems = listOf(
    BarItem(
        title =  R.string.home,
        image = Icons.Filled.Home,
        route = Routes.MainMenuView
    ),
    BarItem(
        title = R.string.drchat,
        image = Icons.Filled.Face,
        route = Routes.ChatView
    ),
    BarItem(
        title = R.string.empty,
        image = R.drawable.ic_logo,
        route = Routes.IAChatView
    ),
    BarItem(
        title = R.string.calendar,
        image = Icons.Filled.DateRange,
        route = Routes.CalendarView
    ),
    BarItem(
        title = R.string.notificaciones,
        image = Icons.Filled.Notifications,
        route = Routes.NotificationsView
    ),

)
