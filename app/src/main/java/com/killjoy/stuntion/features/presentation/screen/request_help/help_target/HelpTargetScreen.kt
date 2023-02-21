package com.killjoy.stuntion.features.presentation.screen.request_help.help_target

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.killjoy.stuntion.features.presentation.utils.components.StuntionBasicTextField
import com.killjoy.stuntion.ui.stuntionUI.StuntionText
import com.killjoy.stuntion.ui.theme.PrimaryBlue
import com.killjoy.stuntion.ui.theme.Type

@Composable
fun HelpTargetScreen(navController: NavController) {

    val viewModel = hiltViewModel<HelpTargetViewModel>()

    Column{
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
                    text = "Determine The Approximate Food Needed",
                    textStyle = Type.labelLarge()
                )
                StuntionText(text = " *", textStyle = Type.labelLarge(), color = Color.Red)
            }
            StuntionBasicTextField(
                placeHolder = "Enter the need for additional food",
                value = viewModel.foodState.value,
                onValueChange = {
                    viewModel.isFoodFieldClicked.value = true
                    viewModel.foodState.value = it
                },
                shape = RoundedCornerShape(100.dp),
                singleLine = true,
                isError = !viewModel.isValidFood.value,
                showWarningMessage = !viewModel.isValidFood.value,
                warningMessage = "Field could not be empty.",
                modifier = Modifier.fillMaxWidth()
            )


            // Phone
            Spacer(modifier = Modifier.height(16.dp))
            Row(modifier = Modifier.padding(bottom = 8.dp)) {
                StuntionText(
                    text = "Determine the estimated cost of the food needed",
                    textStyle = Type.labelLarge()
                )
                StuntionText(text = " *", textStyle = Type.labelLarge(), color = Color.Red)
            }
            StuntionBasicTextField(
                placeHolder = "Enter the cost of the food",
                value = viewModel.costState.value,
                onValueChange = {
                    viewModel.isCostFieldClicked.value = true
                    viewModel.costState.value = it
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


            // Phone
            Spacer(modifier = Modifier.height(16.dp))
            Row(modifier = Modifier.padding(bottom = 8.dp)) {
                StuntionText(
                    text = "Determine how long a request for help lasts ",
                    textStyle = Type.labelLarge()
                )
                StuntionText(text = " *", textStyle = Type.labelLarge(), color = Color.Red)
                /* TODO : RADIO BUTTON */
            }
        }
    }
}

@Preview
@Composable
fun HelpTargetPreview() {
    HelpTargetScreen(navController = rememberNavController())
}