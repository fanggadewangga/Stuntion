package com.killjoy.stuntion.features.presentation.screen.payment.instruction

import android.os.CountDownTimer
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.killjoy.stuntion.R
import com.killjoy.stuntion.features.data.util.Resource
import com.killjoy.stuntion.features.presentation.screen.home.HomePaymentSharedViewModel
import com.killjoy.stuntion.features.presentation.utils.Screen
import com.killjoy.stuntion.features.presentation.utils.components.StuntionButton
import com.killjoy.stuntion.features.presentation.utils.components.StuntionText
import com.killjoy.stuntion.features.presentation.utils.components.StuntionTopBar
import com.killjoy.stuntion.ui.theme.LightGray
import com.killjoy.stuntion.ui.theme.PrimaryBlue
import com.killjoy.stuntion.ui.theme.Type
import es.dmoral.toasty.Toasty
import kotlinx.coroutines.delay

@Composable
fun PaymentInstructionScreen(
    navController: NavController,
    sharedViewModel: HomePaymentSharedViewModel,
) {
    val context = LocalContext.current
    val viewModel = hiltViewModel<PaymentInstructionViewModel>()
    val systemUiController = rememberSystemUiController()
    val walletResponse = viewModel.walletResponse.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        systemUiController.apply {
            setStatusBarColor(color = Color.Transparent, darkIcons = true)
            setNavigationBarColor(color = Color.White, darkIcons = true)
        }
        viewModel.apply {
            bankAccountNumber.value = (0..999999999).random()
            delay(2000L)
            updateUserWalletBalance(sharedViewModel.selectedNominal.value)
        }
    }

    LaunchedEffect(viewModel.totalSecond.value) {
        val timer = object : CountDownTimer(viewModel.totalSecond.value * 1000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val hours = millisUntilFinished / 3600000
                val minutes = (millisUntilFinished % 3600000) / 60000
                val seconds = (millisUntilFinished % 60000) / 1000

                 viewModel.formattedTime.value =
                    String.format("%02d:%02d:%02d", hours, minutes, seconds)
            }

            override fun onFinish() {
                Toasty.info(context, "Payment time has expired", Toast.LENGTH_SHORT).show()
            }
        }
        timer.start()
    }

    LaunchedEffect(walletResponse.value) {
        if (walletResponse.value is Resource.Success) {
            Toasty.success(context, "Balance top up successful!", Toast.LENGTH_SHORT).show()
            navController.navigate(Screen.HomeScreen.route) {
                popUpTo(Screen.PaymentInstructionScreen.route) {
                    inclusive = true
                }
            }
        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = (LocalConfiguration.current.screenHeightDp / 30).dp)
    ) {

        // Top bar
        StuntionTopBar(
            title = "Payment Instructions",
            onBackPressed = { navController.popBackStack() },
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
                modifier = Modifier.padding(start = 24.dp, top = 24.dp, end = 24.dp)
            ) {
                // Payment deadline
                StuntionText(
                    text = "Payment deadline",
                    textStyle = Type.bodyLarge(),
                    color = Color.LightGray
                )

                // Payment timer
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    StuntionText(
                        text = "Payment deadline",
                        textStyle = Type.titleMedium(),
                        color = Color.White
                    )
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(3.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        AsyncImage(
                            model = R.drawable.ic_clock,
                            contentDescription = "Clock",
                            colorFilter = ColorFilter.tint(Color.White),
                            modifier = Modifier.size(20.dp)
                        )
                        StuntionText(
                            text = viewModel.formattedTime.value,
                            textStyle = Type.titleMedium(),
                            color = Color.White
                        )
                    }
                }
            }

            // Rounded Box
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .height((LocalConfiguration.current.screenHeightDp * 0.8).dp)
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
                    )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 24.dp)
                ) {

                    // Image and name
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 6.dp, top = 24.dp, end = 6.dp)
                    ) {
                        AsyncImage(
                            model = sharedViewModel.selectedPaymentImageUrlState.value,
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

                    // VA number
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
                                .padding(horizontal = 24.dp, vertical = 8.dp)
                        ) {
                            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                                StuntionText(
                                    text = "Bank account number",
                                    textStyle = Type.bodyMedium()
                                )
                                StuntionText(
                                    text = viewModel.bankAccountNumber.value.toString(),
                                    textStyle = Type.titleMedium()
                                )
                            }

                            // Copy
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier
                                    .wrapContentSize()
                                    .border(
                                        width = 1.dp,
                                        color = PrimaryBlue,
                                        shape = RoundedCornerShape(100.dp)
                                    )
                            ) {
                                StuntionText(
                                    text = "Copy",
                                    color = PrimaryBlue,
                                    textStyle = Type.labelLarge(),
                                    modifier = Modifier.padding(horizontal = 24.dp, vertical = 6.dp)
                                )
                            }
                        }
                    }

                    // Total
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
                                .padding(horizontal = 24.dp, vertical = 10.dp)
                        ) {
                            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                                StuntionText(
                                    text = "Total payment",
                                    textStyle = Type.bodyMedium()
                                )
                                StuntionText(
                                    text = "Rp${sharedViewModel.selectedNominal.value}",
                                    textStyle = Type.titleMedium()
                                )
                            }

                            // Copy
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier
                                    .wrapContentSize()
                                    .border(
                                        width = 1.dp,
                                        color = PrimaryBlue,
                                        shape = RoundedCornerShape(100.dp)
                                    )
                            ) {
                                StuntionText(
                                    text = "Copy",
                                    color = PrimaryBlue,
                                    textStyle = Type.labelLarge(),
                                    modifier = Modifier.padding(horizontal = 24.dp, vertical = 6.dp)
                                )
                            }
                        }
                    }

                    // Tutorial
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 32.dp)
                    ) {
                        StuntionText(
                            text = "Payment Method Tutorials",
                            textStyle = Type.wallet(),
                            modifier = Modifier
                                .align(
                                    Alignment.Center
                                )
                        )
                        // Arrow
                        Image(
                            imageVector = Icons.Default.KeyboardArrowRight,
                            contentDescription = "Arrow icon",
                            modifier = Modifier.align(Alignment.CenterEnd)
                        )
                    }
                    Divider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp)
                    )

                    // Back to home
                    StuntionButton(
                        onClick = {

                        }, modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 24.dp)
                    ) {
                        StuntionText(
                            text = "Check Payment Status",
                            color = Color.White,
                            textStyle = Type.labelLarge()
                        )
                    }

                    // Cancel
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp)
                    ) {
                        StuntionText(
                            text = "Cancel Top Up",
                            color = Color.Red,
                            textStyle = Type.labelLarge(),
                            modifier = Modifier
                                .clickable {

                                }
                                .align(Alignment.Center)
                        )
                    }
                    Divider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 24.dp)
                    )

                    // Your top
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

                    // Help
                    StuntionButton(
                        backgroundColor = Color.White,
                        borderColor = PrimaryBlue,
                        borderWidth = 0.5.dp,
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