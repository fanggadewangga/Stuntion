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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.killjoy.stuntion.ui.stuntionUI.StuntionText
import com.killjoy.stuntion.ui.theme.Green
import com.killjoy.stuntion.ui.theme.LightGreen
import com.killjoy.stuntion.ui.theme.Type
import com.killjoy.stuntion.R
import com.killjoy.stuntion.ui.theme.PrimaryBlue

@Composable
fun ChatActivityItem(modifier: Modifier = Modifier) {
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
                    StuntionText(text = "Consultation With", textStyle = Type.labelLarge())
                    // Timestamp
                    StuntionText(
                        text = "February 12, 2023",
                        textStyle = Type.bodySmall(),
                        color = Color.Gray,
                    )
                }

                // Done
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .background(
                            color = LightGreen,
                            shape = RoundedCornerShape(16.dp)
                        )
                        .alpha(0.8F)
                ) {
                    StuntionText(
                        text = "Done",
                        textStyle = Type.labelMedium(),
                        color = Green,
                        modifier = Modifier.padding(horizontal = 32.dp, vertical = 4.dp)
                    )
                }
            }

            // Expert Profile
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp)
            ) {

                // Image
                AsyncImage(
                    model = R.drawable.iv_expert_detail,
                    contentDescription = "Expert image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(64.dp)
                        .clip(RoundedCornerShape(8.dp))
                )

                Column(
                    verticalArrangement = Arrangement.spacedBy(3.dp),
                    modifier = Modifier.padding(start = 8.dp)
                ) {
                    // Name
                    StuntionText(
                        text = "dr. A. Roni Naning, Sp.A (K)",
                        textStyle = Type.titleSmall()
                    )

                    // Category
                    StuntionText(
                        text = "Pediatrician - Respirologist",
                        textStyle = Type.bodySmall()
                    )

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {

                        // Consultation Fee
                        Row {
                            StuntionText(
                                text = "Consultation Fee",
                                textStyle = Type.bodySmall(),
                                color = Color.Gray
                            )
                            StuntionText(
                                text = " IDR 40.000",
                                textStyle = Type.labelMedium(),
                                color = PrimaryBlue
                            )
                        }


                        // Chat Again
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .background(
                                    color = PrimaryBlue,
                                    shape = RoundedCornerShape(16.dp)
                                )
                        ) {
                            StuntionText(
                                text = "Chat Again",
                                textStyle = Type.labelMedium(),
                                color = Color.White,
                                modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
                            )
                        }
                    }
                }
            }


        }
    }
}