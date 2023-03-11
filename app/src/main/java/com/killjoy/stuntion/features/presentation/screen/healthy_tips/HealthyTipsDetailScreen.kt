package com.killjoy.stuntion.features.presentation.screen.healthy_tips

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.killjoy.stuntion.R
import com.killjoy.stuntion.features.presentation.utils.components.StuntionButton
import com.killjoy.stuntion.features.presentation.utils.dashedBorder
import com.killjoy.stuntion.ui.stuntionUI.StuntionText
import com.killjoy.stuntion.ui.theme.Gray
import com.killjoy.stuntion.ui.theme.PrimaryBlue
import com.killjoy.stuntion.ui.theme.Type

@Composable
fun HealthyTipsDetailScreen(navController: NavController) {
    val viewModel = hiltViewModel<HealthyTipsDetailViewModel>()
    val systemUiController = rememberSystemUiController()
    systemUiController.apply {
        setStatusBarColor(color = Color.Transparent, darkIcons = true)
        setNavigationBarColor(color = Color.White, darkIcons = true)
    }

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = (LocalConfiguration.current.screenHeightDp / 17).dp)
    ) {
        // Image
        item {
            AsyncImage(
                model = "https://firebasestorage.googleapis.com/v0/b/stuntion-a32cc.appspot.com/o/task%2Flucy-wolski-sljmgxyzmqM-unsplash.jpg?alt=media&token=116b9827-919a-4315-911c-22eaca990e83",
                contentScale = ContentScale.Crop,
                contentDescription = "Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(
                        (LocalConfiguration.current.screenHeightDp / 4).dp
                    )
            )
        }

        // Title
        item {
            StuntionText(
                text = "Give your baby a drink with formula milk that contains high calcium",
                textStyle = Type.titleMedium(),
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }

        // Age
        item {
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                AsyncImage(
                    model = R.drawable.ic_age,
                    contentDescription = "Baby icon",
                    modifier = Modifier.size(20.dp)
                )
                StuntionText(text = "Age", textStyle = Type.labelLarge())
            }
        }
        item {
            StuntionText(
                text = "0.5 - 4 Years",
                textStyle = Type.bodyMedium(),
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }

        // Material
        item {
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(start = 16.dp)
            ) {
                AsyncImage(
                    model = R.drawable.ic_material,
                    contentDescription = "Material icon",
                    modifier = Modifier.size(20.dp)
                )
                StuntionText(text = "Material", textStyle = Type.labelLarge())
            }
        }
        item {
            StuntionText(
                text = "Formula milk",
                textStyle = Type.bodyMedium(),
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }

        // Instruction
        item {
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                AsyncImage(
                    model = R.drawable.ic_instruction,
                    contentDescription = "Instruction icon",
                    modifier = Modifier.size(20.dp)
                )
                StuntionText(text = "Material", textStyle = Type.labelLarge())
            }
        }
        itemsIndexed(
            items = listOf(
                "Wash your hands thoroughly with soap and water.",
                "Sterilize all the equipment, including the bottle, nipple, and any other utensils, according to the manufacturer's instructions.",
                "Boil water and let it cool down to the recommended temperature. Check the instructions on the formula milk package for the exact temperature.",
                "Pour the correct amount of water into the sterilized bottle.",
                "Add the correct amount of formula milk powder to the water in the bottle. Make sure to use the scoop that comes with the formula, and level it off with a clean knife.",
                "Feed your baby the prepared formula milk immediately.",
                "If your baby doesn't finish the bottle, discard any remaining formula milk within one hour of preparation.",
            )
        ) { index, instruction ->
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                StuntionText(
                    text = "${index + 1}.",
                    textStyle = Type.bodyMedium(),
                    modifier = Modifier.padding(start = 18.dp)
                )
                StuntionText(
                    text = instruction,
                    textStyle = Type.bodyMedium(),
                    modifier = Modifier.padding(end = 16.dp)
                )
            }
        }

        // Your Activity
        item {
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(start = 16.dp)
            ) {
                AsyncImage(
                    model = R.drawable.ic_photo,
                    contentDescription = "Photo icon",
                    modifier = Modifier.size(20.dp)
                )
                StuntionText(text = "Your Activity", textStyle = Type.labelLarge())
            }
        }
        item {
            StuntionText(
                text = "Celebrate this success by uploading a picture so you can see it again and earn points",
                textStyle = Type.bodyMedium(),
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }

        // Box
        item {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                    .height((LocalConfiguration.current.screenHeightDp / 5).dp)
                    .dashedBorder(1.dp, Gray, 16.dp)
                    .clickable {

                    },
            ) {
                Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                    AsyncImage(
                        model = R.drawable.ic_file_upload,
                        contentDescription = "Upload icon",
                        modifier = Modifier.size(24.dp)
                    )
                    StuntionText(
                        text = "Upload photos",
                        textStyle = Type.bodyMedium(),
                        color = Gray
                    )
                }
            }
        }

        // Button
        item {
            StuntionButton(
                onClick = {

                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                StuntionText(
                    text = "Succeed",
                    textStyle = Type.labelLarge(),
                    color = Color.White
                )
            }
        }
    }
}

@Preview
@Composable
fun HealthyTipsDetailPreview() {
    HealthyTipsDetailScreen(navController = rememberNavController())
}