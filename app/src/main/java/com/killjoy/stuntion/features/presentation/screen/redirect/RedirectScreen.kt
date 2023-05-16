package com.killjoy.stuntion.features.presentation.screen.redirect

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.killjoy.stuntion.R
import coil.compose.AsyncImage
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.killjoy.stuntion.features.presentation.navigation.BottomNavigationBar
import com.killjoy.stuntion.features.presentation.utils.Screen
import com.killjoy.stuntion.features.presentation.utils.components.StuntionButton
import com.killjoy.stuntion.features.presentation.utils.components.StuntionText
import com.killjoy.stuntion.ui.theme.PrimaryBlue
import com.killjoy.stuntion.ui.theme.Type

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun RedirectScreen(navController: NavController) {

    val systemUiController = rememberSystemUiController()
    LaunchedEffect(Unit) {
        systemUiController.apply {
            setStatusBarColor(color = Color.White, darkIcons = true)
            setNavigationBarColor(color = Color.White, darkIcons = true)
        }
    }

    Scaffold(
        bottomBar = { BottomNavigationBar(navController = navController) },
        modifier = Modifier.padding(bottom = (LocalConfiguration.current.screenHeightDp / 17).dp)
    ) {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                AsyncImage(
                    model = R.drawable.iv_redirect,
                    contentDescription = "Redirect",
                    modifier = Modifier.size(
                        (LocalConfiguration.current.screenHeightDp * 0.4).dp
                    )
                )
                StuntionText(text = "Please Login or Create Account", textStyle = Type.titleLarge())

                // Button
                Spacer(modifier = Modifier.height(2.dp))
                StuntionButton(
                    onClick = {
                        navController.navigate(Screen.LoginScreen.route)
                    }, modifier = Modifier.width((LocalConfiguration.current.screenWidthDp / 2).dp)
                ) {
                    StuntionText(
                        text = "Log in", color = Color.White, textStyle = Type.labelLarge()
                    )
                }

                // Or
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.width((LocalConfiguration.current.screenWidthDp / 2).dp)
                ) {
                    Divider(
                        color = Color.Gray,
                        thickness = 1.dp,
                        modifier = Modifier.width((LocalConfiguration.current.screenWidthDp / 5).dp)
                    )
                    StuntionText(
                        text = "OR",
                        color = Color.Gray,
                        textStyle = Type.bodyLarge(),
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                    Divider(
                        color = Color.Gray,
                        thickness = 1.dp,
                        modifier = Modifier.width((LocalConfiguration.current.screenWidthDp / 5).dp)
                    )
                }

                // Sign up
                StuntionButton(
                    backgroundColor = Color.White,
                    borderColor = PrimaryBlue,
                    borderWidth = 0.5.dp,
                    onClick = {
                        navController.navigate(Screen.SignupScreen.route)
                    },
                    modifier = Modifier.width((LocalConfiguration.current.screenWidthDp / 2).dp)
                ) {
                    StuntionText(
                        text = "Sign up",
                        color = PrimaryBlue,
                        textStyle = Type.labelLarge()
                    )
                }
            }
        }
    }

}