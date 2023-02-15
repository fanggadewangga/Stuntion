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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.killjoy.stuntion.R
import com.killjoy.stuntion.features.presentation.utils.Constants.GENDER
import com.killjoy.stuntion.features.presentation.utils.Screen
import com.killjoy.stuntion.features.presentation.utils.components.StuntionButton
import com.killjoy.stuntion.features.presentation.utils.components.StuntionTextField
import com.killjoy.stuntion.ui.stuntionUI.StuntionText
import com.killjoy.stuntion.ui.theme.PrimaryBlue
import com.killjoy.stuntion.ui.theme.Type
import com.maxkeppeker.sheets.core.models.base.rememberSheetState
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarConfig
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import java.time.format.DateTimeFormatter

@Composable
fun GeneralInformationScreen(navController: NavController) {
    val calendarState = rememberSheetState()
    val viewModel = hiltViewModel<GeneralInformationViewModel>()

    CalendarDialog(
        state = calendarState,
        config = CalendarConfig(
            monthSelection = true,
            yearSelection = true
        ),
        selection = CalendarSelection.Date { date ->
            val formatted = DateTimeFormatter.ofPattern("MM/dd/yyy").format(date)
            viewModel.dateState.value = formatted
        }
    )

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
                modifier = Modifier.size(180.dp)
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
                    textStyle = Type.titleMedium()
                )
                StuntionTextField(
                    placeHolder = "Enter name",
                    value = viewModel.nameState.value,
                    onValueChange = {
                        viewModel.apply {
                            isNameFieldClicked.value = true
                            nameState.value = it
                        }
                    },
                    shape = RoundedCornerShape(100.dp),
                    singleLine = true,
                    focusedIndicatorColor = PrimaryBlue,
                    isError = viewModel.isNameValid.value,
                    showWarningMessage = viewModel.isNameValid.value,
                    warningMessage = "Field cannot be empty",
                    modifier = Modifier.fillMaxWidth()
                )

                // Date
                Spacer(modifier = Modifier.height(16.dp))
                StuntionText(
                    text = "Date of birth",
                    textStyle = Type.titleMedium()
                )
                StuntionTextField(
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
                    shape = RoundedCornerShape(100.dp),
                    singleLine = true,
                    focusedIndicatorColor = PrimaryBlue,
                    isError = viewModel.isDateValid.value,
                    showWarningMessage = viewModel.isDateValid.value,
                    warningMessage = "Field cannot be empty",
                    modifier = Modifier.fillMaxWidth()
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
                    onClick = { }, modifier = Modifier.fillMaxWidth()
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