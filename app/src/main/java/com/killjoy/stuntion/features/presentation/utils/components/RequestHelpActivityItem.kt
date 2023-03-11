package com.killjoy.stuntion.features.presentation.utils.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.killjoy.stuntion.R
import com.killjoy.stuntion.ui.stuntionUI.StuntionText
import com.killjoy.stuntion.ui.theme.*

@Composable
fun RequestHelpActivityItem(modifier: Modifier = Modifier) {
    Card(elevation = 8.dp, shape = RoundedCornerShape(16.dp), modifier = modifier) {
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp, horizontal = 8.dp)
        ) {

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, end = 8.dp)
            ) {
                // Consultation With & Timestamp
                Column {
                    StuntionText(text = "Request a Help", textStyle = Type.labelLarge())
                    // Timestamp
                    StuntionText(
                        text = "March 03, 2023",
                        textStyle = Type.bodySmall(),
                        color = Color.Gray,
                    )
                }

                // Done
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .background(
                            color = LightYellow,
                            shape = RoundedCornerShape(16.dp)
                        )
                        .alpha(0.8F)
                ) {
                    StuntionText(
                        text = "On Process",
                        textStyle = Type.labelMedium(),
                        color = Yellow,
                        modifier = Modifier.padding(horizontal = 32.dp, vertical = 4.dp)
                    )
                }
            }

            // Request Help Data
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp)
            ) {

                // Image
                AsyncImage(
                    model = R.drawable.iv_donation,
                    contentDescription = "Donantion image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(64.dp)
                        .clip(RoundedCornerShape(8.dp))
                )

                Column(
                    verticalArrangement = Arrangement.spacedBy(3.dp),
                    modifier = Modifier.padding(start = 12.dp)
                ) {
                    // Title
                    StuntionText(
                        text = "Milk For Babies Aged 1 Year",
                        textStyle = Type.titleSmall(),
                        maxLine = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                    // Detail
                    StuntionText(
                        text = "I need financial assistance to buy formula milk for my baby because my ...",
                        textStyle = Type.bodyMedium(),
                        maxLine = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}