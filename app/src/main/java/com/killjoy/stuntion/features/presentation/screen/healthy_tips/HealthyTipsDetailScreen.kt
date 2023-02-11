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
import com.killjoy.stuntion.R
import com.killjoy.stuntion.features.presentation.utils.components.StuntionButton
import com.killjoy.stuntion.features.presentation.utils.dashedBorder
import com.killjoy.stuntion.ui.stuntionUI.StuntionText
import com.killjoy.stuntion.ui.theme.Gray
import com.killjoy.stuntion.ui.theme.Type

@Composable
fun HealthyTipsDetailScreen(navController: NavController) {
    val viewModel = hiltViewModel<HealthyTipsDetailViewModel>()

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        // Image
        item {
            AsyncImage(
                model = R.drawable.iv_baby,
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
                text = "If the child can walk, train and accompany the child when climbing the stairs",
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
                text = "1 - 1,5 Years",
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
                text = "Climbing Toy or safe Stairs",
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
                "Start with small steps: Begin by having your child practice climbing up and down just a few stairs at a time. As they get more confident and comfortable, you can gradually increase the number of stairs.",
                "Hold their hand: For younger children, it is important to hold their hand or have them hold onto a stable handrail while they climb. This can help them feel more secure and reduce the risk of falls.",
                "Encourage them to look ahead: Remind your child to look ahead while they climb, rather than down at their feet. This can help them maintain their balance and avoid tripping.",
                "Practice safe stepping: Teach your child to place their entire foot on each step, rather than just the toes or balls of their feet. This can help them maintain better balance and avoid falls.",
                "Be patient and encouraging: Climbing stairs can be challenging for young children, so it is important to be patient and encouraging throughout the process. Offer praise and support for their efforts and accomplishments, and allow them to move at their own pace.",
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
                modifier = Modifier.fillMaxWidth().padding(16.dp)
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