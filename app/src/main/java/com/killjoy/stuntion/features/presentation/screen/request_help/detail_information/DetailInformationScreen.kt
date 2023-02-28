package com.killjoy.stuntion.features.presentation.screen.request_help.detail_information

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
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
fun DetailInformationScreen(navController: NavController) {
    val viewModel = hiltViewModel<DetailInformationViewModel>()

    Column{

        // Indicator
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 16.dp)
        )
        {
            Divider(
                thickness = 1.dp,
                color = PrimaryBlue,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        end = (LocalConfiguration.current.screenWidthDp / 8).dp,
                        bottom = 16.dp
                    )
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                viewModel.listOfStep.forEachIndexed { index, step ->
                    val currentStepIndex = index + 2
                    val backgroundColor = if (currentStepIndex == 5) Color.White else PrimaryBlue
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
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            StuntionText(
                text = "Detail Information",
                textStyle = Type.titleMedium(),
                modifier = Modifier.padding(top = 32.dp)
            )
            Spacer(modifier = Modifier.height(24.dp))

            // Title
            Row(modifier = Modifier.padding(bottom = 8.dp)) {
                StuntionText(
                    text = "Write a Story About a Request For Help",
                    textStyle = Type.labelLarge()
                )
                StuntionText(text = " *", textStyle = Type.labelLarge(), color = Color.Red)
            }
            StuntionBasicTextField(
                placeHolder = "Write down some request reasons",
                textFieldHeight = 160.dp,
                value = viewModel.storyState.value,
                onValueChange = {
                    viewModel.isStoryFieldClicked.value = true
                    viewModel.storyState.value = it
                },
                shape = RoundedCornerShape(32.dp),
                singleLine = true,
                isError = !viewModel.isValidStory.value,
                showWarningMessage = !viewModel.isValidStory.value,
                warningMessage = "Field could not be empty.",
                verticalAlignment = Alignment.Top,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Preview
@Composable
fun DetailInformationPreview() {
    DetailInformationScreen(navController = rememberNavController())
}