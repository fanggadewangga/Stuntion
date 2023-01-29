package com.killjoy.stuntion.features.presentation.utils

sealed class Screen(val route: String) {
    object SplashScreen : Screen("splash_screen")
    object OnboardScreen : Screen("onboard_screen")

    // Auth
    object LoginScreen : Screen("login_screen")
    object SignupScreen : Screen("signup_screen")

    // General information
    object GeneralInformationScreen: Screen("general_information_screen")

    // Main screen
    object HomeScreen: Screen("home_screen")
    object ConsultScreen: Screen("consult_screen")
    object ActivityScreen: Screen("activity_screen")
    object ProfileScreen: Screen("profile_screen")
}
