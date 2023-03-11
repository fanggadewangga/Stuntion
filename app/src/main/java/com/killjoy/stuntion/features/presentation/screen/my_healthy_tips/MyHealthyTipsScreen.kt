package com.killjoy.stuntion.features.presentation.screen.my_healthy_tips

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.killjoy.stuntion.features.presentation.utils.components.ChildNotesItem
import com.killjoy.stuntion.features.presentation.utils.components.MyHealthyTipsItem
import com.killjoy.stuntion.features.presentation.utils.components.StuntionTopBar

@Composable
fun MyHealthyTipsScreen(navController: NavController) {
    val systemUiController = rememberSystemUiController()
    systemUiController.apply {
        setStatusBarColor(color = Color.White, darkIcons = true)
        setNavigationBarColor(color = Color.White, darkIcons = true)
    }

    LazyColumn(
        Modifier
            .fillMaxSize()
            .padding(vertical = (LocalConfiguration.current.screenHeightDp / 17).dp)
    ) {

        item {
            StuntionTopBar(
                title = "My Healthy Tips",
                onBackPressed = { navController.popBackStack() },
                isWithDivider = true
            )
            Spacer(modifier = Modifier.height(8.dp))
        }

        items(4) {
            MyHealthyTipsItem(
                title = "If the child can walk, train and accompany the child when climbing ...",
                completedAction = 4,
                totalAction = 7,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp)
            )
        }
    }
}