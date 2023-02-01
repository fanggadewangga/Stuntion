package com.killjoy.stuntion.features.presentation.screen.consultation

import androidx.compose.animation.Animatable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.killjoy.stuntion.features.presentation.screen.consultation.ask_expert.questions.AskExpertScreen
import com.killjoy.stuntion.features.presentation.utils.components.StuntionTopBar
import com.killjoy.stuntion.ui.stuntionUI.StuntionText
import com.killjoy.stuntion.ui.theme.LightBlue
import com.killjoy.stuntion.ui.theme.PrimaryBlue
import com.killjoy.stuntion.ui.theme.Type
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ConsultationScreen(navController: NavController) {

    val pagerState = com.google.accompanist.pager.rememberPagerState()
    val pages = listOf("Ask Expert", "Chat with Experts")
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {

        // Top Bar
        StuntionTopBar(title = "Consultation")

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
                StuntionText(
                    text = pages[page],
                    modifier = Modifier.padding(50.dp)
                )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ConsultationScreenPreview() {
    ConsultationScreen(navController = rememberNavController())
}