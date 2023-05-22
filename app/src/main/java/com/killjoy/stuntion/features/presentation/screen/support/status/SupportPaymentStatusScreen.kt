package com.killjoy.stuntion.features.presentation.screen.support.status

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.killjoy.stuntion.features.presentation.screen.support.payment.SupportPaymentSharedViewModel
import com.killjoy.stuntion.features.presentation.utils.Screen
import com.killjoy.stuntion.features.presentation.utils.components.StuntionButton
import com.killjoy.stuntion.features.presentation.utils.components.StuntionText
import com.killjoy.stuntion.features.presentation.utils.components.StuntionTopBar
import com.killjoy.stuntion.ui.theme.Green
import com.killjoy.stuntion.ui.theme.LightGray
import com.killjoy.stuntion.ui.theme.LightGreen
import com.killjoy.stuntion.ui.theme.PrimaryBlue
import com.killjoy.stuntion.ui.theme.Type
import kotlinx.coroutines.delay
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun SupportPaymentStatusScreen(
    navController: NavController,
    sharedViewModel: SupportPaymentSharedViewModel,
) {
    val viewModel = hiltViewModel<SupportPaymentStatusViewModel>()
    val systemUiController = rememberSystemUiController()
    val deadline = DateTimeFormatter.ofPattern("MMMM dd, yyyy HH:mm")
        .format(LocalDateTime.now().plusMinutes(15L))
    val date = DateTimeFormatter.ofPattern("MMMM dd, yyyy").format(LocalDate.now())
    LaunchedEffect(Unit) {
        systemUiController.apply {
            setStatusBarColor(color = Color.Transparent, darkIcons = true)
            setNavigationBarColor(color = Color.White, darkIcons = true)
        }
        viewModel.supportNumberState.value = (0..999999).random()
        delay(6000L)
        viewModel.isLoadingStatusState.value = false
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = (LocalConfiguration.current.screenHeightDp / 30).dp)
    ) {

        // Top bar
        StuntionTopBar(
            title = "Payment Status",
            onBackPressed = {
                navController.navigate(Screen.SupportScreen.route) {
                    popUpTo(Screen.SupportPaymentStatusScreen.route) {
                        inclusive = true
                    }
                }
            },
            isWithDivider = true
        )

        // Deadline
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
                // Payment deadline
                StuntionText(
                    text = if (viewModel.isLoadingStatusState.value) "Waiting for payment" else "Thank you",
                    textStyle = Type.titleMedium(),
                    color = Color.White
                )

                // Description
                StuntionText(
                    text = if (viewModel.isLoadingStatusState.value) "Transfer immediately before" else "Your donation has been received and will be distributed",
                    textStyle = Type.bodyMedium(),
                    textAlign = TextAlign.Center,
                    color = Color.White
                )

                // Date
                StuntionText(
                    text = if (viewModel.isLoadingStatusState.value) deadline else date,
                    textStyle = Type.titleSmall(),
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
                        .padding(horizontal = 24.dp)
                ) {

                    // Image and name
                    Spacer(modifier = Modifier.height(16.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                color = Color(0xFFF4EFF4),
                                shape = RoundedCornerShape(100.dp)
                            )
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 24.dp, vertical = 16.dp)
                        ) {
                            AsyncImage(
                                model = sharedViewModel.selectedPaymentImageState.value,
                                contentDescription = "Payment logo",
                                modifier = Modifier
                                    .width(64.dp)
                                    .height(20.dp)
                            )
                            StuntionText(
                                text = sharedViewModel.selectedPaymentNameState.value,
                                textStyle = Type.wallet()
                            )
                        }
                    }

                    // Support number
                    Spacer(modifier = Modifier.height(16.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                color = Color(0xFFF4EFF4),
                                shape = RoundedCornerShape(100.dp)
                            )
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 24.dp, vertical = 16.dp)
                        ) {
                            StuntionText(
                                text = "ID Support",
                                textStyle = Type.bodyMedium()
                            )
                            StuntionText(
                                text = "#ST${viewModel.supportNumberState.value}",
                                textStyle = Type.titleMedium()
                            )
                        }
                    }

                    // Status
                    Spacer(modifier = Modifier.height(16.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                color = Color(0xFFF4EFF4),
                                shape = RoundedCornerShape(100.dp)
                            )
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 24.dp, vertical = 16.dp)
                        ) {
                            StuntionText(
                                text = "Status",
                                textStyle = Type.bodyMedium()
                            )
                            // Copy
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier
                                    .wrapContentSize()
                                    .background(
                                        color = if (viewModel.isLoadingStatusState.value) Color.White else LightGreen,
                                        shape = RoundedCornerShape(100.dp)
                                    )
                                    .border(
                                        width = 1.dp,
                                        color = if (viewModel.isLoadingStatusState.value) PrimaryBlue else Green,
                                        shape = RoundedCornerShape(100.dp)
                                    )
                            ) {
                                StuntionText(
                                    text = if (viewModel.isLoadingStatusState.value) "Pending" else "Succeed",
                                    color = if (viewModel.isLoadingStatusState.value) PrimaryBlue else Green,
                                    textStyle = Type.labelLarge(),
                                    modifier = Modifier.padding(horizontal = 24.dp, vertical = 6.dp)
                                )
                            }
                        }
                    }

                    // Update status
                    Spacer(modifier = Modifier.height(8.dp))
                    if (viewModel.isLoadingStatusState.value)
                        StuntionButton(
                            backgroundColor = Color.White,
                            borderColor = PrimaryBlue,
                            borderWidth = 2.dp,
                            onClick = {
                                viewModel.isLoadingStatusState.value = false
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 16.dp)
                        ) {
                            StuntionText(
                                text = "Update Status",
                                color = PrimaryBlue,
                                textStyle = Type.labelLarge()
                            )
                        }
                    Divider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 24.dp)
                    )

                    // Your top
                    if (viewModel.isLoadingStatusState.value) {
                        StuntionText(
                            text = "Your top up is not received?",
                            textStyle = Type.titleMedium(),
                            modifier = Modifier.padding(top = 16.dp)
                        )
                        StuntionText(
                            text = "Get in touch with help and provide evidence of the transaction for immediate verification, which will be completed within a maximum of 24 hours.",
                            textStyle = Type.bodyMedium(),
                            color = LightGray,
                            modifier = Modifier.padding(top = 16.dp)
                        )
                    }

                    // Help
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