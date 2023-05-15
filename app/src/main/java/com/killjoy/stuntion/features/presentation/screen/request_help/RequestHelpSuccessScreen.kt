package com.killjoy.stuntion.features.presentation.screen.request_help

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.killjoy.stuntion.R
import com.killjoy.stuntion.features.presentation.utils.Constants
import com.killjoy.stuntion.features.presentation.utils.Screen
import com.killjoy.stuntion.features.presentation.utils.components.StuntionButton
import com.killjoy.stuntion.features.presentation.utils.components.StuntionText
import com.killjoy.stuntion.ui.theme.PrimaryBlue
import com.killjoy.stuntion.ui.theme.Type
import kotlinx.coroutines.delay

@Composable
fun RequestHelpSuccessScreen(navController: NavController) {

    LaunchedEffect(true) {
        delay(Constants.TIME_SUCCESS_SCREEN)
        navController.navigate(Screen.SupportScreen.route) {
            popUpTo(Screen.RequestHelpSuccessScreen.route) {
                inclusive = true
            }
        }
    }

    Box(
        contentAlignment = Alignment.BottomCenter,
        modifier = Modifier
            .fillMaxHeight()
            .padding(horizontal = 16.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .align(Alignment.TopCenter)
        ) {
            AsyncImage(
                model = R.drawable.iv_request_success,
                contentDescription = "Request success",
                modifier = Modifier.height(360.dp)
            )
            Spacer(modifier = Modifier.height(32.dp))
            StuntionText(
                text = "Your Request For Help \nHas Been Sent",
                textAlign = TextAlign.Center,
                textStyle = Type.titleLarge()
            )
            Spacer(modifier = Modifier.height(16.dp))
            StuntionText(
                text = "We have received your request for help and it will now be processed immediately. Please wait, we are working as fast as we can!",
                textAlign = TextAlign.Center,
                textStyle = Type.bodyLarge()
            )
        }

        StuntionButton(
            backgroundColor = Color.White,
            borderColor = PrimaryBlue,
            borderWidth = 0.5.dp,
            onClick = {
                navController.navigate(Screen.SupportScreen.route) {
                    popUpTo(Screen.RequestHelpSuccessScreen.route) {
                        inclusive = true
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, bottom = 80.dp, end = 16.dp)
                .align(Alignment.Center)
        ) {
            StuntionText(
                text = "Back",
                color = PrimaryBlue,
                textStyle = Type.labelLarge()
            )
        }
    }
}