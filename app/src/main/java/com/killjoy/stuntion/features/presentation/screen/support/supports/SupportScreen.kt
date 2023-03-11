package com.killjoy.stuntion.features.presentation.screen.support.supports

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.killjoy.stuntion.R
import com.killjoy.stuntion.features.presentation.navigation.BottomNavigationBar
import com.killjoy.stuntion.features.presentation.utils.Screen
import com.killjoy.stuntion.features.presentation.utils.components.StuntionSearchField
import com.killjoy.stuntion.ui.stuntionUI.StuntionText
import com.killjoy.stuntion.ui.theme.LightBlue
import com.killjoy.stuntion.ui.theme.PrimaryBlue
import com.killjoy.stuntion.ui.theme.Type

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SupportScreen(navController: NavController) {
    val viewModel = hiltViewModel<SupportViewModel>()

    Scaffold(
        bottomBar = { BottomNavigationBar(navController = navController) },
        modifier = Modifier.padding(bottom = (LocalConfiguration.current.screenHeightDp / 17).dp)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = (LocalConfiguration.current.screenHeightDp / 14).dp)
        ) {
            item {
                Box(modifier = Modifier.fillMaxWidth()) {
                    // Blue Background
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height((LocalConfiguration.current.screenHeightDp / 3.2).dp)
                            .background(PrimaryBlue)
                    ) {}

                    // Support Section
                    Column(
                        Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                    ) {
                        // Top Bar
                        Spacer(modifier = Modifier.height(24.dp))
                        Box(
                            contentAlignment = Alignment.TopCenter,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 24.dp)
                        ) {
                            Image(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Arrow Back",
                                colorFilter = ColorFilter.tint(Color.White),
                                modifier = Modifier
                                    .align(
                                        Alignment.CenterStart
                                    )
                                    .clickable {

                                    }
                            )
                            StuntionText(
                                text = "Support",
                                textStyle = Type.titleLarge(),
                                color = Color.White
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))

                        // Screen description
                        Spacer(modifier = Modifier.height(10.dp))
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .border(
                                    width = 1.dp,
                                    color = Color.White,
                                    shape = RoundedCornerShape(16.dp)
                                )
                                .clickable {
                                    navController.navigate(Screen.SupportRequestTutorialScreen.route)
                                }
                        ) {
                            Spacer(modifier = Modifier.width(10.dp))
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier
                                    .size(48.dp)
                                    .background(color = LightBlue, shape = RoundedCornerShape(8.dp))
                            ) {
                                AsyncImage(
                                    model = R.drawable.ic_age,
                                    contentDescription = "Baby icon",
                                    modifier = Modifier
                                        .size(48.dp)
                                        .padding(8.dp)
                                )
                            }
                            Column(
                                verticalArrangement = Arrangement.spacedBy(4.dp),
                                modifier = Modifier.padding(vertical = 18.dp)
                            ) {
                                StuntionText(
                                    text = "Request For Help!",
                                    color = Color.White,
                                    textStyle = Type.titleMedium()
                                )
                                StuntionText(
                                    text = "Complete the data to get baby's nutrition assistance",
                                    color = Color.White,
                                    textStyle = Type.bodySmall()
                                )
                            }
                        }

                        // Search field
                        Spacer(modifier = Modifier.height(16.dp))
                        StuntionSearchField(
                            valueState = viewModel.searchState.value,
                            onValueChange = { viewModel.searchState.value = it },
                            placeholder = "who will you help today?",
                            borderColor = Color.Transparent,
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Search,
                                    contentDescription = "Search Icon",
                                    tint = Color.Gray
                                )
                            }
                        )
                    }
                }
            }

            item {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    StuntionText(text = "The Most Urgent", textStyle = Type.titleMedium())

                    StuntionText(
                        text = "View All",
                        textStyle = Type.labelMedium(),
                        color = PrimaryBlue,
                        modifier = Modifier.clickable {

                        }
                    )
                }
            }


            item {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        StuntionText(
                            text = "The Suitable Family You Can Help",
                            textStyle = Type.titleMedium()
                        )
                        StuntionText(
                            text = "You can help people around you",
                            textStyle = Type.bodySmall(),
                            color = Color.LightGray
                        )
                    }

                    StuntionText(
                        text = "View All",
                        textStyle = Type.labelMedium(),
                        color = PrimaryBlue,
                        modifier = Modifier.clickable {

                        }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun SupportScreenPreview() {
    SupportScreen(navController = rememberNavController())
}