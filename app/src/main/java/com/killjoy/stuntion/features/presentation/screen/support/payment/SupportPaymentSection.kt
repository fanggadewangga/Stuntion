package com.killjoy.stuntion.features.presentation.screen.support.payment

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.killjoy.stuntion.features.presentation.screen.support.detail.SupportDetailViewModel
import com.killjoy.stuntion.features.presentation.utils.components.StuntionBasicTextField
import com.killjoy.stuntion.features.presentation.utils.components.StuntionText
import com.killjoy.stuntion.ui.theme.LightGray
import com.killjoy.stuntion.ui.theme.PrimaryBlue
import com.killjoy.stuntion.ui.theme.Type

@Composable
fun SupportPaymentSection(
    modifier: Modifier = Modifier,
    viewModel: SupportDetailViewModel,
    sharedViewModel: SupportPaymentSharedViewModel,
    onMethodClicked: () -> Unit,
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
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 16.dp)
        )

        // Nominal Text Field
        StuntionBasicTextField(
            placeHolder = "0",
            value = viewModel.nominalState.value.toString(),
            isWithBorder = false,
            onValueChange = {},
            enabled = false,
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
            text = "Fill your support wallet according to your needs",
            color = LightGray,
            textStyle = Type.bodySmall(),
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Method
        StuntionText(
            text = "Payment Method",
            textStyle = Type.titleMedium()
        )
        Box(
            contentAlignment = Alignment.CenterStart,
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
                .border(
                    width = 1.dp,
                    color = LightGray,
                    shape = RoundedCornerShape(100.dp)
                )
                .clickable(
                    enabled = true,
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple(color = Color.LightGray),
                    onClick = onMethodClicked
                )
        ) {
            if (sharedViewModel.isHasSelectedPaymentState.value) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 24.dp)
                ) {
                    // Logo and name
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        AsyncImage(
                            model = sharedViewModel.selectedPaymentImageState.value,
                            contentDescription = "Payment method logo",
                            modifier = Modifier.size(64.dp)
                        )
                        StuntionText(
                            text = sharedViewModel.selectedPaymentNameState.value,
                            textStyle = Type.bodyLarge(),
                            modifier = Modifier.padding(start = 16.dp)
                        )
                    }

                    // Arrow
                    Image(
                        imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription = "Arrow icon",
                        modifier = Modifier.padding(end = 16.dp)
                    )
                }
            } else {
                StuntionText(
                    text = "Choose",
                    textStyle = Type.bodyLarge(),
                    color = LightGray,
                    maxLine = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(start = 16.dp)
                )
                Image(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = "Arrow icon",
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .padding(end = 16.dp)
                )
            }
        }


        // Make sure
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color(0xFFE6E1E5), shape = RoundedCornerShape(100.dp))
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(2.dp),
                modifier = Modifier.padding(vertical = 6.dp)
            ) {
                StuntionText(text = "Make sure", textStyle = Type.bodySmall())
                StuntionText(
                    text = "nominal you entered is correct",
                    textStyle = Type.bodySmall(),
                    color = PrimaryBlue
                )
            }
        }

        // Anonymous
        StuntionText(
            text = "Hide my name (Anonymous)",
            textStyle = Type.bodyMedium(),
            color = LightGray
        )
    }
}