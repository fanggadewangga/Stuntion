package com.killjoy.stuntion.features.presentation.screen.request_help.personal_data

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.killjoy.stuntion.features.presentation.screen.request_help.RequestHelpViewModel
import com.killjoy.stuntion.features.presentation.utils.components.StuntionBasicTextField
import com.killjoy.stuntion.ui.stuntionUI.StuntionText
import com.killjoy.stuntion.ui.theme.PrimaryBlue
import com.killjoy.stuntion.ui.theme.Type

@Composable
fun PersonalDataScreen() {

    val viewModel = hiltViewModel<RequestHelpViewModel>()

    Column(Modifier.verticalScroll(rememberScrollState())) {
        Spacer(modifier = Modifier.height(16.dp))

        // Indicator
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp)
        )
        {
            Divider(
                thickness = 1.dp,
                color = PrimaryBlue,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = (LocalConfiguration.current.screenWidthDp / 8).dp,
                        bottom = 16.dp
                    )
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                viewModel.listOfStep.forEachIndexed { index, step ->
                    val currentStepIndex = index + 1
                    val backgroundColor = if (currentStepIndex == 1) PrimaryBlue else Color.White
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .size(40.dp)
                                .border(width = 0.5.dp, color = PrimaryBlue, shape = CircleShape)
                                .background(color = backgroundColor, shape = CircleShape)
                        ) {
                            StuntionText(
                                text = currentStepIndex.toString(),
                                textStyle = Type.titleMedium(),
                                color = if (backgroundColor == PrimaryBlue) Color.White else PrimaryBlue
                            )
                        }
                        StuntionText(
                            text = step,
                            textStyle = Type.labelMedium(),
                            color = PrimaryBlue,
                        )
                    }
                }
            }
        }

        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
            StuntionText(
                text = "Personal Data",
                textStyle = Type.titleMedium(),
                modifier = Modifier.padding(vertical = 24.dp)
            )

            // Name
            Row(modifier = Modifier.padding(bottom = 8.dp)) {
                StuntionText(text = "Name According To E-KTP", textStyle = Type.labelLarge())
                StuntionText(text = " *", textStyle = Type.labelLarge(), color = Color.Red)
            }
            StuntionBasicTextField(
                placeHolder = "Enter your name according to E-KTP",
                value = viewModel.nameState.value,
                onValueChange = {
                    viewModel.isNameFieldClicked.value = true
                    viewModel.nameState.value = it
                },
                shape = RoundedCornerShape(100.dp),
                singleLine = true,
                isError = !viewModel.isValidName.value,
                showWarningMessage = !viewModel.isValidName.value,
                warningMessage = "Field could not be empty.",
                modifier = Modifier.fillMaxWidth()
            )

            // Phone
            Spacer(modifier = Modifier.height(16.dp))
            Row(modifier = Modifier.padding(bottom = 8.dp)) {
                StuntionText(text = "Phone Number", textStyle = Type.labelLarge())
                StuntionText(text = " *", textStyle = Type.labelLarge(), color = Color.Red)
            }
            StuntionBasicTextField(
                placeHolder = "Make sure the number is active for SMS",
                value = viewModel.phoneState.value,
                onValueChange = {
                    viewModel.isPhoneFieldClicked.value = true
                    viewModel.phoneState.value = it
                },
                shape = RoundedCornerShape(100.dp),
                singleLine = true,
                isError = !viewModel.isValidPhone.value,
                showWarningMessage = !viewModel.isValidPhone.value,
                warningMessage = "Field could not be empty.",
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )


            // Address
            Spacer(modifier = Modifier.height(16.dp))
            Row(modifier = Modifier.padding(bottom = 8.dp)) {
                StuntionText(text = "Address", textStyle = Type.labelLarge())
                StuntionText(text = " *", textStyle = Type.labelLarge(), color = Color.Red)
            }
            StuntionBasicTextField(
                placeHolder = "Enter your current residential address",
                value = viewModel.addressState.value,
                onValueChange = {
                    viewModel.isAddressFieldClicked.value = true
                    viewModel.addressState.value = it
                },
                shape = RoundedCornerShape(100.dp),
                singleLine = true,
                isError = !viewModel.isValidAddress.value,
                showWarningMessage = !viewModel.isValidAddress.value,
                warningMessage = "Field could not be empty.",
                modifier = Modifier.fillMaxWidth()
            )


            // Job
            Spacer(modifier = Modifier.height(16.dp))
            Row(modifier = Modifier.padding(bottom = 8.dp)) {
                StuntionText(text = "Current Job", textStyle = Type.labelLarge())
                StuntionText(text = " *", textStyle = Type.labelLarge(), color = Color.Red)
            }
            StuntionBasicTextField(
                placeHolder = "Example: Private employees, laborers",
                value = viewModel.jobState.value,
                onValueChange = {
                    viewModel.isJobFieldClicked.value = true
                    viewModel.jobState.value = it
                },
                shape = RoundedCornerShape(100.dp),
                singleLine = true,
                isError = !viewModel.isValidJob.value,
                showWarningMessage = !viewModel.isValidJob.value,
                warningMessage = "Field could not be empty.",
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}