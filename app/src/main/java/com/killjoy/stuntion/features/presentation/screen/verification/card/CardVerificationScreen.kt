package com.killjoy.stuntion.features.presentation.screen.verification.card

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.killjoy.stuntion.R
import com.killjoy.stuntion.features.presentation.utils.Screen
import com.killjoy.stuntion.features.presentation.utils.components.StuntionButton
import com.killjoy.stuntion.features.presentation.utils.components.StuntionTopBar
import com.killjoy.stuntion.features.presentation.utils.components.StuntionText
import com.killjoy.stuntion.ui.theme.Type

@Composable
fun CardVerificationScreen(navController: NavController) {
    Box(
        contentAlignment = Alignment.BottomCenter,
        modifier = Modifier
            .fillMaxHeight()
            .padding(top = 24.dp, bottom = (LocalConfiguration.current.screenHeightDp / 12).dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
        ) {
            StuntionTopBar(
                title = "KTP/E-KTP Verification",
                onBackPressed = { navController.popBackStack() },
                isWithDivider = true,
            )
            AsyncImage(
                model = R.drawable.iv_card,
                contentDescription = "Card illustration",
                modifier = Modifier
                    .height(280.dp)
                    .padding(start = 16.dp, top = 32.dp, end = 16.dp)
            )
            Spacer(modifier = Modifier.height(32.dp))
            StuntionText(
                text = "Prepare your Physical E-KTP",
                textAlign = TextAlign.Center,
                textStyle = Type.titleLarge(),
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            StuntionText(
                text = "Please take a photo of your e-KTP clearly, make sure the contents of the eKTP are legible!",
                textAlign = TextAlign.Center,
                textStyle = Type.bodyLarge(),
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            AsyncImage(
                model = R.drawable.iv_verification_safe,
                contentDescription = "Verification",
                modifier = Modifier.height(40.dp)
            )

            StuntionButton(
                onClick = {
                    navController.navigate(Screen.CardCameraScreen.route)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                StuntionText(
                    text = "Start",
                    color = Color.White,
                    textStyle = Type.labelLarge()
                )
            }
        }
    }
}

@Preview
@Composable
fun CardVerificationPrev() {
    CardVerificationScreen(navController = rememberNavController())
}