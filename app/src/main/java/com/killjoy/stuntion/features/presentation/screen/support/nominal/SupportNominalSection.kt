package com.killjoy.stuntion.features.presentation.screen.support.nominal

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.killjoy.stuntion.features.presentation.screen.support.detail.SupportDetailViewModel
import com.killjoy.stuntion.features.presentation.utils.components.StuntionBasicTextField
import com.killjoy.stuntion.features.presentation.utils.components.StuntionText
import com.killjoy.stuntion.features.presentation.utils.components.SupportNominalSelectorItem
import com.killjoy.stuntion.ui.theme.LightGray
import com.killjoy.stuntion.ui.theme.Type

@Composable
fun SupportNominalSection(
    modifier: Modifier = Modifier,
    viewModel: SupportDetailViewModel,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = modifier
            .padding(16.dp)
    ) {
        StuntionText(
            text = "Enter Support Nominal",
            textStyle = Type.titleMedium(),
            modifier = Modifier
                .align(CenterHorizontally)
                .padding(bottom = 16.dp)
        )

        // Nominal Text Field
        StuntionBasicTextField(
            placeHolder = "0",
            value = viewModel.nominalState.value.toString(),
            isWithBorder = false,
            onValueChange = {
                viewModel.isNominalFieldClicked.value = true
                viewModel.nominalState.value = it.toIntOrNull() ?: 0
            },
            leadingIcon = {
                StuntionText(
                    text = "IDR",
                    textStyle = Type.titleLarge(),
                    modifier = Modifier.padding(start = 18.dp, end = 4.dp)
                )
            },
            shape = RoundedCornerShape(100.dp),
            singleLine = true,
            textStyle = Type.titleLarge(),
            isError = !viewModel.isNominalValid.value,
            showWarningMessage = !viewModel.isNominalValid.value,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
            warningMessage = "Field could not be empty.",
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color(0xFFE6E1E5), shape = RoundedCornerShape(100.dp))
        )

        // Instruction
        StuntionText(
            text = "Fill your support donation nominal",
            textStyle = Type.bodySmall(),
            color = LightGray,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Nominal selector
        viewModel.listOfSupportNominal.forEach { supportNominal ->
            SupportNominalSelectorItem(
                nominal = supportNominal,
                isSelected = viewModel.nominalState.value == supportNominal.nominal,
                onSelectorClicked = {
                    viewModel.nominalState.value = supportNominal.nominal
                }
            )
        }
    }
}