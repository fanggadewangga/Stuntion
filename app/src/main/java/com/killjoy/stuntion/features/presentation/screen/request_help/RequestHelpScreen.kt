package com.killjoy.stuntion.features.presentation.screen.request_help

import androidx.compose.foundation.layout.*
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
import com.killjoy.stuntion.features.presentation.screen.request_help.confirmation.ConfirmationScreen
import com.killjoy.stuntion.features.presentation.screen.request_help.detail_information.DetailInformationScreen
import com.killjoy.stuntion.features.presentation.screen.request_help.help_target.HelpTargetScreen
import com.killjoy.stuntion.features.presentation.screen.request_help.personal_data.PersonalDataScreen
import com.killjoy.stuntion.features.presentation.screen.request_help.title.TitleScreen
import com.killjoy.stuntion.features.presentation.utils.Screen
import com.killjoy.stuntion.features.presentation.utils.components.StuntionButton
import com.killjoy.stuntion.ui.stuntionUI.StuntionText
import com.killjoy.stuntion.ui.theme.PrimaryBlue
import com.killjoy.stuntion.ui.theme.Type

@Composable
fun RequestHelpScreen(navController: NavController) {

    val viewModel = hiltViewModel<RequestHelpViewModel>()

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

        when (viewModel.currentStep.value) {
            1 -> PersonalDataScreen(navController = navController)
            2 -> HelpTargetScreen(navController = navController)
            3 -> TitleScreen(navController = navController)
            4 -> DetailInformationScreen(navController = navController)
            else -> ConfirmationScreen(navController = navController)
        }

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            StuntionButton(
                backgroundColor = Color.White,
                borderColor = PrimaryBlue,
                borderWidth = 0.5.dp,
                onClick = {
                    if (viewModel.currentStep.value > 1)
                        viewModel.currentStep.value -= 1
                    else navController.navigate(Screen.SupportScreen.route)
                },
                modifier = Modifier.width((LocalConfiguration.current.screenWidthDp / 2.2).dp)
            ) {
                StuntionText(
                    text = "Back",
                    color = PrimaryBlue,
                    textStyle = Type.labelLarge()
                )
            }
            StuntionButton(
                onClick = {
                    if (viewModel.currentStep.value < 5)
                        viewModel.currentStep.value += 1
                    else navController.navigate(Screen.RequestHelpSuccessScreen.route)
                },
                modifier = Modifier.width((LocalConfiguration.current.screenWidthDp / 2.2).dp)
            ) {
                StuntionText(
                    text = if (viewModel.currentStep.value < viewModel.listOfStep.size) "Next" else "Finish",
                    color = Color.White,
                    textStyle = Type.labelLarge()
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