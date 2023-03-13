package com.killjoy.stuntion.features.presentation.screen.notification

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.killjoy.stuntion.features.presentation.utils.components.NotificationItem
import com.killjoy.stuntion.features.presentation.utils.components.StuntionTopBar
import com.killjoy.stuntion.ui.stuntionUI.StuntionText
import com.killjoy.stuntion.ui.theme.PrimaryBlue
import com.killjoy.stuntion.ui.theme.Type

@Composable
fun NotificationScreen(navController: NavController) {

    val systemUiController = rememberSystemUiController()
    LaunchedEffect(key1 = true) {
        systemUiController.apply {
            setStatusBarColor(color = Color.White, darkIcons = true)
            setNavigationBarColor(color = Color.White, darkIcons = true)
        }
    }

    Column(
        Modifier
            .fillMaxSize()
            .padding(top = 24.dp)
    ) {
        // Top bar
        StuntionTopBar(
            title = "Notifications",
            onBackPressed = { navController.popBackStack() },
            isWithDivider = true
        )

        // New
        StuntionText(
            text = "New",
            textStyle = Type.titleMedium(),
            modifier = Modifier.padding(start = 16.dp, top = 24.dp, bottom = 8.dp)
        )

        // Notifications
        NotificationItem(
            imageUrl = "https://firebasestorage.googleapis.com/v0/b/stuntion-a32cc.appspot.com/o/notification%2Fcheck%20notification.jpg?alt=media&token=85195a96-0b18-4c9c-b5ff-8384a1f509a7",
            title = "Please check up your baby again with Check Stunting",
            description = "to find out the condition of your baby's growth",
            time = 9,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 6.dp)
        )
        NotificationItem(
            imageUrl = "https://firebasestorage.googleapis.com/v0/b/stuntion-a32cc.appspot.com/o/donation%2FBaby%20Formula%20Milk.jpg?alt=media&token=2674909b-2401-47c6-8abf-3a161aceb141",
            title = "Your request for help is being processed",
            description = "Pending further notification",
            time = 10,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 6.dp)
        )
        NotificationItem(
            imageUrl = "https://firebasestorage.googleapis.com/v0/b/stuntion-a32cc.appspot.com/o/expert%2FDavid%20Kim.jpg?alt=media&token=ce8e7ae8-3977-46e0-bc08-b97331d0be52",
            title = "Your payment has been successfully made",
            description = "Please chat with the health professional of your choice",
            time = 12,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 6.dp)
        )
    }
}