package com.killjoy.stuntion.features.presentation.screen.request_help.detail_information

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.killjoy.stuntion.features.presentation.utils.components.StuntionTextField
import com.killjoy.stuntion.ui.stuntionUI.StuntionText
import com.killjoy.stuntion.ui.theme.PrimaryBlue
import com.killjoy.stuntion.ui.theme.Type

@Composable
fun DetailInformationScreen(navController: NavController) {
    val viewModel = hiltViewModel<DetailInformationViewModel>()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        StuntionText(text = "Detail Information", textStyle = Type.titleMedium())
        Spacer(modifier = Modifier.height(24.dp))

        // Title
        Row {
            StuntionText(
                text = "Write a Story About a Request For Help",
                textStyle = Type.labelLarge()
            )
            StuntionText(text = " *", textStyle = Type.labelLarge(), color = Color.Red)
        }
        StuntionTextField(
            placeHolder = "Write down some request reasons",
            value = viewModel.storyState.value,
            onValueChange = {
                viewModel.isStoryFieldClicked.value = true
                viewModel.storyState.value = it
            },
            shape = RoundedCornerShape(32.dp),
            singleLine = true,
            focusedIndicatorColor = PrimaryBlue,
            isError = !viewModel.isValidStory.value,
            showWarningMessage = !viewModel.isValidStory.value,
            warningMessage = "Field could not be empty.",
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
        )
    }
}