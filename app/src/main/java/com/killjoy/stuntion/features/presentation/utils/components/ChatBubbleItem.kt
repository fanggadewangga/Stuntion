package com.killjoy.stuntion.features.presentation.utils.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.killjoy.stuntion.ui.stuntionUI.StuntionText
import com.killjoy.stuntion.ui.theme.LightBlue
import com.killjoy.stuntion.ui.theme.LighterGray
import com.killjoy.stuntion.ui.theme.Type

@Composable
fun ChatBubbleItem(
    text: String,
    type: Int,
) {
    val bubbleColor = if (type == 0) LightBlue else LighterGray
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .width((LocalConfiguration.current.screenWidthDp * 0.8).dp)
                .align(if (type == 0) Alignment.CenterEnd else Alignment.CenterStart)
        ) {
            if (type != 0)
                AsyncImage(
                    model = "https://firebasestorage.googleapis.com/v0/b/stuntion-a32cc.appspot.com/o/expert%2Fusman-yousaf-pTrhfmj2jDA-unsplash.jpg?alt=media&token=23212350-188e-4555-92ac-34056c48ad26",
                    contentDescription = "Expert image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(44.dp)
                        .clip(CircleShape)
                )
            Box(
                modifier = Modifier
                    .background(
                        color = bubbleColor,
                        shape = RoundedCornerShape(22.dp)
                    )
            ) {
                StuntionText(
                    text = text,
                    textStyle = Type.bodyLarge(),
                    modifier = Modifier.padding(vertical = 12.dp, horizontal = 16.dp)
                )
            }
        }
    }
}