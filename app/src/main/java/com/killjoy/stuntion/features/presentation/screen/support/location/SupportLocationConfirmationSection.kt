package com.killjoy.stuntion.features.presentation.screen.support.location

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.killjoy.stuntion.R
import com.killjoy.stuntion.features.presentation.screen.support.detail.SupportDetailViewModel
import com.killjoy.stuntion.features.presentation.utils.components.StuntionBasicTextField
import com.killjoy.stuntion.features.presentation.utils.components.StuntionText
import com.killjoy.stuntion.ui.theme.LightGray
import com.killjoy.stuntion.ui.theme.Type

@Composable
fun SupportLocationConfirmationSection(
    modifier: Modifier = Modifier,
    destinationAddress: String = "",
    viewModel: SupportDetailViewModel,
) {

    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = modifier
            .padding(top = 16.dp)
    ) {

        StuntionText(
            text = "Location Confirmation",
            textStyle = Type.titleMedium(),
            modifier = Modifier
                .padding(bottom = 16.dp)
                .align(CenterHorizontally)
        )

        // Destination
        StuntionText(text = "Destination", textStyle = Type.labelLarge(), color = LightGray)
        StuntionBasicTextField(
            placeHolder = "",
            value = destinationAddress,
            isWithBorder = false,
            enabled = false,
            onValueChange = {},
            leadingIcon = {
                AsyncImage(
                    model = R.drawable.ic_red_marker,
                    contentDescription = "Destination icon",
                    modifier = Modifier.size(24.dp)
                )
            },
            shape = RoundedCornerShape(100.dp),
            singleLine = true,
            textStyle = Type.bodyLarge(),
            isError = false,
            showWarningMessage = false,
            warningMessage = "Field could not be empty.",
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color(0xFFE6E1E5), shape = RoundedCornerShape(100.dp))
        )

        // Current location
        StuntionText(text = "Your current location", textStyle = Type.labelLarge(), color = LightGray)
        StuntionBasicTextField(
            placeHolder = "",
            value = viewModel.userAddress.value,
            isWithBorder = false,
            onValueChange = {
                viewModel.apply {
                    isUserAddressFieldClicked.value = true
                    userAddress.value = it
                }
            },
            leadingIcon = {
                AsyncImage(
                    model = R.drawable.ic_blue_marker,
                    contentDescription = "Current location icon",
                    modifier = Modifier.size(24.dp)
                )
            },
            shape = RoundedCornerShape(100.dp),
            singleLine = true,
            textStyle = Type.bodyLarge(),
            isError = !viewModel.isUserAddressValid.value,
            showWarningMessage = !viewModel.isUserAddressValid.value,
            warningMessage = "Field could not be empty.",
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color(0xFFE6E1E5), shape = RoundedCornerShape(100.dp))
        )

        StuntionText(text = "Your current location", textStyle = Type.labelLarge(), color = LightGray)

        // Instruction
        Row(
            horizontalArrangement = Arrangement.spacedBy(2.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(
                start = 16.dp,
                end = 16.dp,
                bottom = (LocalConfiguration.current.screenHeightDp / 18).dp
            )
        ) {
            StuntionText(text = "*", color = LightGray, textStyle = Type.bodySmall())
            StuntionText(
                text = "Make sure the location you enter is correct",
                color = LightGray,
                textStyle = Type.bodySmall()
            )
        }

    }
}