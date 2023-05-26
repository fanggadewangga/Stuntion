package com.killjoy.stuntion.features.presentation.screen.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.killjoy.stuntion.R
import com.killjoy.stuntion.features.data.util.Resource
import com.killjoy.stuntion.features.presentation.utils.Constants.TIME_SPLASH
import com.killjoy.stuntion.features.presentation.utils.Screen
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    val viewModel = hiltViewModel<SplashViewModel>()
    val user = viewModel.userResponse.collectAsStateWithLifecycle()

    LaunchedEffect(user.value) {
        when(user.value) {
            is Resource.Loading -> {}
            is Resource.Success -> {
                if (!user.value.data!!.name.isNullOrEmpty() || user.value.data!!.name == "Anonymous")
                    viewModel.saveUserIndex(1)
                else if (!user.value.data!!.name.isNullOrEmpty() && user.value.data!!.name != "Anonymous" )
                    viewModel.saveUserIndex(3)
                else
                    viewModel.saveUserIndex(0)
            }
            is Resource.Empty -> { viewModel.saveUserIndex(0) }
            is Resource.Error -> {}
        }
        delay(TIME_SPLASH)
        navController.navigate(Screen.HomeScreen.route)
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