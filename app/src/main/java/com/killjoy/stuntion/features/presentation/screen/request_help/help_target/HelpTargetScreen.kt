package com.killjoy.stuntion.features.presentation.screen.request_help.help_target

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Divider
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import com.killjoy.stuntion.features.presentation.screen.request_help.RequestHelpViewModel
import com.killjoy.stuntion.features.presentation.utils.components.StuntionBasicTextField
import com.killjoy.stuntion.features.presentation.utils.components.StuntionText
import com.killjoy.stuntion.ui.theme.LightGray
import com.killjoy.stuntion.ui.theme.PrimaryBlue
import com.killjoy.stuntion.ui.theme.Type
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.DatePickerDefaults
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.LocalDate

@Composable
fun HelpTargetScreen() {

    val viewModel = hiltViewModel<RequestHelpViewModel>()
    val calendarState = rememberMaterialDialogState()

    MaterialDialog(
        shape = RoundedCornerShape(28.dp),
        dialogState = calendarState,
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true
        ),
        buttons = {
            positiveButton(text = "Ok", textStyle = TextStyle(color = PrimaryBlue)) {
                viewModel.dateState.value = viewModel.formattedEndDate.value
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
            viewModel.apply {
                isPickedAnEndDate.value = true
                endDate.value = it
                dateState.value = viewModel.formattedEndDate.value
            }
        }
    }

    Column {
        // Indicator
        Spacer(modifier = Modifier.height(16.dp))
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
                    val backgroundColor =
                        if (currentStepIndex == 1 || currentStepIndex == 2) PrimaryBlue else Color.White
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

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            StuntionText(
                text = "Help Targets",
                textStyle = Type.titleMedium(),
                modifier = Modifier.padding(vertical = 24.dp)
            )
            // Name
            Row(modifier = Modifier.padding(bottom = 8.dp)) {
                StuntionText(
                    text = "Determine The Approximate Child's needs",
                    textStyle = Type.labelLarge()
                )
                StuntionText(text = " *", textStyle = Type.labelLarge(), color = Color.Red)
            }
            StuntionBasicTextField(
                placeHolder = "Enter the child's needs",
                value = viewModel.foodState.value.toString(),
                onValueChange = {
                    viewModel.isFoodFieldClicked.value = true
                    viewModel.foodState.value = it.toIntOrNull() ?: 0
                },
                shape = RoundedCornerShape(100.dp),
                singleLine = true,
                isError = !viewModel.isValidFood.value,
                showWarningMessage = !viewModel.isValidFood.value,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                warningMessage = "Field could not be empty.",
                modifier = Modifier.fillMaxWidth()
            )


            // Phone
            Spacer(modifier = Modifier.height(16.dp))
            Row(modifier = Modifier.padding(bottom = 8.dp)) {
                StuntionText(
                    text = "Determine the estimated cost of the child's needs",
                    textStyle = Type.labelLarge()
                )
                StuntionText(text = " *", textStyle = Type.labelLarge(), color = Color.Red)
            }
            StuntionBasicTextField(
                placeHolder = "Enter the cost of the needs",
                value = viewModel.costState.value.toString(),
                onValueChange = {
                    viewModel.isCostFieldClicked.value = true
                    viewModel.costState.value = it.toIntOrNull() ?: 0
                },
                leadingIcon = {
                    StuntionText(
                        text = "IDR",
                        textStyle = Type.titleMedium(),
                        modifier = Modifier.padding(start = 18.dp, end = 4.dp)
                    )
                },
                shape = RoundedCornerShape(100.dp),
                singleLine = true,
                isError = !viewModel.isValidCost.value,
                showWarningMessage = !viewModel.isValidCost.value,
                warningMessage = "Field could not be empty.",
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )


            // Duration
            Spacer(modifier = Modifier.height(16.dp))
            Row(modifier = Modifier.padding(bottom = 8.dp)) {
                StuntionText(
                    text = "Determine how long a request for help lasts ",
                    textStyle = Type.labelLarge()
                )
                StuntionText(text = " *", textStyle = Type.labelLarge(), color = Color.Red)
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .width((LocalConfiguration.current.screenWidthDp / 2.4).dp)
                        .border(
                            width = 1.dp,
                            color = LightGray,
                            shape = RoundedCornerShape(100.dp)
                        )
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        RadioButton(
                            selected = viewModel.selectedDuration.value == viewModel.listOfDuration[0],
                            onClick = {
                                viewModel.selectedDuration.value = viewModel.listOfDuration[0]
                            },
                            colors = RadioButtonDefaults.colors(PrimaryBlue)
                        )
                        StuntionText(
                            text = viewModel.listOfDuration[0],
                            textStyle = Type.bodyLarge()
                        )
                    }
                }

                Box(
                    modifier = Modifier
                        .width((LocalConfiguration.current.screenWidthDp / 2.4).dp)
                        .border(
                            width = 1.dp,
                            color = LightGray,
                            shape = RoundedCornerShape(100.dp)
                        )
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        RadioButton(
                            selected = viewModel.selectedDuration.value == viewModel.listOfDuration[1],
                            onClick = {
                                viewModel.selectedDuration.value = viewModel.listOfDuration[1]
                            },
                            colors = RadioButtonDefaults.colors(PrimaryBlue)
                        )
                        StuntionText(
                            text = viewModel.listOfDuration[1],
                            textStyle = Type.bodyLarge()
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .width((LocalConfiguration.current.screenWidthDp / 2.4).dp)
                        .border(
                            width = 1.dp,
                            color = LightGray,
                            shape = RoundedCornerShape(100.dp)
                        )
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        RadioButton(
                            selected = viewModel.selectedDuration.value == viewModel.listOfDuration[2],
                            onClick = {
                                viewModel.selectedDuration.value = viewModel.listOfDuration[2]
                            },
                            colors = RadioButtonDefaults.colors(PrimaryBlue)
                        )
                        StuntionText(
                            text = viewModel.listOfDuration[2],
                            textStyle = Type.bodyLarge()
                        )
                    }
                }

                Box(
                    modifier = Modifier
                        .width((LocalConfiguration.current.screenWidthDp / 2.4).dp)
                        .border(
                            width = 1.dp,
                            color = LightGray,
                            shape = RoundedCornerShape(100.dp)
                        )
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        RadioButton(
                            selected = viewModel.selectedDuration.value == viewModel.listOfDuration[3],
                            onClick = {
                                viewModel.selectedDuration.value = viewModel.listOfDuration[3]
                                calendarState.show()
                            },
                            colors = RadioButtonDefaults.colors(PrimaryBlue)
                        )
                        StuntionText(
                            text = viewModel.listOfDuration[3],
                            textStyle = Type.bodyLarge()
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}