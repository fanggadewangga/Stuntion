package com.killjoy.stuntion.features.presentation.screen.account_management

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.killjoy.stuntion.features.data.util.Resource
import com.killjoy.stuntion.features.presentation.utils.Screen
import com.killjoy.stuntion.features.presentation.utils.components.AccountManagementItem
import com.killjoy.stuntion.features.presentation.utils.components.StuntionTopBar
import es.dmoral.toasty.Toasty

@Composable
fun AccountManagementScreen(navController: NavController) {

    val viewModel = hiltViewModel<AccountManagementViewModel>()
    val systemUiController = rememberSystemUiController()
    val logoutResponse = viewModel.logoutResponse.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        systemUiController.apply {
            setStatusBarColor(color = Color.White, darkIcons = true)
            setNavigationBarColor(color = Color.White, darkIcons = true)
        }
    }

    Column(modifier = Modifier.padding(top = 24.dp)) {

        when (logoutResponse.value) {
            is Resource.Error -> Toasty.error(context, logoutResponse.value.message.toString(), Toast.LENGTH_SHORT).show()
            is Resource.Empty ->{}
            is Resource.Loading -> {}
            is Resource.Success -> Toasty.success(context, "Logout success!", Toast.LENGTH_SHORT).show()
        }

        // Top Bar
        StuntionTopBar(
            title = "Account Management",
            onBackPressed = { navController.popBackStack() },
            isWithDivider = true
        )

        // Settings item
        Spacer(modifier = Modifier.height(16.dp))
        AccountManagementItem(
            title = "Change E-mail",
            onClick = {},
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 4.dp)
        )
        AccountManagementItem(
            title = "Change Password",
            onClick = {},
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 4.dp)
        )
        AccountManagementItem(
            title = "Logout",
            textColor = Color.Red,
            description = "Are you sure? You’ll have to log in again once you’re back.",
            onClick = {
                viewModel.logout()
                navController.navigate(Screen.LoginScreen.route) {
                    popUpTo(Screen.ProfileScreen.route) {
                        inclusive = true
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 4.dp)
        )
    }
}