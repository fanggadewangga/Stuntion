package com.killjoy.stuntion.features.presentation.screen.verification.data

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.killjoy.stuntion.R
import com.killjoy.stuntion.features.presentation.utils.Screen
import com.killjoy.stuntion.features.presentation.utils.components.StuntionButton
import com.killjoy.stuntion.features.presentation.utils.components.StuntionTopBar
import com.killjoy.stuntion.ui.stuntionUI.StuntionText
import com.killjoy.stuntion.ui.theme.LightGray
import com.killjoy.stuntion.ui.theme.PrimaryBlue
import com.killjoy.stuntion.ui.theme.Type

@Composable
fun DataVerificationScreen(navController: NavController) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 24.dp)
            .verticalScroll(rememberScrollState())
    ) {
        // Top bar
        StuntionTopBar(
            title = "Data Verification",
            onBackPressed = { navController.popBackStack() },
            isWithDivider = true,
        )

        // KTP
        StuntionText(
            text = "E-KTP Photo",
            textStyle = Type.titleMedium(),
            modifier = Modifier.padding(top = 8.dp)
        )
        StuntionText(
            text = "Make sure you upload a photo of the original E-KTP and it's clearly visible",
            textStyle = Type.bodySmall(),
            color = LightGray,
            modifier = Modifier.padding(top = 8.dp)
        )
        AsyncImage(
            model = R.drawable.iv_card,
            contentDescription = "Card illustration",
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
                .align(CenterHorizontally)
        )
        StuntionButton(
            backgroundColor = Color.White,
            borderColor = PrimaryBlue,
            borderWidth = 0.5.dp,
            onClick = {
                navController.navigate(Screen.CardVerificationScreen.route)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        ) {
            StuntionText(
                text = "Take another photo",
                color = PrimaryBlue,
                textStyle = Type.labelLarge()
            )
        }

        // Face
        StuntionText(text = "Self-Portrait With E-KTP", textStyle = Type.titleMedium())
        StuntionText(
            text = "Make sure the E-KTP does not cover the face and can be read clearly in the photo",
            textStyle = Type.bodySmall(),
            color = LightGray
        )
        AsyncImage(
            model = R.drawable.iv_identity_verification,
            contentDescription = "Illustration",
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
                .align(CenterHorizontally)
        )
        StuntionButton(
            backgroundColor = Color.White,
            borderColor = PrimaryBlue,
            borderWidth = 0.5.dp,
            onClick = {
                navController.navigate(Screen.IdentityVerificationScreen.route)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp)
        ) {
            StuntionText(
                text = "Take another photo",
                color = PrimaryBlue,
                textStyle = Type.labelLarge()
            )
        }

        // Button
        Column(
            horizontalAlignment = CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(bottom = 48.dp)
        ) {
            AsyncImage(
                model = R.drawable.iv_verification_safe,
                contentDescription = "Verification",
                modifier = Modifier.height(40.dp)
            )

            StuntionButton(
                onClick = {
                    navController.navigate(Screen.VerificationSuccessScreen.route)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                StuntionText(
                    text = "Verification",
                    color = Color.White,
                    textStyle = Type.labelLarge()
                )
            }
        }
    }
}

@Preview
@Composable
fun DataVerificationPreview() {
    DataVerificationScreen(navController = rememberNavController())
}