package com.killjoy.stuntion.features.presentation.screen.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.killjoy.stuntion.R
import com.killjoy.stuntion.features.presentation.utils.Constants.TIME_SPLASH
import com.killjoy.stuntion.features.presentation.utils.Screen
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {

    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val viewModel = hiltViewModel<SplashViewModel>()
    val uid = viewModel.uid.collectAsState()
    val isHaveCreatedAccount = viewModel.isHaveCreatedAccount.collectAsState()

    LaunchedEffect(key1 = true) {
        viewModel.apply {
            readUid()
            readHaveCreatedAccount()
        }
        delay(TIME_SPLASH)
        if (uid.value == "") {
            navController.navigate(Screen.LoginScreen.route) {
                popUpTo(Screen.SplashScreen.route) {
                    inclusive = true
                }
            }
        } else {
            if (isHaveCreatedAccount.value)
                navController.navigate(Screen.HomeScreen.route) {
                    popUpTo(Screen.SplashScreen.route) {
                        inclusive = true
                    }
                }
            else {
                navController.navigate(Screen.GeneralInformationScreen.route) {
                    popUpTo(Screen.SplashScreen.route) {
                        inclusive = true
                    }
                }
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Image(
            painter = painterResource(id = R.drawable.iv_splash),
            contentDescription = "App Text Logo",
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp)
                .align(Alignment.Center)
        )
    }
}