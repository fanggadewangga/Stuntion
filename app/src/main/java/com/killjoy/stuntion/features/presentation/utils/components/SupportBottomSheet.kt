package com.killjoy.stuntion.features.presentation.utils.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.killjoy.stuntion.features.presentation.screen.support.detail.SupportDetailViewModel
import com.killjoy.stuntion.features.presentation.screen.support.nominal.SupportNominal
import com.killjoy.stuntion.features.presentation.screen.support.payment.SupportPayment
import com.killjoy.stuntion.features.presentation.screen.support.type.SupportType
import com.killjoy.stuntion.ui.theme.PrimaryBlue
import com.killjoy.stuntion.ui.theme.Type

@Composable
fun SupportBottomSheet(
    modifier: Modifier = Modifier,
    viewModel: SupportDetailViewModel,
) {
    Column(modifier = modifier) {
        when(viewModel.sendSupportStepState.value) {
            1 -> SupportType(viewModel = viewModel)
            2 -> SupportNominal(viewModel = viewModel)
            3 -> SupportPayment(viewModel = viewModel)
        }

        // Next
        StuntionButton(
            enabled = viewModel.supportTypeState.value == 1 || viewModel.supportTypeState.value == 2,
            onClick = {
                if (viewModel.sendSupportStepState.value in 1 until 3)
                    viewModel.sendSupportStepState.value++
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
                .padding(horizontal = 16.dp)
        )
    }
}