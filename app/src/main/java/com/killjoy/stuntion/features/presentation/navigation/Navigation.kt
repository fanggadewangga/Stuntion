package com.killjoy.stuntion.features.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.killjoy.stuntion.features.presentation.screen.auth.login.LoginScreen
import com.killjoy.stuntion.features.presentation.screen.auth.signup.SignupScreen
import com.killjoy.stuntion.features.presentation.utils.Screen

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.SignupScreen.route) {

        composable(route = Screen.SplashScreen.route) {

        }

        composable(route = Screen.OnboardScreen.route) {

        }

        composable(route = Screen.SignupScreen.route) {
            SignupScreen(navController = navController)
        }

        composable(route = Screen.LoginScreen.route) {
            LoginScreen(navController = navController)
        }
    }
}