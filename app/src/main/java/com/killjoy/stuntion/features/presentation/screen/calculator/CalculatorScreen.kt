package com.killjoy.stuntion.features.presentation.screen.calculator

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.killjoy.stuntion.R
import com.killjoy.stuntion.features.presentation.utils.Constants
import com.killjoy.stuntion.features.presentation.utils.components.StuntionButton
import com.killjoy.stuntion.features.presentation.utils.components.StuntionSegmentedControl
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
fun CalculatorScreen(navController: NavController) {
    val calendarState = rememberSheetState()
    val viewModel = hiltViewModel<CalculatorViewModel>()

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
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 32.dp, end = 16.dp)
        ) {
            StuntionText(
                text = "Stunting Prevention",
                color = Color.White,
                textStyle = Type.titleLarge()
            )
            Image(
                painter = painterResource(id = R.drawable.ic_measure_tutorial),
                contentDescription = "Measure tutorial icon",
                modifier = Modifier
                    .size(24.dp)
                    .clickable {

                    }
            )
        }
        StuntionText(
            text = "Please enter your baby's data for stunting checking!",
            color = Color.White,
            textStyle = Type.bodySmall(),
            modifier = Modifier.padding(start = 16.dp)
        )

        // Illustration image
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            Image(
                painter = painterResource(id = R.drawable.ic_baby),
                contentDescription = "General information icon",
                modifier = Modifier.size(220.dp)
            )
        }

        // Box
        Spacer(modifier = Modifier.height(8.dp))
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Color.White, RoundedCornerShape(topStart = 48.dp, topEnd = 48.dp)
                )
                .padding(horizontal = 16.dp, vertical = 24.dp)
        ) {
            Column(modifier = Modifier.matchParentSize()) {

                // Gender
                Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth()) {
                    StuntionSegmentedControl(
                        items = Constants.GENDER,
                        itemsIcon = listOf(R.drawable.ic_male, R.drawable.ic_female),
                        itemWidth = 155.dp,
                        cornerRadius = 100,
                        useFixedWidth = true,
                        onItemSelection = { index ->
                            viewModel.genderState.value = Constants.GENDER[index]
                        }
                    )
                }

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
                        }
                        ) {
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
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    focusedIndicatorColor = PrimaryBlue,
                    isError = viewModel.isDateValid.value,
                    showWarningMessage = viewModel.isDateValid.value,
                    warningMessage = "Field cannot be empty",
                    modifier = Modifier.fillMaxWidth()
                )

                // Height and weight
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    // Height
                    Column {
                        StuntionText(
                            text = "Height",
                            textStyle = Type.titleMedium()
                        )
                        StuntionTextField(
                            placeHolder = "Enter height",
                            value = viewModel.heightState.value,
                            onValueChange = {
                                viewModel.apply {
                                    isHeightFieldClicked.value = true
                                    heightState.value = it
                                }
                            },
                            shape = RoundedCornerShape(100.dp),
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            focusedIndicatorColor = PrimaryBlue,
                            isError = viewModel.isHeightValid.value,
                            showWarningMessage = viewModel.isHeightValid.value,
                            warningMessage = "Field cannot be empty",
                            modifier = Modifier.width(170.dp)
                        )
                    }
                    // Weight
                    Column {
                        StuntionText(
                            text = "Weight",
                            textStyle = Type.titleMedium()
                        )
                        StuntionTextField(
                            placeHolder = "Enter height",
                            value = viewModel.weightState.value,
                            onValueChange = {
                                viewModel.apply {
                                    isWeightFieldClicked.value = true
                                    weightState.value = it
                                }
                            },
                            shape = RoundedCornerShape(100.dp),
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            focusedIndicatorColor = PrimaryBlue,
                            isError = viewModel.isWeightValid.value,
                            showWarningMessage = viewModel.isWeightValid.value,
                            warningMessage = "Field cannot be empty",
                            modifier = Modifier.width(170.dp)
                        )
                    }
                }

                // Head and arm
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    // Head
                    Column {
                        StuntionText(
                            text = "Height Circumference",
                            textStyle = Type.titleMedium()
                        )
                        StuntionTextField(
                            placeHolder = "Enter head",
                            value = viewModel.headState.value,
                            onValueChange = {
                                viewModel.apply {
                                    isHeadFieldClicked.value = true
                                    headState.value = it
                                }
                            },
                            shape = RoundedCornerShape(100.dp),
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            focusedIndicatorColor = PrimaryBlue,
                            isError = viewModel.isHeadValid.value,
                            showWarningMessage = viewModel.isHeadValid.value,
                            warningMessage = "Field cannot be empty",
                            modifier = Modifier.width(170.dp)
                        )
                    }

                    // Arm
                    Column {
                        StuntionText(
                            text = "Arm Circumference",
                            textStyle = Type.titleMedium()
                        )
                        StuntionTextField(
                            placeHolder = "Enter arm",
                            value = viewModel.armState.value,
                            onValueChange = {
                                viewModel.apply {
                                    isArmFieldClicked.value = true
                                    armState.value = it
                                }
                            },
                            shape = RoundedCornerShape(100.dp),
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            focusedIndicatorColor = PrimaryBlue,
                            isError = viewModel.isArmValid.value,
                            showWarningMessage = viewModel.isArmValid.value,
                            warningMessage = "Field cannot be empty",
                            modifier = Modifier.width(170.dp)
                        )
                    }
                }

                // Button
                Spacer(modifier = Modifier.height(32.dp))
                Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth()) {
                    StuntionButton(
                        onClick = {

                        }, modifier = Modifier.width(180.dp)
                    ) {
                        StuntionText(
                            text = "Next", color = Color.White, textStyle = Type.labelLarge()
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun CalculatorPrev() {
    CalculatorScreen(navController = rememberNavController())
}