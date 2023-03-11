package com.killjoy.stuntion.features.presentation.screen.activity_list

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.killjoy.stuntion.features.presentation.utils.components.ChatActivityItem
import com.killjoy.stuntion.features.presentation.utils.components.RequestHelpActivityItem
import com.killjoy.stuntion.features.presentation.utils.components.StuntionTopBar

@Composable
fun ActivityListScreen(navController: NavController) {
    val systemUiController = rememberSystemUiController()
    systemUiController.apply {
        setStatusBarColor(color = Color.White, darkIcons = true)
        setNavigationBarColor(color = Color.White, darkIcons = true)
    }

    LazyColumn(
        modifier = Modifier.padding(vertical = (LocalConfiguration.current.screenHeightDp / 17).dp)
    ) {

        item {
            StuntionTopBar(
                title = "Activity List",
                onBackPressed = { navController.popBackStack() },
                isWithDivider = true
            )
            Spacer(modifier = Modifier.height(8.dp))
        }

        items(3) {
            RequestHelpActivityItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 6.dp)
            )
        }

        items(3) {
            ChatActivityItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 4.dp)
            )
        }
    }
}