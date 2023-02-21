package com.killjoy.stuntion.features.presentation.screen.request_help.title

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.killjoy.stuntion.R
import com.killjoy.stuntion.features.presentation.utils.components.StuntionBasicTextField
import com.killjoy.stuntion.features.presentation.utils.dashedBorder
import com.killjoy.stuntion.ui.stuntionUI.StuntionText
import com.killjoy.stuntion.ui.theme.Gray
import com.killjoy.stuntion.ui.theme.LightGray
import com.killjoy.stuntion.ui.theme.Type

@Composable
fun TitleScreen(navController: NavController) {
    val viewModel = hiltViewModel<TitleViewModel>()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        StuntionText(text = "Tittle", textStyle = Type.titleMedium())
        Spacer(modifier = Modifier.height(24.dp))

        // Title
        Row(modifier = Modifier.padding(bottom = 8.dp)) {
            StuntionText(
                text = "Give a title to the request for help",
                textStyle = Type.labelLarge()
            )
            StuntionText(text = " *", textStyle = Type.labelLarge(), color = Color.Red)
        }
        StuntionBasicTextField(
            placeHolder = "Enter request title",
            value = viewModel.titleState.value,
            onValueChange = {
                viewModel.isTitleFieldClicked.value = true
                viewModel.titleState.value = it
            },
            shape = RoundedCornerShape(100.dp),
            singleLine = true,
            isError = !viewModel.isValidTitle.value,
            showWarningMessage = !viewModel.isValidTitle.value,
            warningMessage = "Field could not be empty.",
            modifier = Modifier.fillMaxWidth()
        )


        // Upload box
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            StuntionText(
                text = "Upload a photo for a request for help",
                textStyle = Type.labelLarge()
            )
            StuntionText(text = " *", textStyle = Type.labelLarge(), color = Color.Red)
        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp, top = 8.dp)
                .height((LocalConfiguration.current.screenHeightDp / 5).dp)
                .dashedBorder(1.dp, Gray, 16.dp)
                .clickable {

                },
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AsyncImage(
                    model = R.drawable.ic_file_upload,
                    contentDescription = "Upload icon",
                    modifier = Modifier.size(24.dp)
                )
                StuntionText(
                    text = "Upload photos",
                    textStyle = Type.bodyMedium(),
                    color = LightGray
                )
            }
        }
        StuntionText(
            text = "*   Photos used in accordance with the application",
            textStyle = Type.bodySmall(),
            color = LightGray,
        )
        StuntionText(
            text = "*   Original photo without editing",
            textStyle = Type.bodySmall(),
            color = LightGray,
        )
        StuntionText(
            text = "*   Photos are not blurry",
            textStyle = Type.bodySmall(),
            color = LightGray,
        )
        StuntionText(
            text = "*   Adequate photo lighting",
            textStyle = Type.bodySmall(),
            color = LightGray,
        )
    }
}