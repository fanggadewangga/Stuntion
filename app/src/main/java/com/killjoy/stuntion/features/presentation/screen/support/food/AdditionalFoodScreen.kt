package com.killjoy.stuntion.features.presentation.screen.support.food

import android.annotation.SuppressLint
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.killjoy.stuntion.R
import com.killjoy.stuntion.features.presentation.utils.components.StuntionBasicTextField
import com.killjoy.stuntion.features.presentation.utils.components.StuntionButton
import com.killjoy.stuntion.features.presentation.utils.components.StuntionText
import com.killjoy.stuntion.features.presentation.utils.components.StuntionTopBar
import com.killjoy.stuntion.features.presentation.utils.dashedBorder
import com.killjoy.stuntion.ui.theme.Gray
import com.killjoy.stuntion.ui.theme.LightGray
import com.killjoy.stuntion.ui.theme.PrimaryBlue
import com.killjoy.stuntion.ui.theme.Type

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AdditionalFoodScreen(
    navController: NavController,
) {
    val viewModel = hiltViewModel<AdditionalFoodViewModel>()
    val systemUiController = rememberSystemUiController()
    val singlePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri ->
            viewModel.selectedImageUri.value = uri
        }
    )

    LaunchedEffect(Unit) {
        systemUiController.apply {
            setStatusBarColor(color = Color.Transparent, darkIcons = true)
            setNavigationBarColor(color = Color.White, darkIcons = true)
        }
    }

    Scaffold(
        bottomBar = {
            Column(
                verticalArrangement = Arrangement.spacedBy(24.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                AsyncImage(
                    model = R.drawable.ic_support_terms,
                    contentDescription = "Terms",
                )
                StuntionButton(
                    onClick = {},
                    backgroundColor = PrimaryBlue,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    StuntionText(
                        text = "Confirmation",
                        textStyle = Type.labelLarge(),
                        color = Color.White
                    )
                }
            }
        },
        modifier = Modifier.padding(bottom = (LocalConfiguration.current.screenHeightDp / 14).dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = (LocalConfiguration.current.screenHeightDp / 30).dp)
        ) {

            // Top bar
            StuntionTopBar(
                title = "Additional Food",
                onBackPressed = { navController.popBackStack() },
                isWithDivider = true
            )

            // Description
            StuntionText(
                text = "We greatly appreciate your kind intention and concern. Please don't forget to complete this form so that it can be processed promptly.",
                textStyle = Type.bodyMedium(),
                modifier = Modifier.padding(16.dp)
            )
            Divider(
                color = Color.LightGray,
                modifier = Modifier
                    .fillMaxWidth()
            )


            // Title
            StuntionText(
                text = "The quantity of food", textStyle = Type.labelLarge(),
                modifier = Modifier.padding(16.dp)
            )
            StuntionBasicTextField(
                placeHolder = "Enter the quantity of food",
                value = viewModel.foodQuantityState.value.toString(),
                onValueChange = {
                    viewModel.isFoodFieldClicked.value = true
                    viewModel.foodQuantityState.value = it.toIntOrNull() ?: 0
                },
                shape = RoundedCornerShape(100.dp),
                singleLine = true,
                isError = !viewModel.isValidQuantity.value,
                showWarningMessage = !viewModel.isValidQuantity.value,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
                warningMessage = "Field could not be empty.",
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
            )


            // Upload box
            Spacer(modifier = Modifier.height(16.dp))
            Row(modifier = Modifier.padding(horizontal = 16.dp)) {
                StuntionText(
                    text = "Upload a photo of the food to be donated",
                    textStyle = Type.labelLarge()
                )
                StuntionText(text = " *", textStyle = Type.labelLarge(), color = Color.Red)
            }
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp,top = 8.dp, bottom = 16.dp, end = 16.dp )
                    .height((LocalConfiguration.current.screenHeightDp / 5).dp)
                    .dashedBorder(1.dp, Gray, 16.dp)
                    .clickable {
                        singlePhotoPickerLauncher.launch(
                            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                        )
                    },
            ) {
                if (viewModel.selectedImageUri.value != null)
                    AsyncImage(
                        model = viewModel.selectedImageUri.value,
                        contentDescription = "Selected image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(16.dp))
                    )
                else {
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
            }
            StuntionText(
                text = "*   Photos used in accordance with the application",
                textStyle = Type.bodySmall(),
                color = LightGray,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            StuntionText(
                text = "*   Original photo without editing",
                textStyle = Type.bodySmall(),
                color = LightGray,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            StuntionText(
                text = "*   Photos are not blurry",
                textStyle = Type.bodySmall(),
                color = LightGray,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            StuntionText(
                text = "*   Adequate photo lighting",
                textStyle = Type.bodySmall(),
                color = LightGray,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }

    }
}