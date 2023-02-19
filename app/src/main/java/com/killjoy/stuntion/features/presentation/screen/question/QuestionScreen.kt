package com.killjoy.stuntion.features.presentation.screen.question

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.killjoy.stuntion.R
import com.killjoy.stuntion.features.presentation.utils.components.MultipleChoiceItem
import com.killjoy.stuntion.features.presentation.utils.components.StuntionButton
import com.killjoy.stuntion.ui.stuntionUI.StuntionText
import com.killjoy.stuntion.ui.theme.PrimaryBlue
import com.killjoy.stuntion.ui.theme.Type

@Composable
fun QuestionScreen(navController: NavController) {

    val viewModel = hiltViewModel<QuestionViewModel>()
    val listOfQuestions = viewModel.listOfQuestion
    val currentStep = remember {
        mutableStateOf(1)
    }
    val question = listOfQuestions[currentStep.value - 1].question
    val isMultipleChoice = listOfQuestions[currentStep.value - 1].isMultipleChoices
    val listOfAnswer = listOfQuestions[currentStep.value - 1].listOfAnswer

    Column(Modifier.fillMaxSize()) {

        // Progress bar
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 32.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = "Back button",
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { if (currentStep.value > 1) currentStep.value -= 1 }
                )
                StepsProgressBar(
                    numberOfSteps = listOfQuestions.size - 1,
                    currentStep = currentStep.value
                )
                StuntionText(
                    text = "Skip",
                    color = Color.Gray,
                    textStyle = Type.labelLarge(),
                    modifier = Modifier.clickable {
                    }
                )
            }
        }

        // Question
        StuntionText(
            text = question,
            textStyle = Type.titleLarge(),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(start = 24.dp, top = 32.dp, end = 24.dp)
        )

        // Answer
        Spacer(modifier = Modifier.height(32.dp))
        if (isMultipleChoice) {
            listOfAnswer.forEach {
                MultipleChoiceItem(
                    title = it,
                    onSelected = {

                    },
                    onUnselected = {

                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .background(color = Color.LightGray, shape = RoundedCornerShape(100.dp))
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        } else {
            listOfAnswer.forEach {
                StuntionButton(
                    onClick = {

                    },
                    backgroundColor = Color.LightGray,
                    contentAlignment = Alignment.CenterStart,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, bottom = 8.dp)
                ) {
                    StuntionText(
                        text = it,
                        textStyle = Type.bodyMedium(),
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }
        }

        // Next
        Spacer(modifier = Modifier.height(140.dp))
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth()) {
            StuntionButton(
                onClick = {
                    if (currentStep.value < listOfQuestions.size)
                        currentStep.value += 1
                },
                backgroundColor = PrimaryBlue,
                modifier = Modifier.width(180.dp)
            ) {
                StuntionText(text = "Next", color = Color.White, textStyle = Type.labelLarge())
            }
        }
    }
}

@Preview
@Composable
fun QuestionPreview() {
    QuestionScreen(navController = rememberNavController())
}