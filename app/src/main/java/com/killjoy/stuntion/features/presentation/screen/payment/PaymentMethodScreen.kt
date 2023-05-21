package com.killjoy.stuntion.features.presentation.screen.payment

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.killjoy.stuntion.features.data.util.Resource
import com.killjoy.stuntion.features.presentation.screen.home.HomePaymentSharedViewModel
import com.killjoy.stuntion.features.presentation.utils.components.ErrorLayout
import com.killjoy.stuntion.features.presentation.utils.components.PaymentMethodItem
import com.killjoy.stuntion.features.presentation.utils.components.StuntionText
import com.killjoy.stuntion.features.presentation.utils.components.StuntionTopBar
import com.killjoy.stuntion.ui.theme.Type

@Composable
fun PaymentMethodScreen(navController: NavController, homePaymentSharedViewModel: HomePaymentSharedViewModel) {
    val viewModel = hiltViewModel<PaymentMethodViewModel>()
    val systemUiController = rememberSystemUiController()
    val paymentResponse = viewModel.paymentResponse.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        systemUiController.apply {
            setStatusBarColor(color = Color.Transparent, darkIcons = true)
            setNavigationBarColor(color = Color.White, darkIcons = true)
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = (LocalConfiguration.current.screenHeightDp / 17).dp)
    ) {

        // Top Bar
        item {
            StuntionTopBar(
                title = "Payment Method",
                onBackPressed = { navController.popBackStack() },
                isWithDivider = true
            )
            Spacer(modifier = Modifier.height(8.dp))
        }

        when (paymentResponse.value) {
            is Resource.Loading -> {}
            is Resource.Success -> {
                item {
                    // Instants
                    StuntionText(
                        text = "Instant Payments (automatic verification)",
                        textStyle = Type.titleMedium(),
                        modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
                    )
                }
                items(paymentResponse.value.data!!.filter { it.type == 1 && it.paymentName == "QRIS" }) {
                    PaymentMethodItem(
                        payment = it,
                        onClick = {
                            homePaymentSharedViewModel.apply {
                                isHasSelectedAPayment.value = true
                                selectedPaymentNameState.value = it.paymentName
                                selectedPaymentImageUrlState.value = it.imageUrl
                            }
                            navController.popBackStack()
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                    )
                }

                // VA
                item {
                    Spacer(modifier = Modifier.height(16.dp))
                    StuntionText(
                        text = "Virtual Account (automatic verification)",
                        textStyle = Type.titleMedium(),
                        modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
                    )
                }
                items(paymentResponse.value.data!!.filter { it.type == 2 }) {
                    PaymentMethodItem(
                        payment = it,
                        onClick = {
                            homePaymentSharedViewModel.apply {
                                isHasSelectedAPayment.value = true
                                selectedPaymentNameState.value = it.paymentName
                                selectedPaymentImageUrlState.value = it.imageUrl
                            }
                            navController.popBackStack()
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                    )
                }


                // Transfers
                item {
                    Spacer(modifier = Modifier.height(16.dp))
                    StuntionText(
                        text = "Transfer Bank (manual verification)",
                        textStyle = Type.titleMedium(),
                        modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
                    )
                }
                items(paymentResponse.value.data!!.filter { it.type == 3 }) {
                    PaymentMethodItem(
                        payment = it,
                        onClick = {
                            homePaymentSharedViewModel.apply {
                                isHasSelectedAPayment.value = true
                                selectedPaymentNameState.value = it.paymentName
                                selectedPaymentImageUrlState.value = it.imageUrl
                            }
                            navController.popBackStack()
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                    )
                }

            }

            is Resource.Error ->
                item {
                    ErrorLayout("Something went wrong")
                }

            is Resource.Empty -> {}
        }
    }
}