package com.killjoy.stuntion.features.presentation.screen.support.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.killjoy.stuntion.R
import com.killjoy.stuntion.features.presentation.utils.components.StuntionButton
import com.killjoy.stuntion.ui.stuntionUI.StuntionText
import com.killjoy.stuntion.ui.theme.PrimaryBlue
import com.killjoy.stuntion.ui.theme.Type

@Composable
fun SupportDetailScreen(navController: NavController) {

    val viewModel = hiltViewModel<SupportDetailViewModel>()

    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        // Image
        item {
            Box(modifier = Modifier.fillMaxWidth()) {
                AsyncImage(
                    model = R.drawable.iv_support_detail,
                    contentDescription = "Support image",
                    contentScale = ContentScale.FillWidth
                )
                Image(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Arrow back icon",
                    colorFilter = ColorFilter.tint(Color.White),
                    modifier = Modifier
                        .padding(start = 16.dp, top = 48.dp)
                        .align(Alignment.TopStart)
                        .clickable { }
                )
            }
        }

        // Title
        item {
            StuntionText(
                text = "Milk For Babies Aged 1 Year",
                textStyle = Type.titleLarge(),
                modifier = Modifier.padding(16.dp)
            )
        }

        // Fee
        item {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier.padding(start = 16.dp, end = 16.dp)
            ) {
                StuntionText(
                    text = "Rp70.000",
                    textStyle = Type.titleMedium(),
                    color = PrimaryBlue
                )
                StuntionText(
                    text = "or",
                    textStyle = Type.titleMedium(),
                    color = Color.Gray
                )
                StuntionText(
                    text = "1 Item",
                    textStyle = Type.titleMedium(),
                    color = PrimaryBlue
                )
            }
        }

        // Collected
        item {
            StuntionText(
                text = "Collected Rp70.000 from Rp350.000 or 1 Item from 5 Item",
                textStyle = Type.bodySmall(),
                color = Color.Gray,
                maxLine = 2,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
        }

        // Progress
        item {
            LinearProgressIndicator(
                progress = (1 / 6.toFloat()),
                backgroundColor = Color.LightGray,
                color = PrimaryBlue,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(12.dp)
                    .padding(horizontal = 16.dp)
                    .clip(shape = RoundedCornerShape(16.dp))
            )
        }

        // Supporters and days counter
        item {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                // Supporter count
                Row(
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AsyncImage(
                        model = R.drawable.ic_supporter,
                        contentDescription = "Person icon",
                        modifier = Modifier.size(20.dp)
                    )
                    StuntionText(
                        text = "1",
                        textStyle = Type.titleSmall(),
                    )
                    StuntionText(
                        text = "support",
                        textStyle = Type.bodySmall(),
                    )
                }

                // Days counter
                Row(
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AsyncImage(
                        model = R.drawable.ic_clock,
                        contentDescription = "Person icon",
                        modifier = Modifier.size(20.dp)
                    )
                    StuntionText(
                        text = "1",
                        textStyle = Type.titleSmall(),
                    )
                    StuntionText(
                        text = "days more",
                        textStyle = Type.bodySmall(),
                    )
                }
            }
        }

        // Divider
        item {
            Divider(
                color = Color.Gray,
                thickness = 0.5.dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            )
        }


        // User Profile
        item {
            StuntionText(
                text = "Uploaded By",
                textStyle = Type.titleMedium(),
                modifier = Modifier.padding(16.dp)
            )
        }
        item {
            Card(
                elevation = 4.dp,
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )
                {
                    // Expert Image
                    AsyncImage(
                        model = R.drawable.avatar_1,
                        contentDescription = "User Avatar",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(60.dp)
                            .clip(CircleShape)
                    )

                    Spacer(modifier = Modifier.width(8.dp))
                    Column(
                        verticalArrangement = Arrangement.spacedBy(6.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        // User name
                        StuntionText(
                            text = "Maya Susanti",
                            textStyle = Type.labelLarge()
                        )
                        // User Location
                        StuntionText(
                            text = "Malang, East Java, Indonesia",
                            textStyle = Type.bodySmall(),
                            color = Color.Gray
                        )
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            AsyncImage(
                                model = R.drawable.ic_person,
                                contentDescription = "Person icon",
                                modifier = Modifier.size(12.dp)
                            )
                            StuntionText(
                                text = "Contact Info",
                                textStyle = Type.labelMedium(),
                                color = PrimaryBlue,
                            )
                        }
                    }
                }
            }
        }

        // Information
        item {
            StuntionText(
                text = "Information",
                textStyle = Type.titleMedium(),
                modifier = Modifier.padding(start = 16.dp, top = 16.dp, end = 16.dp)
            )
        }
        item {
            StuntionText(
                text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
                textStyle = Type.bodyMedium(),
                modifier = Modifier.padding(start = 16.dp, top = 16.dp, end = 16.dp)
            )
        }

        // Button
        item {
            Spacer(modifier = Modifier.height((LocalConfiguration.current.screenHeightDp / 14).dp))
            StuntionButton(
                onClick = {

                },
                contentPadding = PaddingValues(vertical = 8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, bottom = 64.dp)
            ) {
                StuntionText(
                    text = "Support",
                    color = Color.White,
                    textStyle = Type.labelLarge(),
                )
            }
        }
    }
}

@Preview
@Composable
fun SupportDetailPreview() {
    SupportDetailScreen(navController = rememberNavController())
}