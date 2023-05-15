package com.killjoy.stuntion.features.presentation.screen.verification

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.killjoy.stuntion.R
import com.killjoy.stuntion.features.presentation.utils.Screen
import com.killjoy.stuntion.features.presentation.utils.components.StuntionButton
import com.killjoy.stuntion.features.presentation.utils.components.StuntionText
import com.killjoy.stuntion.ui.theme.PrimaryBlue
import com.killjoy.stuntion.ui.theme.Type

@Composable
fun VerificationSuccessScreen(navController: NavController) {
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
            Spacer(modifier = Modifier.height(24.dp))
            AsyncImage(
                model = R.drawable.iv_request_success,
                contentDescription = "Request success",
                modifier = Modifier.height(360.dp)
            )
            Spacer(modifier = Modifier.height(32.dp))
            StuntionText(
                text = "Data Sending Successful",
                textAlign = TextAlign.Center,
                textStyle = Type.titleLarge()
            )
            Spacer(modifier = Modifier.height(16.dp))
            StuntionText(
                text = "We have received your data and it will now be processed immediately. Please hold on, we are working as fast as we can!",
                textAlign = TextAlign.Center,
                textStyle = Type.bodyLarge()
            )
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(bottom = 48.dp)
        ) {
            StuntionButton(
                onClick = {
                    navController.navigate(Screen.RequestHelpScreen.route)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                StuntionText(
                    text = "Request Help Right Now",
                    color = Color.White,
                    textStyle = Type.labelLarge()
                )
            }

            StuntionButton(
                backgroundColor = Color.White,
                borderColor = PrimaryBlue,
                borderWidth = 0.5.dp,
                onClick = {
                    navController.navigate(Screen.SupportRequestTutorialScreen.route)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, bottom = 32.dp, end = 16.dp)
            ) {
                StuntionText(
                    text = "Back",
                    color = PrimaryBlue,
                    textStyle = Type.labelLarge()
                )
            }
        }

    }
}

@Preview
@Composable
fun VerificationSuccessPreview() {
    VerificationSuccessScreen(navController = rememberNavController())
}