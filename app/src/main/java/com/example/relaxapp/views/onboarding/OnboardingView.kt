package com.example.relaxapp.views.onboarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.relaxapp.bottomnavigationbar.Routes
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingView(navController: NavController) {
    val pagerState = rememberPagerState (pageCount = { 3 })
    val coroutineScope = rememberCoroutineScope()

    Column (modifier =  Modifier.fillMaxSize()) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.weight(1f)
        ) { page ->
            when (page) {
                0 -> OnboardingScreenView1()
                1 -> OnboardingScreenView2()
                2 -> OnboardingScreenView3()
            }
        }
        Row (modifier = Modifier
            .align(Alignment.CenterHorizontally)
            .padding(16.dp)) {
            Button(onClick = {
                if (pagerState.currentPage > 0) {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(pagerState.currentPage - 1)
                    }
                }
            }, modifier = Modifier.padding(end=8.dp)) {
                Text(text = "Anterior")
            }
            Button(onClick = {
                if (pagerState.currentPage < pagerState.pageCount - 1) {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
                    }
                }
            }) {
                Text(text = "Siguiente")
            }
            if (pagerState.currentPage == pagerState.pageCount - 1) {
            //cambiar
                Button(onClick = { navController.navigate(Routes.LoginView) }) {
                    Text(text = "Empezar")
                }

        }

        }

    }

}