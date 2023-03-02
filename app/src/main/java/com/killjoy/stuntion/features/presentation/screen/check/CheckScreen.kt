package com.killjoy.stuntion.features.presentation.screen.check

import android.annotation.SuppressLint
import android.net.Uri
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.gson.Gson
import com.killjoy.stuntion.R
import com.killjoy.stuntion.features.domain.model.child.Child
import com.killjoy.stuntion.features.presentation.navigation.BottomNavigationBar
import com.killjoy.stuntion.features.presentation.utils.Constants
import com.killjoy.stuntion.features.presentation.utils.Screen
import com.killjoy.stuntion.features.presentation.utils.components.StuntionBasicTextField
import com.killjoy.stuntion.features.presentation.utils.components.StuntionButton
import com.killjoy.stuntion.features.presentation.utils.components.StuntionSegmentedControl
import com.killjoy.stuntion.ui.stuntionUI.StuntionText
import com.killjoy.stuntion.ui.theme.PrimaryBlue
import com.killjoy.stuntion.ui.theme.Type
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.DatePickerDefaults
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CheckScreen(navController: NavController) {
    val systemUiController = rememberSystemUiController()
    systemUiController.apply {
        setStatusBarColor(color = Color.Transparent, darkIcons = true)
        setNavigationBarColor(color = Color.White, darkIcons = true)
    }
    val calendarState = rememberMaterialDialogState()
    val viewModel = hiltViewModel<CheckViewModel>()
    val coroutineScope = rememberCoroutineScope()
    val pickedDate = remember {
        mutableStateOf(LocalDate.now())
    }
    val formattedDate = remember {
        derivedStateOf {
            DateTimeFormatter.ofPattern("MM/dd/yyy").format(pickedDate.value)
        }
    }

    MaterialDialog(
        shape = RoundedCornerShape(28.dp),
        dialogState = calendarState,
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true
        ),
        buttons = {
            positiveButton(text = "Ok", textStyle = TextStyle(color = PrimaryBlue)) {
                viewModel.dateState.value = formattedDate.value
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
            pickedDate.value = it
            viewModel.dateState.value = formattedDate.value
        }
    }

    Scaffold(
        bottomBar = { BottomNavigationBar(navController = navController) },
        modifier = Modifier.padding(bottom = (LocalConfiguration.current.screenHeightDp / 17).dp)
    ) {
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
                    .padding(start = 16.dp, top = 48.dp, end = 16.dp)
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
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Color.White, RoundedCornerShape(topStart = 48.dp, topEnd = 48.dp)
                    )
                    .padding(start = 16.dp, end = 16.dp, bottom = 48.dp)
            ) {
                Column(
                    modifier = Modifier
                        .matchParentSize()
                        .verticalScroll(rememberScrollState())
                        .padding(vertical = 24.dp)
                ) {

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

                    // Name
                    Spacer(modifier = Modifier.height(16.dp))
                    StuntionText(
                        text = "Child Name",
                        textStyle = Type.titleMedium(),
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    StuntionBasicTextField(
                        placeHolder = "Enter your child name",
                        value = viewModel.nameState.value,
                        onValueChange = {
                            viewModel.apply {
                                isNameFieldClicked.value = true
                                nameState.value = it
                            }
                        },
                        singleLine = true,
                        isError = !viewModel.isNameValid.value,
                        showWarningMessage = !viewModel.isNameValid.value,
                        warningMessage = "Field cannot be empty",
                        modifier = Modifier.fillMaxWidth()
                    )

                    // Date
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(modifier = Modifier.padding(bottom = 8.dp)) {
                        StuntionText(
                            text = "Date of birth ",
                            textStyle = Type.titleMedium(),
                        )
                        StuntionText(
                            text = "*",
                            textStyle = Type.titleMedium(),
                            color = Color.Red,
                        )
                    }

                    StuntionBasicTextField(
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
                        isError = !viewModel.isDateValid.value,
                        showWarningMessage = !viewModel.isDateValid.value,
                        warningMessage = "Field cannot be empty",
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                calendarState.show()
                            }
                    )

                    // Height and weight
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        // Height
                        Column {
                            Row(modifier = Modifier.padding(bottom = 8.dp)) {
                                StuntionText(
                                    text = "Height (cm) ",
                                    textStyle = Type.titleMedium(),
                                )
                                StuntionText(
                                    text = "*",
                                    textStyle = Type.titleMedium(),
                                    color = Color.Red,
                                )
                            }
                            StuntionBasicTextField(
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
                                isError = !viewModel.isHeightValid.value,
                                showWarningMessage = !viewModel.isHeightValid.value,
                                warningMessage = "Field cannot be empty",
                                modifier = Modifier.width(170.dp)
                            )
                        }
                        // Weight
                        Column {
                            Row(modifier = Modifier.padding(bottom = 8.dp)) {
                                StuntionText(
                                    text = "Weight (kg) ",
                                    textStyle = Type.titleMedium(),
                                )
                                StuntionText(
                                    text = "*",
                                    textStyle = Type.titleMedium(),
                                    color = Color.Red,
                                )
                            }
                            StuntionBasicTextField(
                                placeHolder = "Enter weight",
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
                                isError = !viewModel.isWeightValid.value,
                                showWarningMessage = !viewModel.isWeightValid.value,
                                warningMessage = "Field cannot be empty",
                                modifier = Modifier.width(170.dp)
                            )
                        }
                    }

                    // Button
                    Spacer(modifier = Modifier.height(8.dp))
                    Box(
                        contentAlignment = Alignment.Center, modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                    ) {
                        StuntionButton(
                            onClick = {
                                val child = Child(
                                    name = viewModel.nameState.value,
                                    gender = viewModel.genderState.value,
                                    birthDate = viewModel.dateState.value,
                                    height = viewModel.heightState.value.toDouble(),
                                    weight = viewModel.weightState.value.toDouble()
                                )
                                val childJson = Uri.encode(Gson().toJson(child))
                                navController.navigate("${Screen.QuestionScreen.route}/$childJson")
                            },
                            modifier = Modifier.width(180.dp),
                            enabled = viewModel.isFormNotValid.value
                        ) {
                            StuntionText(
                                text = "Calculate",
                                color = Color.White,
                                textStyle = Type.labelLarge()
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun CalculatorPrev() {
    CheckScreen(navController = rememberNavController())
}