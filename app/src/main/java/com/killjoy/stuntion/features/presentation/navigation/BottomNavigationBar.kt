package com.killjoy.stuntion.features.presentation.navigation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.killjoy.stuntion.ui.theme.PrimaryBlue

@Composable
fun BottomNavigationBar(navController: NavController) {
    val navigationItems = listOf(
        NavigationItem.Home,
        NavigationItem.Consult,
        NavigationItem.Check,
        NavigationItem.Support,
        NavigationItem.Profile
    )

    BottomNavigation(
        backgroundColor = Color.White,
        contentColor = Color.LightGray,
        elevation = 24.dp,
        modifier = Modifier.fillMaxWidth()
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        navigationItems.forEach { item ->
            BottomNavigationItem(
                icon = {
                    if (currentRoute == item.route)
                        Icon(
                            painter = painterResource(id = item.selectedIcon),
                            contentDescription = item.title,
                            tint = PrimaryBlue,
                            modifier = Modifier.size(24.dp)
                        )
                    else
                        Icon(
                            painter = painterResource(id = item.icon),
                            contentDescription = item.title,
                            modifier = Modifier.size(24.dp)
                        )
                },
                label = {
                    Text(
                        text = item.title,
                        fontSize = 11.sp,
                        fontWeight = if (currentRoute == item.route) FontWeight.Bold else FontWeight.Normal
                    )
                },
                onClick = {
                    navController.navigate(item.route) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                    }
                },
                selected = currentRoute == item.route,
                selectedContentColor = PrimaryBlue,
                unselectedContentColor = Color.LightGray,
                modifier = Modifier.height(56.dp)
            )
        }
    }
}