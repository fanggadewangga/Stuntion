package com.killjoy.stuntion.features.presentation.screen.question

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.killjoy.stuntion.R
import com.killjoy.stuntion.ui.stuntionUI.StuntionText
import com.killjoy.stuntion.ui.theme.Type

@Composable
fun QuestionScreen(navController: NavController) {

    val currentStep = remember {
        mutableStateOf(1)
    }

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
                    .clickable { currentStep.value -= 1 }
            )
            StepsProgressBar(numberOfSteps = 5, currentStep = currentStep.value)
            StuntionText(
                text = "Skip",
                color = Color.Gray,
                textStyle = Type.labelLarge(),
                modifier = Modifier.clickable {
                    currentStep.value += 1
                }
            )
        }
    }
}

@Preview
@Composable
fun QuestionPreview() {
    QuestionScreen(navController = rememberNavController())
}