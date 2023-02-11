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
    object Check : NavigationItem("check_screen", R.drawable.ic_check, R.drawable.ic_check, "Check")
    object Support: NavigationItem("support_screen", R.drawable.ic_support, R.drawable.ic_activity, "Support")
    object Profile: NavigationItem("profile_screen", R.drawable.ic_profile, R.drawable.ic_profile, "Profile")
}
