package com.killjoy.stuntion.features.presentation.utils.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.killjoy.stuntion.R
import com.killjoy.stuntion.ui.stuntionUI.StuntionText
import com.killjoy.stuntion.ui.theme.PrimaryBlue
import com.killjoy.stuntion.ui.theme.Type

@Composable
fun MyHealthyTipsItem(
    modifier: Modifier = Modifier,
    title: String = "",
    imageUrl: String = "",
    completedAction: Int = 0,
    totalAction: Int = 0,
) {
    Card(
        elevation = 4.dp,
        shape = RoundedCornerShape(16.dp),
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 12.dp)
        ) {
            // Image
            AsyncImage(
                model = R.drawable.iv_home_task,
                contentDescription = "Task image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(8.dp))
            )


            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(start = 8.dp)
            ) {

                // Title
                StuntionText(
                    text = "If the child can walk, train and accompany the child when climbing ...",
                    textStyle = Type.titleSmall(),
                    maxLine = 2,
                    overflow = TextOverflow.Ellipsis
                )

                // Indicator
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    for (i in 1..totalAction) {
                        Divider(
                            thickness = 8.dp,
                            color = if (i <= completedAction) PrimaryBlue else Color.LightGray,
                            modifier = Modifier
                                .width((LocalConfiguration.current.screenWidthDp * 0.6 / totalAction).dp)
                                .clip(RoundedCornerShape(8.dp))
                        )
                        Spacer(modifier = Modifier.width(1.dp))
                    }
                }


                // Counter
                StuntionText(
                    text = "$completedAction out of $totalAction actions",
                    textStyle = Type.bodySmall()
                )
            }
        }
    }
}

@Preview
@Composable
fun MyHealthyTipsItemPreview() {
    MyHealthyTipsItem(
        title = "If the child can walk, train and accompany the child when climbing ...",
        completedAction = 4,
        totalAction = 7,
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp)
    )
}