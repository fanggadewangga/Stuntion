package com.killjoy.stuntion.features.presentation.utils.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.killjoy.stuntion.features.data.util.Resource
import com.killjoy.stuntion.features.presentation.screen.support.detail.SupportDetailViewModel
import com.killjoy.stuntion.features.presentation.screen.support.location.SupportLocationConfirmationSection
import com.killjoy.stuntion.features.presentation.screen.support.nominal.SupportNominalSection
import com.killjoy.stuntion.features.presentation.screen.support.payment.SupportPaymentSection
import com.killjoy.stuntion.features.presentation.screen.support.payment.SupportPaymentSharedViewModel
import com.killjoy.stuntion.features.presentation.screen.support.type.SupportTypeSection
import com.killjoy.stuntion.ui.theme.PrimaryBlue
import com.killjoy.stuntion.ui.theme.Type
import kotlinx.coroutines.launch

@Composable
fun SupportBottomSheet(
    modifier: Modifier = Modifier,
    donationId: String,
    showErrorToast: () -> Unit,
    navigateToSupportPaymentScreen: () -> Unit,
    navigateToSupportPaymentStatusScreen: () -> Unit,
    navigateToAdditionalFoodScreen: () -> Unit,
    viewModel: SupportDetailViewModel,
    sharedViewModel: SupportPaymentSharedViewModel,
) {
    val userBalanceResponse = viewModel.userBalanceResponse.collectAsStateWithLifecycle()
    val giveDonationResponse = viewModel.giveDonationResponse.collectAsStateWithLifecycle()
    val supportDetailResponse = viewModel.donationResponse.collectAsStateWithLifecycle()
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(giveDonationResponse.value) {
        if (giveDonationResponse.value is Resource.Success)
            navigateToSupportPaymentStatusScreen()
    }

    Column(modifier = modifier) {
        when (viewModel.sendSupportStepState.value) {
            1 -> SupportTypeSection(viewModel = viewModel)
            2 -> {
                if (viewModel.supportTypeState.value == 1)
                    SupportNominalSection(viewModel = viewModel)
                else if (viewModel.supportTypeState.value == 2)
                    SupportLocationConfirmationSection(
                        destinationAddress = if (supportDetailResponse.value is Resource.Success) supportDetailResponse.value.data!!.address else "",
                        viewModel = viewModel)
            }
            3 -> SupportPaymentSection(
                viewModel = viewModel,
                sharedViewModel = sharedViewModel,
                onMethodClicked = {
                    navigateToSupportPaymentScreen()
                }
            )
        }

        // Next
        StuntionButton(
            enabled = viewModel.supportTypeState.value == 1 || viewModel.supportTypeState.value == 2,
            onClick = {
                if (viewModel.sendSupportStepState.value in 1 until 3) {
                    if (viewModel.sendSupportStepState.value == 2 && viewModel.supportTypeState.value == 2)
                        navigateToAdditionalFoodScreen()
                    else
                        viewModel.sendSupportStepState.value++
                }
                else if (viewModel.sendSupportStepState.value == 3) {
                    if (userBalanceResponse.value is Resource.Success && userBalanceResponse.value.data != null) {
                        // Check wallet balance
                        if (userBalanceResponse.value.data!!.balance < viewModel.nominalState.value)
                            showErrorToast()
                        else
                            coroutineScope.launch {
                                viewModel.giveNewDonation(
                                    donationId,
                                    viewModel.isAnonymous.value
                                )
                            }
                    }
                }
            },
            backgroundColor = PrimaryBlue,
            content = {
                StuntionText(
                    text = if (viewModel.sendSupportStepState.value == 1) "Next" else "Next payment",
                    textStyle = Type.labelLarge(),
                    color = Color.White
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 32.dp, end = 16.dp)
        )
    }
}