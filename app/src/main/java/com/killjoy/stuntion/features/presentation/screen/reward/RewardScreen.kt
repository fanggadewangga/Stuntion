package com.killjoy.stuntion.features.presentation.screen.reward

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.killjoy.stuntion.R
import com.killjoy.stuntion.features.data.util.Resource
import com.killjoy.stuntion.features.presentation.utils.components.ErrorLayout
import com.killjoy.stuntion.features.presentation.utils.components.RewardItem
import com.killjoy.stuntion.features.presentation.utils.components.StuntionTopBar
import com.killjoy.stuntion.ui.stuntionUI.StuntionText
import com.killjoy.stuntion.ui.theme.PrimaryBlue
import com.killjoy.stuntion.ui.theme.Type

@Composable
fun RewardScreen(navController: NavController) {
    val viewModel = hiltViewModel<RewardViewModel>()
    val systemUiController = rememberSystemUiController()
    val userResponse = viewModel.userResponse.collectAsState()

    LaunchedEffect(true) {
        systemUiController.apply {
            setStatusBarColor(color = Color.White, darkIcons = true)
            setNavigationBarColor(color = Color.White, darkIcons = true)
        }
    }

    when (userResponse.value) {
        is Resource.Loading -> {}
        is Resource.Error -> ErrorLayout()
        is Resource.Empty -> ErrorLayout("Something went wrong")
        is Resource.Success -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.White)
                    .padding(
                        top = 24.dp,
                        bottom = (LocalConfiguration.current.screenHeightDp / 17).dp
                    )
            ) {
                // Top bar
                StuntionTopBar(
                    title = "Level & Reward",
                    onBackPressed = { navController.popBackStack() },
                    isWithDivider = true
                )

                Column(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxSize()
                ) {

                    // Level section
                    Card(
                        shape = RoundedCornerShape(16.dp),
                        elevation = 4.dp,
                        backgroundColor = PrimaryBlue,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, end = 16.dp, top = 12.dp)
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(6.dp),
                            modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
                        ) {

                            Box(modifier = Modifier.fillMaxWidth()) {
                                // My Level
                                StuntionText(
                                    text = "My Level",
                                    textStyle = Type.titleMedium(),
                                    color = Color.White,
                                    modifier = Modifier.align(
                                        Alignment.Center
                                    )
                                )

                                // Information
                                Image(
                                    imageVector = Icons.Default.Info,
                                    contentDescription = "Info Icon",
                                    colorFilter = ColorFilter.tint(
                                        Color.White
                                    ),
                                    modifier = Modifier
                                        .align(Alignment.TopEnd)
                                        .clickable {

                                        }
                                )
                            }

                            // Level
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier.wrapContentSize()
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.ic_level_bg),
                                    contentDescription = "Level Background",
                                    colorFilter = ColorFilter.tint(Color.White),
                                    modifier = Modifier.wrapContentSize()
                                )
                                StuntionText(
                                    text = userResponse.value.data!!.level.toString(),
                                    textStyle = Type.displaySmall(),
                                    color = PrimaryBlue
                                )
                            }

                            StuntionText(
                                text = "Successfully completing this level will unleash a \n" +
                                        "mystery box which contains several prizes",
                                color = Color.White,
                                textAlign = TextAlign.Center,
                                textStyle = Type.bodySmall()
                            )

                            // Linear Progress
                            LinearProgressIndicator(
                                progress = userResponse.value.data!!.xp / (userResponse.value.data!!.level * 1000f),
                                backgroundColor = Color.LightGray,
                                color = Color.White,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(12.dp)
                                    .clip(shape = RoundedCornerShape(16.dp))
                            )

                            // XP
                            StuntionText(
                                text = "${userResponse.value.data!!.xp} / ${userResponse.value.data!!.level * 1000} XP",
                                textStyle = Type.bodyMedium(),
                                color = Color.White
                            )
                        }
                    }

                    StuntionText(
                        text = "Latest Rewards",
                        textStyle = Type.titleMedium(),
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                    LazyColumn {
                        item {
                            RewardItem(
                                iconUrl = "https://firebasestorage.googleapis.com/v0/b/stuntion-a32cc.appspot.com/o/reward%2Fiv_discount_50.png?alt=media&token=779c6ef2-54d9-4fcf-8491-bd9130051478",
                                title = "50% Discount Private Consultation With Midwife",
                                level = 10,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 8.dp)
                            )
                        }
                        item {
                            RewardItem(
                                iconUrl = "https://firebasestorage.googleapis.com/v0/b/stuntion-a32cc.appspot.com/o/reward%2Fiv_gopay_200.png?alt=media&token=b7d5a1eb-aa18-4a5e-935e-54779a46674c",
                                title = "Get a Gopay Balance of IDR 200,000",
                                level = 10,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 8.dp)
                            )
                        }
                        item {
                            RewardItem(
                                iconUrl = "https://firebasestorage.googleapis.com/v0/b/stuntion-a32cc.appspot.com/o/reward%2Fiv_discount_75.png?alt=media&token=600c5895-ffd0-482a-931c-eb7656174089c",
                                title = "75% Discount Private Consultation With General Practitioners",
                                level = 11,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 8.dp)
                            )
                        }
                        item {
                            RewardItem(
                                iconUrl = "https://firebasestorage.googleapis.com/v0/b/stuntion-a32cc.appspot.com/o/reward%2Fiv_gopay_200.png?alt=media&token=b7d5a1eb-aa18-4a5e-935e-54779a46674c",
                                title = "Get a Gopay Balance of IDR 200,000",
                                level = 11,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 8.dp)
                            )
                        }
                        item {
                            RewardItem(
                                iconUrl = "https://firebasestorage.googleapis.com/v0/b/stuntion-a32cc.appspot.com/o/reward%2Fiv_discount_75.png?alt=media&token=600c5895-ffd0-482a-931c-eb7656174089c",
                                title = "75% Discount Private Consultation With General Practitioners",
                                level = 12,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 8.dp)
                            )
                        }
                        item {
                            RewardItem(
                                iconUrl = "https://firebasestorage.googleapis.com/v0/b/stuntion-a32cc.appspot.com/o/reward%2Fiv_gopay_200.png?alt=media&token=b7d5a1eb-aa18-4a5e-935e-54779a46674c",
                                title = "Get a Gopay Balance of IDR 200,000",
                                level = 14,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 4.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}