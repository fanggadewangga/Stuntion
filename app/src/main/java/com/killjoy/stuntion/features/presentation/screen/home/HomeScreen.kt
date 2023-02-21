package com.killjoy.stuntion.features.presentation.screen.home

import android.annotation.SuppressLint
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.killjoy.stuntion.R
import com.killjoy.stuntion.features.presentation.navigation.BottomNavigationBar
import com.killjoy.stuntion.features.presentation.utils.components.HomeArticleItem
import com.killjoy.stuntion.features.presentation.utils.components.HomeDonationItem
import com.killjoy.stuntion.ui.stuntionUI.StuntionText
import com.killjoy.stuntion.ui.theme.LightBlue
import com.killjoy.stuntion.ui.theme.LightGray
import com.killjoy.stuntion.ui.theme.PrimaryBlue
import com.killjoy.stuntion.ui.theme.Type

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavController) {
    val viewModel = hiltViewModel<HomeViewModel>()
    val systemUiController = rememberSystemUiController()
    systemUiController.apply {
        setStatusBarColor(color = PrimaryBlue, darkIcons = true)
        setNavigationBarColor(color = Color.White, darkIcons = true)
    }

    Scaffold(
        bottomBar = { BottomNavigationBar(navController = navController) },
        modifier = Modifier.padding(bottom = (LocalConfiguration.current.screenHeightDp / 17).dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(bottom = (LocalConfiguration.current.screenHeightDp / 12).dp)
        ) {

            // Top section
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height((LocalConfiguration.current.screenHeightDp / 3).dp)
            ) {
                // Background
                AsyncImage(
                    model = R.drawable.ic_profile_bg,
                    contentDescription = "Background",
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height((LocalConfiguration.current.screenHeightDp / 3.5).dp)
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, top = 56.dp, end = 16.dp)
                ) {

                    // Logo and notification icon
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        // Stuntion logo
                        AsyncImage(
                            model = R.drawable.ic_app_text_logo,
                            contentDescription = "Logo",
                            colorFilter = ColorFilter.tint(Color.White),
                            modifier = Modifier.height(24.dp)
                        )

                        // Notification item
                        Image(
                            imageVector = Icons.Default.Notifications,
                            contentDescription = "Notification icon",
                            colorFilter = ColorFilter.tint(Color.White)
                        )
                    }

                    // Name
                    Row(modifier = Modifier.padding(top = 24.dp)) {
                        StuntionText(
                            text = "Good Morning!, ",
                            textStyle = Type.bodyLarge(),
                            color = Color.White
                        )
                        StuntionText(
                            text = "Maya Susanti",
                            textStyle = Type.titleMedium(),
                            color = Color.White
                        )
                    }

                    // Check text
                    StuntionText(
                        text = "Don't forget to check your",
                        textStyle = Type.titleLarge(),
                        color = Color.White,
                        modifier = Modifier
                            .padding(top = 16.dp)
                            .clickable {

                            }
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.clickable {

                        }
                    ) {
                        StuntionText(
                            text = "baby's health ",
                            textStyle = Type.titleLarge(),
                            color = Color.White,
                        )
                        Image(
                            imageVector = Icons.Default.KeyboardArrowRight,
                            contentDescription = "Arrow forward",
                            colorFilter = ColorFilter.tint(Color.White),
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
                AsyncImage(
                    model = R.drawable.iv_doll,
                    contentDescription = "Doll",
                    modifier = Modifier
                        .size(width = 98.dp, height = 160.dp)
                        .align(Alignment.BottomEnd)
                )
            }

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {

                // Child notes
                Card(
                    elevation = 4.dp,
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier.clickable {

                    }
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(horizontal = 18.dp, vertical = 16.dp)
                    ) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .background(
                                    color = LightBlue,
                                    shape = RoundedCornerShape(8.dp)
                                )
                                .size(40.dp)
                        ) {
                            AsyncImage(
                                model = R.drawable.ic_child_notes,
                                contentDescription = "Notes icon",
                                modifier = Modifier.size(24.dp)
                            )
                        }
                        StuntionText(
                            text = "Child Notes",
                            textStyle = Type.titleMedium(),
                            modifier = Modifier.padding(horizontal = 4.dp)
                        )
                    }
                }

                // Activity list
                Card(
                    elevation = 4.dp,
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier.clickable {

                    }
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(horizontal = 18.dp, vertical = 16.dp)
                    ) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .background(
                                    color = LightBlue,
                                    shape = RoundedCornerShape(8.dp)
                                )
                                .size(40.dp)
                        ) {
                            AsyncImage(
                                model = R.drawable.ic_activity,
                                contentDescription = "Activity icon",
                                modifier = Modifier.size(24.dp)
                            )
                        }
                        StuntionText(
                            text = "Activity List",
                            textStyle = Type.titleMedium(),
                            modifier = Modifier.padding(horizontal = 4.dp)
                        )
                    }
                }
            }

            // My Healthy Tips
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    StuntionText(text = "My Healty Tips", textStyle = Type.titleMedium())
                    StuntionText(
                        text = "View All",
                        textStyle = Type.labelMedium(),
                        color = PrimaryBlue,
                        modifier = Modifier.clickable {

                        }
                    )
                }

                StuntionText(
                    text = "The healthy tips are based on your baby's nutritional calculations",
                    textStyle = Type.bodySmall(),
                    color = LightGray
                )
            }

            Card(
                elevation = 3.dp,
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .clickable {

                    }
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.padding(bottom = 6.dp)
                ) {

                    // Tips card
                    Box(modifier = Modifier.fillMaxWidth()) {
                        AsyncImage(
                            model = R.drawable.iv_home_task,
                            contentDescription = "Tips image",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(220.dp)
                        )
                        Box(
                            modifier = Modifier
                                .padding(top = 16.dp)
                                .background(
                                    shape = RoundedCornerShape(
                                        topStart = 16.dp,
                                        bottomStart = 16.dp
                                    ),
                                    color = PrimaryBlue
                                )
                                .align(Alignment.TopEnd)
                        ) {
                            StuntionText(
                                text = "Take Action",
                                textStyle = Type.labelMedium(),
                                color = Color.White,
                                modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
                            )
                        }
                        StuntionText(
                            text = "If the child can walk, train and accompany the child when climbing the stairs",
                            textStyle = Type.titleSmall(),
                            color = Color.White,
                            modifier = Modifier
                                .align(Alignment.BottomStart)
                                .padding(start = 16.dp, end = 24.dp, bottom = 16.dp)
                        )
                    }

                    StuntionText(
                        text = "0 out of 5 actions",
                        textStyle = Type.bodySmall(),
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                    )
                }
            }

            // Donation
            StuntionText(
                text = "Let's Support With Additional Food",
                textStyle = Type.titleMedium(),
                modifier = Modifier.padding(start = 16.dp, top = 24.dp, bottom = 8.dp)
            )
            LazyRow(modifier = Modifier.padding(horizontal = 16.dp)) {
                items(5) {
                    HomeDonationItem(
                        title = "Milk For Babies Aged 1 Year",
                        location = "Malang, Jawa Timur",
                        currentValue = 1,
                        maxValue = 5,
                        deadlineDate = "02/15/2023",
                        fee = 50000,
                        modifier = Modifier
                            .width(196.dp)
                            .padding(end = 16.dp)
                    )
                }
            }

            // Information
            StuntionText(
                text = "Information For You",
                textStyle = Type.titleMedium(),
                modifier = Modifier.padding(start = 16.dp, top = 24.dp, bottom = 8.dp)
            )
            LazyRow(modifier = Modifier.padding(horizontal = 16.dp)) {
                items(viewModel.listOfBanner) {
                    AsyncImage(
                        model = it,
                        contentDescription = "Banner",
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier
                            .height(160.dp)
                            .padding(end = 16.dp)
                    )
                }
            }

            // Article
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    StuntionText(
                        text = "SmartStun Video Recommendation",
                        textStyle = Type.titleMedium()
                    )
                    StuntionText(
                        text = "View All",
                        textStyle = Type.labelMedium(),
                        color = PrimaryBlue,
                        modifier = Modifier.clickable {

                        }
                    )
                }

                StuntionText(
                    text = "Many informative videos that are suitable for you",
                    textStyle = Type.bodySmall(),
                    color = LightGray
                )
            }
            LazyRow(modifier = Modifier.padding(horizontal = 16.dp)) {
                items(5) {
                    HomeArticleItem(
                        modifier = Modifier.padding(end = 16.dp)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun HomePreview() {
    HomeScreen(navController = rememberNavController())
}