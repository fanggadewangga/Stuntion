package com.killjoy.stuntion.features.presentation.screen.support.supports

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.killjoy.stuntion.R
import com.killjoy.stuntion.features.data.util.Resource
import com.killjoy.stuntion.features.presentation.navigation.BottomNavigationBar
import com.killjoy.stuntion.features.presentation.utils.Screen
import com.killjoy.stuntion.features.presentation.utils.components.*
import com.killjoy.stuntion.features.presentation.utils.components.StuntionText
import com.killjoy.stuntion.ui.theme.LightBlue
import com.killjoy.stuntion.ui.theme.PrimaryBlue
import com.killjoy.stuntion.ui.theme.Type

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SupportScreen(navController: NavController) {
    val viewModel = hiltViewModel<SupportViewModel>()
    val donations = viewModel.donationResponse.collectAsStateWithLifecycle()

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
                            .height((LocalConfiguration.current.screenHeightDp / 3.8).dp)
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
                        Card(
                            elevation = 8.dp,
                            backgroundColor = PrimaryBlue,
                            shape = RoundedCornerShape(16.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    navController.navigate(Screen.SupportRequestTutorialScreen.route)
                                }
                                .shadow(
                                    elevation = 32.dp,
                                    shape = RoundedCornerShape(16.dp),
                                    ambientColor = Color.Black,
                                )
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(
                                        brush = Brush.verticalGradient(
                                            colors = listOf(
                                                Color(0xFF1B6BB1),
                                                Color(0xFF277CC7),
                                                PrimaryBlue
                                            ),
                                            startY = 0F,
                                            endY = Float.POSITIVE_INFINITY
                                        )
                                    )
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                                    modifier = Modifier
                                        .fillMaxWidth()

                                ) {
                                    Spacer(modifier = Modifier.width(10.dp))
                                    Box(
                                        contentAlignment = Alignment.Center,
                                        modifier = Modifier
                                            .size(48.dp)
                                            .background(
                                                color = LightBlue,
                                                shape = RoundedCornerShape(8.dp)
                                            )
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
                            }
                        }


                        // Search field
                        Spacer(modifier = Modifier.height(16.dp))
                        Card(
                            elevation = 8.dp,
                            shape = RoundedCornerShape(32.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .shadow(
                                    elevation = 32.dp,
                                    shape = RoundedCornerShape(32.dp),
                                    ambientColor = Color.Black,
                                )
                        ) {
                            StuntionSearchField(
                                valueState = viewModel.searchState.value,
                                onValueChange = {
                                    viewModel.searchState.value = it
                                    viewModel.searchDonations()
                                },
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
            }

            item {
                StuntionText(
                    text = "The Most Urgent",
                    textStyle = Type.titleMedium(),
                    modifier = Modifier.padding(16.dp)
                )
            }

            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .horizontalScroll(
                            rememberScrollState()
                        )
                ) {
                    when (donations.value) {
                        is Resource.Loading -> {
                            for (i in 1..5)
                                HomeDonationItemShimmer(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 16.dp, vertical = 4.dp)
                                )
                        }

                        is Resource.Empty -> {

                        }

                        is Resource.Success -> {
                            donations.value.data!!
                                .sortedBy { it.dayRemaining }
                                .take(5)
                                .forEach { donation ->
                                    HomeDonationItem(
                                        donation = donation,
                                        onClick = {
                                            navController.currentBackStackEntry?.savedStateHandle?.set(
                                                key = "donationId",
                                                value = donation.donationId
                                            )
                                            navController.navigate(Screen.SupportDetailScreen.route)
                                        },
                                        modifier = Modifier
                                            .width(196.dp)
                                            .padding(end = 16.dp)
                                    )
                                }
                        }

                        is Resource.Error -> {

                        }
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
                            navController.navigate(Screen.SupportScreen.route)
                        }
                    )
                }
            }

            when (donations.value) {
                is Resource.Loading -> {
                    items(6) {
                        DonationItemShimmer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp, vertical = 4.dp)
                        )
                    }
                }

                is Resource.Empty -> {

                }

                is Resource.Success -> {
                    items(
                        donations.value.data!!
                            .shuffled()
                            .take(2)
                    ) { donation ->
                        SuitableDonationItem(
                            donation = donation,
                            onClick = {
                                navController.currentBackStackEntry?.savedStateHandle?.set(
                                    key = "donationId",
                                    value = donation.donationId
                                )
                                navController.navigate(Screen.SupportDetailScreen.route)
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                        )
                    }
                }

                is Resource.Error -> {

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