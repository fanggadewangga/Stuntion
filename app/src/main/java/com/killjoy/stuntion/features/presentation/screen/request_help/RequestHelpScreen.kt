package com.killjoy.stuntion.features.presentation.screen.request_help

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.killjoy.stuntion.features.presentation.utils.components.StuntionStepper
import com.killjoy.stuntion.ui.stuntionUI.StuntionText
import com.killjoy.stuntion.ui.theme.Type

@Composable
fun RequestHelpScreen(navController: NavController) {

    val viewModel = hiltViewModel<RequestHelpViewModel>()
    val listOfSteps = viewModel.listOfStep

    Column(modifier = Modifier.fillMaxSize()) {

        // Top bar
        Box(modifier = Modifier.fillMaxWidth()) {
            StuntionText(
                text = "Request For Help",
                textStyle = Type.titleLarge(),
                modifier = Modifier.align(
                    Alignment.TopCenter
                )
            )
        }
        Divider(
            color = Color.LightGray, modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp)
        )

        // Form to
        Spacer(modifier = Modifier.height(24.dp))
        StuntionText(
            text = "Form To Request For Help",
            textStyle = Type.titleMedium(),
            modifier = Modifier.padding(start = 16.dp)
        )
        StuntionText(
            text = "This form is used for those of you who wish to request additional food assistance. Please fill in and fill in correctly",
            textStyle = Type.bodyMedium(),
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Divider(
            color = Color.LightGray, modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp)
        )

        // Stepper
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            listOfSteps.forEachIndexed { index, item ->
                StuntionStepper(
                    index = index,
                    currentStep = viewModel.currentStep.value - 1,
                    numberOfSteps = listOfSteps.size,
                    stepItems = listOfSteps,
                    isWithNumber = true,
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun RequestHelpScreenPreview() {
    RequestHelpScreen(navController = rememberNavController())
}