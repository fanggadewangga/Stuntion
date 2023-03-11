package com.killjoy.stuntion.features.presentation.screen.general_information

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.killjoy.stuntion.R
import com.killjoy.stuntion.features.data.util.Resource
import com.killjoy.stuntion.features.presentation.utils.Constants.GENDER
import com.killjoy.stuntion.features.presentation.utils.Screen
import com.killjoy.stuntion.features.presentation.utils.components.StuntionBasicTextField
import com.killjoy.stuntion.features.presentation.utils.components.StuntionButton
import com.killjoy.stuntion.ui.stuntionUI.StuntionText
import com.killjoy.stuntion.ui.theme.PrimaryBlue
import com.killjoy.stuntion.ui.theme.Type
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.DatePickerDefaults
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.LocalDate

@Composable
fun GeneralInformationScreen(navController: NavController) {
    val calendarState = rememberMaterialDialogState()
    val viewModel = hiltViewModel<GeneralInformationViewModel>()
    val userUpdateInfoState = viewModel.userState.collectAsState()

    /*when (userUpdateInfoState.value) {
        is Resource.Empty -> {}
        is Resource.Success -> {
            Log.d("Success", userUpdateInfoState.toString())
            navController.navigate(Screen.AvatarScreen.route)
        }
        is Resource.Error -> {}
        is Resource.Loading -> {
            Log.d("Loading", userUpdateInfoState.toString())
        }
    }*/

    MaterialDialog(
        shape = RoundedCornerShape(28.dp),
        dialogState = calendarState,
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true
        ),
        buttons = {
            positiveButton(text = "Ok", textStyle = TextStyle(color = PrimaryBlue)) {
                viewModel.dateState.value = viewModel.formattedDate.value
                calendarState.hide()
            }
            negativeButton(text = "Cancel", textStyle = TextStyle(color = PrimaryBlue)) {
                calendarState.hide()
            }
        }
    ) {
        datepicker(
            title = "Select date",
            initialDate = LocalDate.now(),
            waitForPositiveButton = true,
            colors = DatePickerDefaults.colors(
                headerBackgroundColor = PrimaryBlue,
                headerTextColor = Color.White,
                dateActiveBackgroundColor = PrimaryBlue,
                dateActiveTextColor = Color.White
            )
        ) {
            viewModel.pickedDate.value = it
            viewModel.dateState.value = viewModel.formattedDate.value
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(PrimaryBlue)
    ) {
        // General information image
        Spacer(modifier = Modifier.height(72.dp))
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            Image(
                painter = painterResource(id = R.drawable.ic_general_info),
                contentDescription = "General information icon",
                modifier = Modifier.size(200.dp)
            )
        }

        // Box
        Spacer(modifier = Modifier.height(32.dp))
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Color.White, RoundedCornerShape(topStart = 48.dp, topEnd = 48.dp)
                )
                .padding(horizontal = 16.dp, vertical = 24.dp)
        ) {
            Column(modifier = Modifier.matchParentSize()) {

                // General information
                StuntionText(text = "General Information", textStyle = Type.headlineLarge())

                // Please complete
                StuntionText(
                    text = "Please complete your personal data!",
                    textStyle = Type.bodyMedium(),
                    color = Color.Gray
                )

                // Name
                Spacer(modifier = Modifier.height(16.dp))
                StuntionText(
                    text = "Name",
                    textStyle = Type.titleMedium(),
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                StuntionBasicTextField(
                    placeHolder = "Enter name",
                    value = viewModel.nameState.value,
                    onValueChange = {
                        viewModel.apply {
                            isNameFieldClicked.value = true
                            nameState.value = it
                        }
                    },
                    singleLine = true,
                    isError = viewModel.isNameValid.value,
                    showWarningMessage = viewModel.isNameValid.value,
                    warningMessage = "Field cannot be empty",
                    modifier = Modifier.fillMaxWidth()
                )

                // Date
                Spacer(modifier = Modifier.height(16.dp))
                StuntionText(
                    text = "Date of birth",
                    textStyle = Type.titleMedium(),
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                StuntionBasicTextField(
                    trailingIcon = {
                        IconButton(onClick = {
                            calendarState.show()
                        }) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_calendar),
                                contentDescription = "Calendar icon",
                                modifier = Modifier
                                    .size(24.dp)
                            )
                        }
                    },
                    placeHolder = "mm/dd/yyyy",
                    value = viewModel.dateState.value,
                    onValueChange = {
                        viewModel.apply {
                            isDateFieldClicked.value = true
                            dateState.value = it
                        }
                    },
                    singleLine = true,
                    isError = viewModel.isDateValid.value,
                    showWarningMessage = viewModel.isDateValid.value,
                    warningMessage = "Field cannot be empty",
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            calendarState.show()
                        }
                )

                // Gender
                Spacer(modifier = Modifier.height(16.dp))
                StuntionText(
                    text = "Gender",
                    textStyle = Type.titleMedium()
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        selected = viewModel.selectedGender.value == GENDER[0],
                        onClick = { viewModel.selectedGender.value = GENDER[0] },
                        colors = RadioButtonDefaults.colors(PrimaryBlue)
                    )
                    StuntionText(
                        text = GENDER[0],
                        textStyle = Type.bodyLarge()
                    )

                    Spacer(modifier = Modifier.width(24.dp))
                    RadioButton(
                        selected = viewModel.selectedGender.value == GENDER[1],
                        onClick = { viewModel.selectedGender.value = GENDER[1] },
                        colors = RadioButtonDefaults.colors(PrimaryBlue)
                    )
                    StuntionText(
                        text = GENDER[1],
                        textStyle = Type.bodyLarge()
                    )
                }

                // Button
                Spacer(modifier = Modifier.height(16.dp))
                StuntionButton(
                    onClick = {
                        viewModel.updateUserGeneralInformation()
                    }, modifier = Modifier.fillMaxWidth()
                ) {
                    StuntionText(
                        text = "Confirm", color = Color.White, textStyle = Type.labelLarge()
                    )
                }


                // Skip
                Spacer(modifier = Modifier.height(24.dp))
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    StuntionText(
                        text = "Skip",
                        color = PrimaryBlue,
                        textStyle = Type.bodyMedium(),
                        modifier = Modifier.clickable { navController.navigate(Screen.AvatarScreen.route) }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun GeneralInformationPreview() {
    GeneralInformationScreen(navController = rememberNavController())
}