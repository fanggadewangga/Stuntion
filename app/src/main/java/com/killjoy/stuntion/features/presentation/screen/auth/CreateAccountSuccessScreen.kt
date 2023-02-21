package com.killjoy.stuntion.features.presentation.screen.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.killjoy.stuntion.R
import com.killjoy.stuntion.features.presentation.utils.Constants.TIME_SPLASH
import com.killjoy.stuntion.features.presentation.utils.Screen
import com.killjoy.stuntion.ui.stuntionUI.StuntionText
import com.killjoy.stuntion.ui.theme.PrimaryBlue
import com.killjoy.stuntion.ui.theme.Type
import kotlinx.coroutines.delay

@Composable
fun CreateAccountSuccessScreen(navController: NavController) {

    LaunchedEffect(key1 = true) {
        delay(TIME_SPLASH)
        navController.navigate(Screen.HomeScreen.route) {
            popUpTo(Screen.SplashScreen.route) {
                inclusive = true
            }
        }
    }

    Box(
        contentAlignment = Alignment.Center, modifier = Modifier
            .fillMaxSize()
            .background(color = PrimaryBlue)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            StuntionText(
                text = "Account created successfully",
                color = Color.White,
                textStyle = Type.titleLarge()
            )
            AsyncImage(model = R.drawable.ic_create_acc_success, contentDescription = "Icon")
        }
    }
}