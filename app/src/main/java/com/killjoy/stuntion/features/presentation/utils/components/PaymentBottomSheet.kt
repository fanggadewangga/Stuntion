package com.killjoy.stuntion.features.presentation.utils.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.killjoy.stuntion.ui.theme.LightGray
import com.killjoy.stuntion.ui.theme.Type

@Composable
fun PaymentBottomSheet(
    modifier: Modifier = Modifier,
    isHasSelected: Boolean,
    paymentImageUrl: String,
    paymentName: String,
    onMethodClicked: () -> Unit,
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
            placeHolder = "Enter the cost of the food",
            value = "",
            isWithBorder = false,
            textStyle = Type.bodyMedium(TextAlign.Center),
            onValueChange = {

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
            WalletNominalSelector(selected = true, nominal = "50.000")
            WalletNominalSelector(selected = false, nominal = "100.000")
            WalletNominalSelector(selected = false, nominal = "150.000")
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        ) {
            WalletNominalSelector(selected = true, nominal = "250.000")
            WalletNominalSelector(selected = false, nominal = "500.000")
            WalletNominalSelector(selected = false, nominal = "750.000")
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
                .clickable {
                    onMethodClicked()
                }
        ) {
            if (isHasSelected) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth().padding(start = 24.dp)
                ) {
                    // Logo and name
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        AsyncImage(
                            model = paymentImageUrl,
                            contentDescription = "Payment method logo",
                            modifier = Modifier.size(64.dp)
                        )
                        StuntionText(
                            text = paymentName,
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
    }
}