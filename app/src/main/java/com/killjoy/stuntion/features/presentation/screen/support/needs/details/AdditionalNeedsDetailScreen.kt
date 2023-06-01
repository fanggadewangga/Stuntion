package com.killjoy.stuntion.features.presentation.screen.support.needs.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.killjoy.stuntion.R
import com.killjoy.stuntion.features.presentation.utils.Screen
import com.killjoy.stuntion.features.presentation.utils.components.StuntionButton
import com.killjoy.stuntion.features.presentation.utils.components.StuntionText
import com.killjoy.stuntion.features.presentation.utils.components.StuntionTopBar
import com.killjoy.stuntion.ui.theme.LightGray
import com.killjoy.stuntion.ui.theme.PrimaryBlue
import com.killjoy.stuntion.ui.theme.Type

@Composable
fun AdditionalNeedsDetailScreen(navController: NavController) {

    val systemUiController = rememberSystemUiController()
    LaunchedEffect(Unit) {
        systemUiController.apply {
            setStatusBarColor(color = Color.Transparent, darkIcons = true)
            setNavigationBarColor(color = Color.White, darkIcons = true)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = (LocalConfiguration.current.screenHeightDp / 30).dp)
    ) {

        // Top bar
        StuntionTopBar(
            title = "Support Details",
            onBackPressed = {
                navController.navigate(Screen.SupportScreen.route) {
                    popUpTo(Screen.SupportPaymentStatusScreen.route) {
                        inclusive = true
                    }
                }
            },
            isWithDivider = true
        )

        // Description
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            Color(0xFF1B6BB1),
                            Color(0xFF277CC7),
                            Color(0xFF4597E2),
                            Color(0xFF65B6FF)
                        ),
                        start = Offset.Zero,
                        end = Offset.Infinite
                    )
                )
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, top = 24.dp, end = 24.dp)
            ) {
                // Donation status
                StuntionText(
                    text = "Your donation is validated",
                    textStyle = Type.titleLarge(),
                    color = Color.White
                )

                // Description
                StuntionText(
                    text = "Please wait for our team to collect your donation",
                    textStyle = Type.bodyMedium(),
                    textAlign = TextAlign.Center,
                    color = Color.White
                )
            }

            // Rounded Box
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(top = 16.dp)
                    .height((LocalConfiguration.current.screenHeightDp * 0.77).dp)
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(topStart = 48.dp, topEnd = 48.dp)
                    )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .padding(horizontal = 24.dp, vertical = 16.dp)
                ) {

                    StuntionText(text = "Shipping Information", textStyle = Type.titleMedium())
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(32.dp),
                        modifier = Modifier.padding(start = 16.dp,top = 32.dp)
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            AsyncImage(
                                model = R.drawable.ic_shipping_taken_soon,
                                contentDescription = "Shipping taken soon icon",
                                modifier = Modifier.size(24.dp)
                            )
                            Divider(
                                color = LightGray, modifier = Modifier
                                    .width(8.dp)
                                    .padding(top = 12.dp)
                                    .rotate(90F)
                            )
                            Divider(
                                color = LightGray, modifier = Modifier
                                    .width(8.dp)
                                    .padding(top = 12.dp)
                                    .rotate(90F)
                            )
                        }
                        StuntionText(
                            text = "Donations will be taken soon",
                            textStyle = Type.titleBold()
                        )
                    }

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(32.dp),
                        modifier = Modifier.padding(start = 16.dp, top = 8.dp)
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            AsyncImage(
                                model = R.drawable.ic_gray_circle,
                                contentDescription = "Shipping taken soon icon",
                                modifier = Modifier.size(24.dp)
                            )
                            Divider(
                                color = LightGray, modifier = Modifier
                                    .width(8.dp)
                                    .padding(top = 12.dp)
                                    .rotate(90F)
                            )
                            Divider(
                                color = LightGray, modifier = Modifier
                                    .width(8.dp)
                                    .padding(top = 12.dp)
                                    .rotate(90F)
                            )
                        }
                        StuntionText(
                            text = "Donations is being delivered",
                            textStyle = Type.titleBold()
                        )
                    }

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(32.dp),
                        modifier = Modifier.padding(start = 16.dp, top = 8.dp)
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            AsyncImage(
                                model = R.drawable.ic_gray_circle,
                                contentDescription = "Shipping taken soon icon",
                                modifier = Modifier.size(24.dp)
                            )
                        }
                        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            StuntionText(
                                text = "Donations is received",
                                textStyle = Type.titleBold()
                            )
                            StuntionText(
                                text = "Additional needs has been received by the requester for support",
                                textStyle = Type.bodyMedium(),
                                color = LightGray
                            )
                        }
                    }

                    Divider(
                        color = LightGray, modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp)
                    )

                    // Shipping Location and Estimated Up
                    StuntionText(text = "Shipping Information", textStyle = Type.titleMedium())
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(32.dp),
                        modifier = Modifier.padding(start = 16.dp, top = 8.dp)
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            AsyncImage(
                                model = R.drawable.ic_blue_marker,
                                contentDescription = "Blue marker",
                                modifier = Modifier.size(32.dp)
                            )
                            Divider(
                                color = LightGray, modifier = Modifier
                                    .width(8.dp)
                                    .padding(top = 12.dp)
                                    .rotate(90F)
                            )
                            Divider(
                                color = LightGray, modifier = Modifier
                                    .width(8.dp)
                                    .padding(top = 12.dp)
                                    .rotate(90F)
                            )
                            Divider(
                                color = LightGray, modifier = Modifier
                                    .width(8.dp)
                                    .padding(top = 12.dp)
                                    .rotate(90F)
                            )
                        }
                        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            StuntionText(
                                text = "Jl. Plaosan Timur No. 100",
                                textStyle = Type.titleBold()
                            )
                            StuntionText(
                                text = "9:10 AM",
                                textStyle = Type.bodyMedium(),
                                color = LightGray
                            )
                        }
                    }
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(32.dp),
                        modifier = Modifier.padding(start = 16.dp, top = 8.dp)
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            AsyncImage(
                                model = R.drawable.ic_red_marker,
                                contentDescription = "Red marker",
                                modifier = Modifier.size(32.dp)
                            )
                        }
                        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            StuntionText(
                                text = "Jl. Veteran No.8, Ketawanggede",
                                textStyle = Type.titleBold()
                            )
                            StuntionText(
                                text = "9:26 AM",
                                textStyle = Type.bodyMedium(),
                                color = PrimaryBlue
                            )
                        }
                    }

                    Divider(
                        color = LightGray, modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp)
                    )


                    // Help
                    StuntionText(text = "Do you need help?", textStyle = Type.titleBold(), modifier = Modifier.padding(bottom = 16.dp))
                    StuntionButton(
                        backgroundColor = Color.White,
                        borderColor = PrimaryBlue,
                        borderWidth = 2.dp,
                        onClick = {
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp)
                    ) {
                        StuntionText(
                            text = "Help",
                            color = PrimaryBlue,
                            textStyle = Type.labelLarge()
                        )
                    }
                }
            }
        }
    }
}