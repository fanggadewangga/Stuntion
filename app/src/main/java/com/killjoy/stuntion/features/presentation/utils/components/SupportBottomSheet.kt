package com.killjoy.stuntion.features.presentation.utils.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.killjoy.stuntion.features.presentation.screen.support.detail.SupportDetailViewModel
import com.killjoy.stuntion.features.presentation.screen.support.nominal.SupportNominalSection
import com.killjoy.stuntion.features.presentation.screen.support.payment.SupportPaymentSection
import com.killjoy.stuntion.features.presentation.screen.support.payment.SupportPaymentSharedViewModel
import com.killjoy.stuntion.features.presentation.screen.support.type.SupportTypeSection
import com.killjoy.stuntion.ui.theme.PrimaryBlue
import com.killjoy.stuntion.ui.theme.Type

@Composable
fun SupportBottomSheet(
    modifier: Modifier = Modifier,
    navigateToSupportPaymentScreen: () -> Unit,
    navigateToSupportPaymentStatusScreen: () -> Unit,
    viewModel: SupportDetailViewModel,
    sharedViewModel: SupportPaymentSharedViewModel,
) {
    Column(modifier = modifier) {
        when (viewModel.sendSupportStepState.value) {
            1 -> SupportTypeSection(viewModel = viewModel)
            2 -> SupportNominalSection(viewModel = viewModel)
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
                if (viewModel.sendSupportStepState.value in 1 until 3)
                    viewModel.sendSupportStepState.value++
                else if (viewModel.sendSupportStepState.value == 3)
                    navigateToSupportPaymentStatusScreen()
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