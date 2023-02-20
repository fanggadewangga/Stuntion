package com.killjoy.stuntion.features.presentation.utils.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
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
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.Period
import java.time.format.DateTimeFormatter

@Composable
fun DonationItem(
    modifier: Modifier = Modifier,
    title: String,
    location: String,
    currentValue: Int,
    maxValue: Int,
    deadlineDate: String,
    imageUrl: String? = null,
    fee: Int,
) {

    val currentDate = DateTimeFormatter.ofPattern("MM/dd/yyy").format(LocalDateTime.now())
    val dayPeriod = Period.between(
        LocalDate.parse(currentDate, DateTimeFormatter.ofPattern("MM/dd/yyyy")),
        LocalDate.parse(deadlineDate, DateTimeFormatter.ofPattern("MM/dd/yyyy"))
    ).days

    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = 6.dp,
        modifier = modifier
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 10.dp)
        ) {

            // Image
            AsyncImage(
                model = R.drawable.iv_donation,
                contentDescription = "Donation image",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(84.dp)
                    .clip(
                        RoundedCornerShape(8.dp)
                    )
            )

            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {

                // Title
                StuntionText(
                    text = title,
                    textStyle = Type.titleMedium(),
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.width((LocalConfiguration.current.screenWidthDp * 0.65).dp)
                )

                // Location
                Row(horizontalArrangement = Arrangement.spacedBy(2.dp)) {
                    Image(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = "Location icon",
                        colorFilter = ColorFilter.tint(PrimaryBlue),
                        modifier = Modifier.size(20.dp)
                    )
                    StuntionText(
                        text = location,
                        textStyle = Type.bodyMedium(),
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.width((LocalConfiguration.current.screenWidthDp * 0.65).dp)
                    )
                }

                // Progress Bar
                LinearProgressIndicator(
                    progress = (currentValue / maxValue.toFloat()),
                    backgroundColor = Color.LightGray,
                    color = PrimaryBlue,
                    modifier = Modifier
                        .width((LocalConfiguration.current.screenWidthDp * 0.65).dp)
                        .height(8.dp)
                        .clip(shape = RoundedCornerShape(16.dp))
                )

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.width((LocalConfiguration.current.screenWidthDp * 0.65).dp)
                ) {
                    // Fee
                    Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                        AsyncImage(
                            model = R.drawable.ic_wallet,
                            contentDescription = "Wallet icon",
                            modifier = Modifier.size(20.dp)
                        )
                        StuntionText(
                            text = "Rp$fee",
                            textStyle = Type.titleSmall(),
                        )
                    }

                    // Deadline
                    Row(horizontalArrangement = Arrangement.spacedBy(2.dp)) {
                        AsyncImage(
                            model = R.drawable.ic_clock,
                            contentDescription = "Clock icon",
                            modifier = Modifier.size(20.dp)
                        )
                        StuntionText(
                            text = dayPeriod.toString(),
                            textStyle = Type.titleSmall(),
                        )
                        StuntionText(
                            text = " days more",
                            textStyle = Type.bodyMedium(),
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun HomeDonationItem(
    modifier: Modifier = Modifier,
    title: String,
    location: String,
    currentValue: Int,
    maxValue: Int,
    deadlineDate: String,
    imageUrl: String? = null,
    fee: Int,
) {
    Card(
        elevation = 3.dp,
        shape = RoundedCornerShape(16.dp),
        modifier = modifier
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(bottom = 6.dp)
        ) {
            Box(modifier = Modifier.fillMaxWidth()) {
                AsyncImage(
                    model = R.drawable.iv_donation,
                    contentDescription = "Donation item image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(98.dp)
                )
                Box(
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .background(
                            shape = RoundedCornerShape(topStart = 16.dp, bottomStart = 16.dp),
                            color = PrimaryBlue
                        )
                        .align(Alignment.TopEnd)
                ) {
                    StuntionText(
                        text = "10 days more",
                        textStyle = Type.labelMedium(),
                        color = Color.White,
                        modifier = Modifier.padding(horizontal = 4.dp, vertical = 2.dp)
                    )
                }
            }

            // Title
            StuntionText(
                text = title,
                textStyle = Type.labelMedium(),
                modifier = Modifier.padding(horizontal = 8.dp)
            )

            // Location
            StuntionText(
                text = location,
                textStyle = Type.bodySmall(),
                color = Color.LightGray,
                modifier = Modifier.padding(horizontal = 8.dp)
            )

            // Progress Bar
            LinearProgressIndicator(
                progress = (currentValue / maxValue.toFloat()),
                backgroundColor = Color.LightGray,
                color = PrimaryBlue,
                modifier = Modifier
                    .width((LocalConfiguration.current.screenWidthDp * 0.65).dp)
                    .height(8.dp)
                    .padding(horizontal = 8.dp)
                    .clip(shape = RoundedCornerShape(16.dp))
            )

            // Collected
            Row(modifier = Modifier.padding(horizontal = 8.dp)) {
                StuntionText(
                    text = "Collected ",
                    textStyle = Type.bodySmall(),
                    color = Color.LightGray
                )
                StuntionText(text = "IDR 10.000", textStyle = Type.labelMedium())
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SupportItemPreview() {
    HomeDonationItem(
        title = "Milk For Babies Aged 1 Year",
        location = "Malang, Jawa Timur",
        currentValue = 1,
        maxValue = 5,
        deadlineDate = "02/15/2023",
        fee = 50000,
        modifier = Modifier.width(196.dp)
    )
}