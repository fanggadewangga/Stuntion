package com.killjoy.stuntion.features.presentation.utils.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.killjoy.stuntion.ui.theme.PrimaryBlue
import com.killjoy.stuntion.ui.theme.Type

@Composable
fun RewardItem(
    modifier: Modifier = Modifier,
    iconUrl: String,
    title: String,
    level: Int,
) {

    val isClaimed = remember {
        mutableStateOf(false)
    }

    Card(
        elevation = 8.dp,
        shape = RoundedCornerShape(16.dp),
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 8.dp)
        ) {
            // Icon
            AsyncImage(
                model = iconUrl,
                contentDescription = "Reward icon",
                modifier = Modifier
                    .width(112.dp)
                    .height(88.dp)
            )

            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                // Title
                StuntionText(
                    text = title,
                    textStyle = Type.titleSmall(),
                    maxLine = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row {
                        // Level
                        StuntionText(text = "Level ", textStyle = Type.bodySmall())
                        StuntionText(
                            text = level.toString(),
                            textStyle = Type.bodySmall(),
                            color = PrimaryBlue
                        )
                    }

                    // Claim button
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .background(
                                color = if (isClaimed.value) Color.LightGray else PrimaryBlue,
                                shape = RoundedCornerShape(16.dp)
                            )
                            .clickable {
                                isClaimed.value = true
                            }
                    ) {
                        StuntionText(
                            text = if (isClaimed.value) "Claimed" else "Claim",
                            color = Color.White,
                            textStyle = Type.labelMedium(),
                            modifier = Modifier.padding(horizontal = 24.dp, vertical = 4.dp)
                        )
                    }
                }
            }
        }
    }
}