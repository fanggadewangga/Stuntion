package com.killjoy.stuntion.features.presentation.screen.consultation

import android.annotation.SuppressLint
import androidx.compose.animation.Animatable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.killjoy.stuntion.features.presentation.navigation.BottomNavigationBar
import com.killjoy.stuntion.features.presentation.screen.chat_expert.ChatExpertsScreen
import com.killjoy.stuntion.features.presentation.screen.ask_expert.AskExpertScreen
import com.killjoy.stuntion.features.presentation.utils.components.StuntionTopBar
import com.killjoy.stuntion.features.presentation.utils.components.StuntionText
import com.killjoy.stuntion.ui.theme.LightBlue
import com.killjoy.stuntion.ui.theme.PrimaryBlue
import com.killjoy.stuntion.ui.theme.Type
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalPagerApi::class)
@Composable
fun ConsultationScreen(navController: NavController) {

    val pagerState = com.google.accompanist.pager.rememberPagerState()
    val pages = listOf("Public Consultation", "Private Consultation")
    val coroutineScope = rememberCoroutineScope()
    val systemUiController = rememberSystemUiController()
    systemUiController.apply {
        setStatusBarColor(color = Color.White, darkIcons = true)
        setNavigationBarColor(color = Color.White, darkIcons = true)
    }

    Scaffold(
        bottomBar = { BottomNavigationBar(navController = navController) },
        modifier = Modifier.padding(bottom = (LocalConfiguration.current.screenHeightDp / 17).dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(vertical = 24.dp)
        ) {

            // Top Bar
            StuntionTopBar(title = "Consultation", onBackPressed = { navController.popBackStack() }, isWithDivider = false)

            // Tab Row
            TabRow(
                selectedTabIndex = pagerState.currentPage,
                backgroundColor = Color.Transparent,
                indicator = { tabPosition ->
                    TabRowDefaults.Indicator(
                        Modifier
                            .pagerTabIndicatorOffset(pagerState, tabPosition)
                            .width(0.dp)
                            .height(0.dp)
                    )
                },
                modifier = Modifier.background(color = Color.Transparent)
            ) {
                pages.forEachIndexed { index, text ->

                    val isSelected = pagerState.currentPage == index
                    val color = remember {
                        Animatable(PrimaryBlue)
                    }
                    LaunchedEffect(key1 = isSelected) {
                        color.animateTo(if (isSelected) PrimaryBlue else LightBlue)
                    }

                    Tab(
                        selected = isSelected,
                        onClick = {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(index)
                            }
                        },
                        text = {
                            StuntionText(
                                text = text,
                                textStyle = Type.titleSmall(),
                                color = if (isSelected) Color.White else PrimaryBlue
                            )
                        },
                        modifier = Modifier
                            .width(140.dp)
                            .height(75.dp)
                            .padding(vertical = 16.dp, horizontal = 8.dp)
                            .background(color = color.value, shape = RoundedCornerShape(100.dp))
                    )
                }
            }

            HorizontalPager(
                count = pages.size,
                state = pagerState,
            ) { page ->
                if (pages[page] == pages[0])
                    AskExpertScreen(navController = navController)
                else
                    ChatExpertsScreen(navController = navController)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ConsultationScreenPreview() {
    ConsultationScreen(navController = rememberNavController())
}