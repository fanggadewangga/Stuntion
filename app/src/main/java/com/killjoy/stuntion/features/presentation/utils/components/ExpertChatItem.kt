package com.killjoy.stuntion.features.presentation.utils.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.killjoy.stuntion.R
import com.killjoy.stuntion.features.data.source.remote.api.response.expert.ExpertListResponse
import com.killjoy.stuntion.ui.stuntionUI.StuntionText
import com.killjoy.stuntion.ui.theme.*

@Composable
fun ExpertChatItem(
    modifier: Modifier = Modifier,
    expert: ExpertListResponse,
    onExpertClicked: () -> Unit,
    onChatClicked: () -> Unit,
) {

    Column(modifier = modifier.clickable { onExpertClicked() }) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {

            Box {
                // Image
                AsyncImage(
                    model = expert.avatarUrl,
                    contentDescription = "Expert Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .clip(
                            CircleShape
                        )
                        .size(80.dp)
                )

                // Online status
                Box(
                    contentAlignment = Alignment.TopCenter,
                    modifier = Modifier
                        .background(
                            color = LightBlue,
                            shape = RoundedCornerShape(100.dp)
                        )
                        .align(Alignment.BottomCenter)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    ) {
                        AsyncImage(
                            model = R.drawable.ic_green_circle,
                            contentDescription = "Green circle icon",
                            modifier = Modifier.size(9.dp)
                        )
                        StuntionText(
                            text = "Online",
                            textStyle = Type.labelSmall(),
                            color = Color.Green
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.width(8.dp))
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                // Name
                StuntionText(text = expert.name, textStyle = Type.labelLarge())

                // Role
                Row(modifier = Modifier.width((LocalConfiguration.current.screenWidthDp * 0.45).dp)) {
                    expert.categories.forEachIndexed { index, category ->
                        StuntionText(
                            text = category,
                            textStyle = Type.bodySmall(),
                            color = LightGray
                        )
                        if (index+1 < expert.categories.size)
                            StuntionText(
                                text = " - ",
                                textStyle = Type.bodySmall(),
                                color = LightGray
                            )
                    }
                }



                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    // Experience
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        modifier = Modifier.wrapContentWidth()
                    ) {
                        AsyncImage(
                            model = R.drawable.ic_bag,
                            contentDescription = "Bag icon",
                            modifier = Modifier.size(16.dp)
                        )
                        StuntionText(
                            text = "${expert.experienceYear} year",
                            textStyle = Type.bodySmall(),
                            color = Gray,
                            maxLine = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }

                    // Rating
                    Spacer(modifier = Modifier.width(8.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        modifier = Modifier.wrapContentWidth()
                    ) {
                        AsyncImage(
                            model = R.drawable.ic_star,
                            contentDescription = "Star icon",
                            modifier = Modifier.size(16.dp)
                        )
                        StuntionText(
                            text = expert.rating.toString(),
                            textStyle = Type.bodySmall(),
                            color = Gray
                        )
                    }
                }

                // Price
                StuntionText(
                    text = "IDR ${expert.fee}",
                    textStyle = Type.titleSmall(),
                    color = PrimaryBlue
                )
            }

            // Button chat
            Spacer(modifier = Modifier.width(24.dp))
            StuntionButton(
                onClick = {
                    onChatClicked()
                },
                contentPadding = PaddingValues(horizontal = 6.dp, vertical = 4.dp),
                modifier = Modifier
                    .width(80.dp)
                    .padding(top = 40.dp)
            ) {
                StuntionText(
                    text = "Chat",
                    textStyle = Type.labelLarge(),
                    color = Color.White
                )
            }
        }

        // Line
        Spacer(modifier = Modifier.height(12.dp))
        Divider(color = Color.LightGray, modifier = Modifier.fillMaxWidth())
    }
}