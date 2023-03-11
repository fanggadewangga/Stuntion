package com.killjoy.stuntion.features.presentation.screen.chat

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.killjoy.stuntion.R
import com.killjoy.stuntion.features.presentation.utils.components.ChatBubbleItem
import com.killjoy.stuntion.features.presentation.utils.components.StuntionBasicTextField
import com.killjoy.stuntion.ui.stuntionUI.StuntionText
import com.killjoy.stuntion.ui.theme.LightGray
import com.killjoy.stuntion.ui.theme.PrimaryBlue
import com.killjoy.stuntion.ui.theme.Type

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ChatRoomScreen(navController: NavController) {

    val viewModel = hiltViewModel<ChatRoomViewModel>()

    Scaffold(
        bottomBar = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 18.dp, vertical = 8.dp)
            ) {
                StuntionBasicTextField(
                    placeHolder = "Type a message",
                    value = viewModel.chatTextState.value,
                    onValueChange = { viewModel.chatTextState.value = it },
                    borderColor = Color.LightGray,
                    textFieldHeight = 42.dp,
                    modifier = Modifier.width((LocalConfiguration.current.screenWidthDp * 0.8).dp)
                )
                Icon(
                    imageVector = Icons.Default.Send,
                    contentDescription = "Send arrow",
                    tint = PrimaryBlue,
                    modifier = Modifier
                        .size(24.dp)
                        .clickable {

                        }
                )
            }
        },
        modifier = Modifier.padding(bottom = (LocalConfiguration.current.screenHeightDp / 17).dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(bottom = (LocalConfiguration.current.screenHeightDp / 15).dp)
        ) {
            // Expert Information
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = PrimaryBlue, shape = RectangleShape)
                    .padding(
                        start = 16.dp,
                        top = (LocalConfiguration.current.screenHeightDp / 17).dp,
                        end = 16.dp,
                        bottom = 16.dp
                    )
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back arrow",
                    tint = Color.White,
                    modifier = Modifier
                        .size(24.dp)
                        .clickable {
                            navController.popBackStack()
                        }
                )
                AsyncImage(
                    model = "https://firebasestorage.googleapis.com/v0/b/stuntion-a32cc.appspot.com/o/expert%2Fusman-yousaf-pTrhfmj2jDA-unsplash.jpg?alt=media&token=23212350-188e-4555-92ac-34056c48ad26",
                    contentDescription = "Expert image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(48.dp)
                        .clip(
                            CircleShape
                        )
                )
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    StuntionText(
                        text = "dr. A. Roni Naning, Sp.A (K)",
                        textStyle = Type.titleMedium(),
                        color = Color.White
                    )
                    StuntionText(
                        text = "Pediatrician - Respirologist",
                        textStyle = Type.bodySmall(),
                        color = Color.White
                    )
                }
            }

            // Time stamp
            Box(
                contentAlignment = Alignment.TopCenter,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
            ) {
                StuntionText(
                    text = "Today 5:34 AM",
                    color = LightGray,
                    textStyle = Type.bodySmall(),
                )
            }

            // Chat
            ChatBubbleItem(
                text = "What are some nutritious food options for a 13-month-old baby and how can they be incorporated into a feeding schedule?",
                type = 0
            )
            ChatBubbleItem(
                text = "At 13 months old, a baby's feeding schedule should consist of three main meals and two snacks per day. The recommended types of food for a 13-month-old baby include a mix of protein, carbohydrates, and healthy fats, as well as fruits and vegetables. It's important to ensure that the baby is getting all of the nutrients that they need for their growth and development.\n" +
                        "\n" +
                        "To organize the feeding schedule effectively, parents should plan out meals and snacks in advance, and aim to offer a variety of foods to the baby. It can be helpful to make a weekly meal plan, and to keep healthy snacks on hand for when the baby gets hungry between meals. It's also important to be flexible and responsive to the baby's cues, as their appetite and needs may vary from day to day.",
                type = 1
            )
            ChatBubbleItem(
                text = "Thank you so much for your answer! It was very informative and helpful. I appreciate you taking the time to provide such a detailed response.",
                type = 0
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ChatRoomPreview() {
    ChatRoomScreen(navController = rememberNavController())
}