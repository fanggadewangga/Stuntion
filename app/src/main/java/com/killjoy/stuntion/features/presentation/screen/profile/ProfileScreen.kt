package com.killjoy.stuntion.features.presentation.screen.profile

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.killjoy.stuntion.R
import com.killjoy.stuntion.features.presentation.navigation.BottomNavigationBar
import com.killjoy.stuntion.features.presentation.utils.ProfileSetting
import com.killjoy.stuntion.features.presentation.utils.components.ProfileSettingItem
import com.killjoy.stuntion.ui.stuntionUI.StuntionText
import com.killjoy.stuntion.ui.theme.LightBlue
import com.killjoy.stuntion.ui.theme.PrimaryBlue
import com.killjoy.stuntion.ui.theme.Type

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ProfileScreen(navController: NavController) {
    Scaffold(
        bottomBar = { BottomNavigationBar(navController = navController) },
        modifier = Modifier.padding(bottom = (LocalConfiguration.current.screenHeightDp / 17).dp)
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            item {
                Box(modifier = Modifier.fillMaxWidth()) {
                    // Blue Background
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height((LocalConfiguration.current.screenHeightDp / 4).dp)
                    ) {
                        AsyncImage(
                            model = R.drawable.ic_profile_bg,
                            contentDescription = "Profile background",
                            contentScale = ContentScale.FillBounds,
                            modifier = Modifier.height((LocalConfiguration.current.screenHeightDp / 4).dp)
                        )
                    }

                    // Profile Section
                    Column(
                        Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, top = 48.dp, end = 16.dp, bottom = 16.dp)
                    ) {

                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 16.dp)
                        ) {

                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(16.dp)
                            ) {
                                // Image
                                AsyncImage(
                                    model = R.drawable.iv_avatar,
                                    contentDescription = "User Avatar",
                                    modifier = Modifier.size(83.dp)
                                )

                                Column(
                                    verticalArrangement = Arrangement.spacedBy(4.dp)
                                ) {

                                    // Name
                                    StuntionText(
                                        text = "Maya Susanti",
                                        textStyle = Type.titleLarge(),
                                        color = Color.White
                                    )

                                    // Address
                                    StuntionText(
                                        text = "East Java, Malang",
                                        textStyle = Type.bodyMedium(),
                                        color = Color.White
                                    )
                                }
                            }

                            // Setting Icon
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier
                                    .size(32.dp)
                                    .background(color = LightBlue, shape = RoundedCornerShape(6.dp))
                            ) {
                                Image(painter = painterResource(id = R.drawable.ic_pen),
                                    contentDescription = "Pen Icon",
                                    colorFilter = ColorFilter.tint(
                                        PrimaryBlue
                                    ),
                                    modifier = Modifier
                                        .size(24.dp)
                                        .clickable {

                                        }
                                )
                            }
                        }

                        // Level Section
                        Spacer(modifier = Modifier.height(16.dp))
                        Card(
                            shape = RoundedCornerShape(16.dp),
                            elevation = 4.dp,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp)
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
                                        modifier = Modifier.align(
                                            Alignment.Center
                                        )
                                    )

                                    // Information
                                    Image(
                                        imageVector = Icons.Default.Info,
                                        contentDescription = "Info Icon",
                                        colorFilter = ColorFilter.tint(
                                            PrimaryBlue
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
                                        modifier = Modifier.wrapContentSize()
                                    )
                                    StuntionText(
                                        text = "10",
                                        textStyle = Type.displaySmall(),
                                        color = Color.White
                                    )
                                }

                                StuntionText(
                                    text = "Successfully completing this level will unleash a \n" +
                                            "mystery box which contains several prizes",
                                    color = Color.LightGray,
                                    textAlign = TextAlign.Center,
                                    textStyle = Type.bodySmall()
                                )

                                // Linear Progress
                                LinearProgressIndicator(
                                    progress = 4950 / 10000f,
                                    backgroundColor = Color.LightGray,
                                    color = PrimaryBlue,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(12.dp)
                                        .clip(shape = RoundedCornerShape(16.dp))
                                )

                                // XP
                                StuntionText(
                                    text = "4.950 / 10.000 XP",
                                    textStyle = Type.bodyMedium()
                                )
                            }
                        }
                    }
                }
            }

            // My Activity
            item {
                StuntionText(
                    text = "My Activity",
                    textStyle = Type.titleMedium(),
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                )
                ProfileSettingItem(
                    item = ProfileSetting(icon = R.drawable.ic_child_notes, title = "Child Notes"),
                    onClick = { /*TODO*/ },
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                )
                ProfileSettingItem(
                    item = ProfileSetting(icon = R.drawable.ic_activity, title = "Activity List"),
                    onClick = { /*TODO*/ },
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                )
                ProfileSettingItem(
                    item = ProfileSetting(
                        icon = R.drawable.ic_bookmark_filled,
                        title = "Favorite Experts"
                    ),
                    onClick = { /*TODO*/ },
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                )
            }

            // My Account
            item {
                Spacer(modifier = Modifier.height(16.dp))
                StuntionText(
                    text = "My Account",
                    textStyle = Type.titleMedium(),
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                )
                ProfileSettingItem(
                    item = ProfileSetting(icon = R.drawable.ic_chest, title = "Level & Reward"),
                    onClick = { /*TODO*/ },
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                )
                ProfileSettingItem(
                    item = ProfileSetting(icon = R.drawable.ic_setting, title = "Settings"),
                    onClick = { /*TODO*/ },
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                )
                ProfileSettingItem(
                    item = ProfileSetting(icon = R.drawable.ic_wallet, title = "Payment Methods"),
                    onClick = { /*TODO*/ },
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                )
            }

            // General
            item {
                Spacer(modifier = Modifier.height(16.dp))
                StuntionText(
                    text = "General",
                    textStyle = Type.titleMedium(),
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                )
                ProfileSettingItem(
                    item = ProfileSetting(icon = R.drawable.ic_terms, title = "Term & Privacy"),
                    onClick = { /*TODO*/ },
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                )
                ProfileSettingItem(
                    item = ProfileSetting(icon = R.drawable.ic_star, title = "Rate Stuntion App"),
                    onClick = { /*TODO*/ },
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                )
                ProfileSettingItem(
                    item = ProfileSetting(icon = R.drawable.ic_help, title = "Help Centre"),
                    onClick = { /*TODO*/ },
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                )
                ProfileSettingItem(
                    item = ProfileSetting(icon = R.drawable.ic_about, title = "About Us"),
                    onClick = { /*TODO*/ },
                    modifier = Modifier.padding(
                        start = 16.dp,
                        top = 8.dp,
                        end = 16.dp,
                        bottom = (LocalConfiguration.current.screenWidthDp / 6).dp
                    )
                )
            }
        }
    }
}

@Preview
@Composable
fun ProfileScreenPreview() {
    ProfileScreen(navController = rememberNavController())
}