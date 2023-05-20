package com.killjoy.stuntion.features.presentation.screen.support.type

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.killjoy.stuntion.features.presentation.screen.support.detail.SupportDetailViewModel
import com.killjoy.stuntion.features.presentation.utils.components.StuntionButton
import com.killjoy.stuntion.features.presentation.utils.components.StuntionText
import com.killjoy.stuntion.ui.theme.LightGray
import com.killjoy.stuntion.ui.theme.PrimaryBlue
import com.killjoy.stuntion.ui.theme.Type

@Composable
fun SupportTypeSection(
    modifier: Modifier = Modifier,
    viewModel: SupportDetailViewModel,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = modifier
            .padding(top = 16.dp)
    ) {
        StuntionText(
            text = "Select Support Type",
            textStyle = Type.titleMedium(),
            modifier = Modifier.padding(bottom = 16.dp)
        )
        // Funds
        StuntionButton(
            onClick = {
                viewModel.supportTypeState.value = 1
            },
            backgroundColor = Color(0xFFE6E1E5),
            content = {
                StuntionText(text = "Support with funds", textStyle = Type.bodyMedium())
            },
            modifier = if (viewModel.supportTypeState.value == 1)
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .border(
                        width = 3.dp,
                        color = PrimaryBlue,
                        shape = RoundedCornerShape(100.dp)
                    )
            else Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )
        // Foods
        StuntionButton(
            onClick = {
                viewModel.supportTypeState.value = 2
            },
            backgroundColor = Color(0xFFE6E1E5),
            content = {
                StuntionText(
                    text = "Support with additional foods",
                    textStyle = Type.bodyMedium()
                )
            },
            modifier = if (viewModel.supportTypeState.value == 2)
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .border(
                        width = 3.dp,
                        color = PrimaryBlue,
                        shape = RoundedCornerShape(100.dp)
                    )
            else Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )

        // Instruction
        Row(
            horizontalArrangement = Arrangement.spacedBy(2.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(
                start = 16.dp,
                end = 16.dp,
                bottom = (LocalConfiguration.current.screenHeightDp / 8).dp
            )
        ) {
            StuntionText(text = "*", color = LightGray, textStyle = Type.bodySmall())
            StuntionText(
                text = "To choose support with additional food, it is expected that your location is no more than 10 kilometers",
                color = LightGray,
                textStyle = Type.bodySmall()
            )
        }
    }
}