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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.relaxapp.R
import com.example.relaxapp.bottomnavigationbar.Routes
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingView(navController: NavController, onboardingViewModel: OnboardingViewModel = viewModel()) {
    val pagerState = rememberPagerState(pageCount = { 3 })
    val coroutineScope = rememberCoroutineScope()
    val isOnboardingCompleted by onboardingViewModel.isOnboardingCompleted.collectAsState()

    LaunchedEffect(isOnboardingCompleted) {
        if (isOnboardingCompleted) {
            navController.navigate(Routes.LoginView)
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
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
        Row(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(16.dp)
        ) {
            Button(onClick = {
                if (pagerState.currentPage > 0) {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(pagerState.currentPage - 1)
                    }
                }
            }, modifier = Modifier.padding(end = 8.dp)) {
                Text(stringResource(id = R.string.backbutton))
            }

            if (pagerState.currentPage < pagerState.pageCount - 1) {
                Button(onClick = {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
                    }
                }) {
                    Text(stringResource(id = R.string.nextbutton))
                }
            }

            if (pagerState.currentPage == pagerState.pageCount - 1) {
                Button(onClick = {
                    onboardingViewModel.completeOnboarding()
                    navController.navigate(Routes.LoginView)
                }) {
                    Text(stringResource(id = R.string.beginbutton))
                }
            }
        }
    }

}