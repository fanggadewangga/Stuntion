package com.killjoy.stuntion.features.presentation.utils.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.killjoy.stuntion.features.data.source.remote.api.response.donation.DonorResponse
import com.killjoy.stuntion.ui.theme.PrimaryBlue
import com.killjoy.stuntion.ui.theme.Type

@Composable
fun DonorItem(
    modifier: Modifier = Modifier,
    donorResponse: DonorResponse,
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        backgroundColor = Color(0xFFF4EFF4),
        modifier = modifier
    ) {
        // Parent
        Row(
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            // Image, name, and nominal
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.width((LocalConfiguration.current.screenWidthDp * 0.5).dp)
            ) {
                // Image
                AsyncImage(
                    model = donorResponse.avatarUrl,
                    contentDescription = "Avatar",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(50.dp)
                )

                // Name and nominal
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxHeight()
                ) {
                    StuntionText(
                        text = donorResponse.donorName,
                        textStyle = Type.titleSmall(),
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.width((LocalConfiguration.current.screenWidthDp * 0.5).dp)
                    )
                    StuntionText(
                        text = "Donate IDR ${donorResponse.nominal.toInt()}",
                        textStyle = Type.bodySmall(),
                    )
                }
            }

            // date
            StuntionText(
                text = if (donorResponse.dayPeriod == 0) "Today" else "${donorResponse.dayPeriod} days ago",
                color = PrimaryBlue,
                textStyle = Type.bodySmall()
            )
        }
    }
}