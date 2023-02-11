package com.killjoy.stuntion.features.presentation.screen.location_permission

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.killjoy.stuntion.ui.stuntionUI.StuntionText
import com.killjoy.stuntion.ui.theme.PrimaryBlue
import com.killjoy.stuntion.ui.theme.Type
import com.killjoy.stuntion.R
import com.killjoy.stuntion.features.presentation.utils.Screen
import com.killjoy.stuntion.features.presentation.utils.components.StuntionButton

@Composable
fun LocationPermissionScreen(navController: NavController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(PrimaryBlue)
    ) {
        // Location Text
        Spacer(modifier = Modifier.height(72.dp))
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            StuntionText(
                text = "Location",
                color = Color.White,
                textStyle = Type.displayMedium()
            )
        }

        // Box
        Spacer(modifier = Modifier.height(32.dp))
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Color.White, RoundedCornerShape(topStart = 48.dp, topEnd = 48.dp)
                )
                .padding(horizontal = 16.dp, vertical = 24.dp)
        ) {
            Column(modifier = Modifier.matchParentSize()) {

                // Enable location
                StuntionText(
                    text = "Enable Location",
                    textStyle = Type.headlineLarge(),
                )

                // We need location
                Spacer(modifier = Modifier.height(8.dp))
                StuntionText(
                    text = "We need your location if you need help fulfilling stunting nutrition",
                    textStyle = Type.bodyMedium(),
                    color = Color.Gray,
                    modifier = Modifier.width((LocalConfiguration.current.screenWidthDp * 0.9).dp)
                )

                // Image
                Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth()) {
                    AsyncImage(
                        model = R.drawable.iv_location,
                        contentDescription = "Location icon",
                        modifier = Modifier.size((LocalConfiguration.current.screenHeightDp * 0.47).dp)
                    )
                }

                // Button
                Spacer(modifier = Modifier.height(32.dp))
                StuntionButton(
                    onClick = { }, modifier = Modifier.fillMaxWidth()
                ) {
                    StuntionText(
                        text = "Enable Location", color = Color.White, textStyle = Type.labelLarge()
                    )
                }


                // Skip
                Spacer(modifier = Modifier.height(16.dp))
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    StuntionText(
                        text = "Skip",
                        color = PrimaryBlue,
                        textStyle = Type.bodyMedium(),
                        modifier = Modifier.clickable { navController.navigate(Screen.HomeScreen.route) }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun LocationPermissionPreview() {
    LocationPermissionScreen(navController = rememberNavController())
}
