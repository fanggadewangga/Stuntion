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
                    model = R.drawable.iv_expert,
                    contentDescription = "Expert image",
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
                    text = "Today 8:43 AM",
                    color = LightGray,
                    textStyle = Type.bodySmall(),
                )
            }

            // Chat
            ChatBubbleItem(
                text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt",
                type = 1
            )
            ChatBubbleItem(
                text = "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur",
                type = 0
            )
            ChatBubbleItem(
                text = "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum",
                type = 1
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ChatRoomPreview() {
    ChatRoomScreen(navController = rememberNavController())
}