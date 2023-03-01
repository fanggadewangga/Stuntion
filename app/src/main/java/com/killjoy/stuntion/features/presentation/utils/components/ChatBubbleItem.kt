package com.killjoy.stuntion.features.presentation.utils.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.killjoy.stuntion.R
import com.killjoy.stuntion.ui.stuntionUI.StuntionText
import com.killjoy.stuntion.ui.theme.LightBlue
import com.killjoy.stuntion.ui.theme.SecondaryBlue
import com.killjoy.stuntion.ui.theme.Type

@Composable
fun ChatBubbleItem(
    text: String,
    type: Int,
) {
    val bubbleColor = if (type == 0) SecondaryBlue else LightBlue
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
                    model = R.drawable.iv_expert,
                    contentDescription = "Expert image",
                    modifier = Modifier.size(44.dp)
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