package com.killjoy.stuntion.features.presentation.screen.onboard

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.killjoy.stuntion.features.presentation.utils.OnBoardingPage
import com.killjoy.stuntion.features.presentation.utils.Screen
import com.killjoy.stuntion.features.presentation.utils.components.StuntionButton
import com.killjoy.stuntion.features.presentation.utils.components.StuntionText
import com.killjoy.stuntion.ui.theme.PrimaryBlue
import com.killjoy.stuntion.ui.theme.Type
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnboardScreen(navController: NavController) {
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val pages = listOf(
        OnBoardingPage.First,
        OnBoardingPage.Second,
        OnBoardingPage.Third,
        OnBoardingPage.Fourth,
    )
    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()
    val viewModel = hiltViewModel<OnboardViewModel>()

    Box(modifier = Modifier.fillMaxSize()) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = screenHeight / 9, end = 16.dp)
        ) {
            HorizontalPager(
                count = pages.size,
                state = pagerState,
                verticalAlignment = Alignment.Top
            ) { position ->
                PagerScreen(onBoardingPage = pages[position])
            }
        }

        // Indicator
        HorizontalPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = screenHeight / 5)
        )


        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(start = 32.dp, end = 32.dp, bottom = screenHeight / 10)
        ) {

            // Skip
            StuntionText(
                text = "Skip",
                color = PrimaryBlue,
                textStyle = Type.labelLarge(),
                modifier = Modifier.clickable {
                    navController.navigate(Screen.SignupScreen.route)
                }
            )


            // Next Button
            StuntionButton(
                onClick = {
                    if (pagerState.currentPage != 3)
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                        }
                    else {
                        coroutineScope.launch {
                            viewModel.saveHaveRunAppBefore()
                        }
                        navController.navigate(Screen.SignupScreen.route)
                    }
                },
                modifier = Modifier.width(96.dp)
            ) {
                StuntionText(
                    text = if (pagerState.currentPage != 3) "Next"
                    else "Finish",
                    color = Color.White,
                    textStyle = Type.labelLarge()
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OnboardPreview() {
    OnboardScreen(navController = rememberNavController())
}
