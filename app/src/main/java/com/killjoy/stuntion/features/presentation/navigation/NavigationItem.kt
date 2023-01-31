package com.killjoy.stuntion.features.presentation.navigation

import com.killjoy.stuntion.R

sealed class NavigationItem(
    val route: String,
    val icon: Int,
    val selectedIcon: Int,
    val title: String
) {
    object Home: NavigationItem("home_screen", R.drawable.ic_home, R.drawable.ic_home, "Home" )
    object Consult: NavigationItem("consult_screen", R.drawable.ic_consult, R.drawable.ic_consult, "Consult")
    object Check : NavigationItem("calculator_screen", R.drawable.ic_check, R.drawable.ic_check, "Check")
    object Activity: NavigationItem("activity_screen", R.drawable.ic_activity, R.drawable.ic_activity, "Activity")
    object Profile: NavigationItem("profile_screen", R.drawable.ic_profile, R.drawable.ic_profile, "Profile")
}
