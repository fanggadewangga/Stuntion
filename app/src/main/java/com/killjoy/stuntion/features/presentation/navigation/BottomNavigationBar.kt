package com.killjoy.stuntion.features.presentation.utils.components

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.killjoy.stuntion.features.presentation.navigation.NavigationItem

@Composable
fun BottomNavigationBar(navController: NavController) {
    val navigationItems = listOf(
        NavigationItem.Home,
        NavigationItem.Consult,
        NavigationItem.Empty,
        NavigationItem.Activity,
        NavigationItem.Profile
    )

    BottomNavigation(
        backgroundColor = Color.White,
        contentColor = Color.Gray
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        navigationItems.forEach { item ->
            if (item.route != null && item.icon != null && item.selectedIcon != null && item.title != null) {
                BottomNavigationItem(
                    icon = {
                        if (currentRoute == item.route)
                            Icon(
                                painter = painterResource(id = item.selectedIcon),
                                contentDescription = item.title,
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
                    selectedContentColor = Color.Blue,
                    unselectedContentColor = Color.Gray,
                    modifier = Modifier.height(88.dp)
                )
            } else {
                BottomNavigationItem(
                    icon = {},
                    label = { },
                    selected = false,
                    onClick = { },
                    enabled = false
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BottNavBar() {
    BottomNavigationBar(navController = rememberNavController())
}