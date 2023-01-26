package com.killjoy.stuntion.features.presentation.utils

import androidx.compose.runtime.Composable
import androidx.navigation.Navigation
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.SplashScreen.route) {

        composable(route = Screen.SplashScreen.route) {

        }

        composable(route = Screen.OnboardScreen.route) {

        }

        composable(route = Screen.SignupScreen.route) {

        }

        composable(route = Screen.LoginScreen.route) {

        }
    }
}