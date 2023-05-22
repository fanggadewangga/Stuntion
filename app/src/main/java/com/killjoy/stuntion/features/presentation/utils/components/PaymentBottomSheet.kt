package com.killjoy.stuntion.features.presentation.utils.components

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
import com.killjoy.stuntion.features.presentation.screen.home.HomePaymentSharedViewModel
import com.killjoy.stuntion.ui.theme.LightGray
import com.killjoy.stuntion.ui.theme.Type

@Composable
fun PaymentBottomSheet(
    modifier: Modifier = Modifier,
    sharedViewModel: HomePaymentSharedViewModel,
    onMethodClicked: () -> Unit,
    navigateToPaymentInstruction: () -> Unit,
) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp), modifier = modifier) {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth()) {
            // Enter nominal text
            StuntionText(
                text = "Enter Nominal",
                textStyle = Type.titleMedium(),
                modifier = Modifier.padding(bottom = 12.dp, top = 16.dp)
            )
        }
        // Text Field
        StuntionBasicTextField(
            placeHolder = "0",
            value = sharedViewModel.selectedNominal.value.toInt().toString(),
            isWithBorder = false,
            textStyle = Type.titleLarge(),
            onValueChange = {
                sharedViewModel.selectedNominal.value = it.toDouble()
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
            isError = false,
            showWarningMessage = false,
            warningMessage = "Field could not be empty.",
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color(0xFFE6E1E5), shape = RoundedCornerShape(100.dp))
        )

        // Description text
        StuntionText(
            text = "Fill your support wallet according to your needs ",
            textStyle = Type.bodySmall(),
            color = Color.LightGray,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Nominal
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
            for (i in 0..2) {
                WalletNominalSelector(
                    selected = sharedViewModel.selectedNominal.value.toInt() == sharedViewModel.listOfNominal[i],
                    nominal = sharedViewModel.listOfNominal[i].toString(),
                    onSelected = {
                        sharedViewModel.selectedNominal.value =
                            sharedViewModel.listOfNominal[i].toDouble()
                    })
            }
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        ) {
            for (i in 3..5) {
                WalletNominalSelector(
                    selected = sharedViewModel.selectedNominal.value.toInt() == sharedViewModel.listOfNominal[i],
                    nominal = sharedViewModel.listOfNominal[i].toString(),
                    onSelected = {
                        sharedViewModel.selectedNominal.value =
                            sharedViewModel.listOfNominal[i].toDouble()
                    })
            }
        }

        // Payment method text
        StuntionText(
            text = "Payment Method",
            textStyle = Type.titleMedium(),
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Payment method button
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
            if (sharedViewModel.isHasSelectedAPayment.value) {
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
                            model = sharedViewModel.selectedPaymentImageUrlState.value,
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

        // Button
        StuntionButton(
            onClick = {
                navigateToPaymentInstruction()
            }, modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp)
        ) {
            StuntionText(
                text = "Fill Your Support Wallet",
                color = Color.White,
                textStyle = Type.labelLarge()
            )
        }
    }
}