package com.killjoy.stuntion.features.presentation.screen.support_tutorial

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.killjoy.stuntion.R
import com.killjoy.stuntion.features.presentation.utils.Screen
import com.killjoy.stuntion.features.presentation.utils.components.StuntionButton
import com.killjoy.stuntion.ui.stuntionUI.StuntionText
import com.killjoy.stuntion.ui.theme.LightBlue
import com.killjoy.stuntion.ui.theme.PrimaryBlue
import com.killjoy.stuntion.ui.theme.Type

@Composable
fun SupportTutorialScreen(navController: NavController) {
    val systemUiController = rememberSystemUiController()
    systemUiController.apply {
        setStatusBarColor(color = Color.Transparent, darkIcons = true)
        setNavigationBarColor(color = Color.White, darkIcons = true)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(vertical = (LocalConfiguration.current.screenHeightDp / 14).dp)
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Image(
                imageVector = Icons.Default.ArrowBack, contentDescription = "Back button",
                modifier = Modifier
                    .size(40.dp)
                    .align(Alignment.TopStart)
                    .padding(start = 16.dp, top = 16.dp)
            )
            AsyncImage(
                model = R.drawable.iv_support_request_tutorial,
                contentDescription = "Illustration",
                modifier = Modifier
                    .height(240.dp)
                    .align(Alignment.TopCenter)
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            StuntionText(text = "Request For Help", textStyle = Type.titleLarge())
            StuntionText(
                text = "At Stuntion there are millions of good \npeople who will help you",
                textStyle = Type.bodyLarge(),
                textAlign = TextAlign.Center,
            )
            StuntionButton(
                onClick = {
                    navController.navigate(Screen.RequestHelpScreen.route)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                StuntionText(
                    text = "Request Help Right Now",
                    color = Color.White,
                    textStyle = Type.labelLarge()
                )
            }
            StuntionButton(
                backgroundColor = Color.White,
                borderColor = PrimaryBlue,
                borderWidth = 0.5.dp,
                onClick = {
                    navController.navigate(Screen.CardVerificationScreen.route)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                StuntionText(
                    text = "Identity Verification",
                    color = PrimaryBlue,
                    textStyle = Type.labelLarge()
                )
            }

            StuntionText(
                text = "How to Request a Help in Stuntion",
                textStyle = Type.titleMedium(),
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(start = 16.dp, top = 24.dp)
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(40.dp)
                        .background(color = PrimaryBlue, shape = CircleShape)
                ) {
                    StuntionText(text = "1", color = Color.White, textStyle = Type.headlineSmall())
                }
                Card(
                    shape = RoundedCornerShape(16.dp),
                    elevation = 8.dp,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        modifier = Modifier.padding(16.dp)
                    ) {
                        StuntionText(
                            text = "Fill Out The \"Request Help\" Form",
                            textStyle = Type.titleSmall()
                        )
                        StuntionText(
                            text = "Fill out the form completely following the given instructions.",
                            textStyle = Type.bodyMedium()
                        )
                    }
                }
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(40.dp)
                        .background(color = PrimaryBlue, shape = CircleShape)
                ) {
                    StuntionText(text = "2", color = Color.White, textStyle = Type.headlineSmall())
                }
                Card(
                    shape = RoundedCornerShape(16.dp),
                    elevation = 8.dp,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        modifier = Modifier.padding(16.dp)
                    ) {
                        StuntionText(
                            text = "Verify Your Request For Help",
                            textStyle = Type.titleSmall()
                        )
                        StuntionText(
                            text = "Conduct identity verification in order to request for help.",
                            textStyle = Type.bodyMedium()
                        )
                    }
                }
            }


            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(40.dp)
                        .background(color = PrimaryBlue, shape = CircleShape)
                ) {
                    StuntionText(text = "3", color = Color.White, textStyle = Type.headlineSmall())
                }
                Card(
                    shape = RoundedCornerShape(16.dp),
                    elevation = 8.dp,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        modifier = Modifier.padding(16.dp)
                    ) {
                        StuntionText(
                            text = "Share Your Request For Help ",
                            textStyle = Type.titleSmall()
                        )
                        StuntionText(
                            text = "After your request for help has been successfully verified, share your request for help as often as possible",
                            textStyle = Type.bodyMedium()
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SupportTutorialPreview() {
    SupportTutorialScreen(navController = rememberNavController())
}