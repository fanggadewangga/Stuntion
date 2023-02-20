package com.killjoy.stuntion.features.presentation.screen.request_help.personal_data

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.killjoy.stuntion.features.presentation.screen.request_help.detail_information.DetailInformationScreen
import com.killjoy.stuntion.features.presentation.screen.request_help.title.TitleScreen
import com.killjoy.stuntion.features.presentation.utils.components.StuntionTextField
import com.killjoy.stuntion.ui.stuntionUI.StuntionText
import com.killjoy.stuntion.ui.theme.PrimaryBlue
import com.killjoy.stuntion.ui.theme.Type

@Composable
fun PersonalDataScreen(navController: NavController) {

    val viewModel = hiltViewModel<PersonalDataViewModel>()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        StuntionText(text = "Personal Data", textStyle = Type.titleMedium())
        Spacer(modifier = Modifier.height(24.dp))

        // Name
        Row {
            StuntionText(text = "Name According To E-KTP", textStyle = Type.labelLarge())
            StuntionText(text = " *", textStyle = Type.labelLarge(), color = Color.Red)
        }
        StuntionTextField(
            placeHolder = "Enter your name according to E-KTP",
            value = viewModel.nameState.value,
            onValueChange = {
                viewModel.isNameFieldClicked.value = true
                viewModel.nameState.value = it
            },
            shape = RoundedCornerShape(100.dp),
            singleLine = true,
            focusedIndicatorColor = PrimaryBlue,
            isError = !viewModel.isValidName.value,
            showWarningMessage = !viewModel.isValidName.value,
            warningMessage = "Field could not be empty.",
            modifier = Modifier.fillMaxWidth()
        )

        // Phone
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            StuntionText(text = "Phone Number", textStyle = Type.labelLarge())
            StuntionText(text = " *", textStyle = Type.labelLarge(), color = Color.Red)
        }
        StuntionTextField(
            placeHolder = "Make sure the number is active for SMS",
            label = "",
            value = viewModel.phoneState.value,
            onValueChange = {
                viewModel.isPhoneFieldClicked.value = true
                viewModel.phoneState.value = it
            },
            shape = RoundedCornerShape(100.dp),
            singleLine = true,
            focusedIndicatorColor = PrimaryBlue,
            isError = !viewModel.isValidPhone.value,
            showWarningMessage = !viewModel.isValidPhone.value,
            warningMessage = "Field could not be empty.",
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        // Address
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            StuntionText(text = "Address", textStyle = Type.labelLarge())
            StuntionText(text = " *", textStyle = Type.labelLarge(), color = Color.Red)
        }
        StuntionTextField(
            placeHolder = "Enter your current residential address",
            label = "",
            value = viewModel.addressState.value,
            onValueChange = {
                viewModel.isAddressFieldClicked.value = true
                viewModel.addressState.value = it
            },
            shape = RoundedCornerShape(100.dp),
            singleLine = true,
            focusedIndicatorColor = PrimaryBlue,
            isError = !viewModel.isValidAddress.value,
            showWarningMessage = !viewModel.isValidAddress.value,
            warningMessage = "Field could not be empty.",
            modifier = Modifier.fillMaxWidth()
        )

        // Job
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            StuntionText(text = "Current Job", textStyle = Type.labelLarge())
            StuntionText(text = " *", textStyle = Type.labelLarge(), color = Color.Red)
        }
        StuntionTextField(
            placeHolder = "Example: Private employees, laborers",
            label = "",
            value = viewModel.jobState.value,
            onValueChange = {
                viewModel.isJobFieldClicked.value = true
                viewModel.jobState.value = it
            },
            shape = RoundedCornerShape(100.dp),
            singleLine = true,
            focusedIndicatorColor = PrimaryBlue,
            isError = !viewModel.isValidJob.value,
            showWarningMessage = !viewModel.isValidJob.value,
            warningMessage = "Field could not be empty.",
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview
@Composable
fun PersonalDataScreenPreview() {
    DetailInformationScreen(navController = rememberNavController())
}