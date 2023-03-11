package com.killjoy.stuntion.features.presentation.screen.child_notes.notes

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
import com.killjoy.stuntion.features.presentation.utils.components.StuntionTopBar

@Composable
fun ChildNotesScreen(navController: NavController) {
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
                title = "Child Notes",
                onBackPressed = { navController.popBackStack() },
                isWithDivider = true
            )
            Spacer(modifier = Modifier.height(8.dp))
        }

        items(4) {
            ChildNotesItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 6.dp)
            )
        }
    }
}