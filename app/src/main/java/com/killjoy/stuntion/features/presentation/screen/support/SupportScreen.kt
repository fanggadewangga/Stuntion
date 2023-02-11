package com.killjoy.stuntion.features.presentation.screen.support

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.killjoy.stuntion.features.presentation.navigation.BottomNavigationBar
import com.killjoy.stuntion.ui.stuntionUI.StuntionText

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SupportScreen(navController: NavController) {
    val viewModel = hiltViewModel<SupportViewModel>()
    Scaffold(
       bottomBar = { BottomNavigationBar(navController = navController) },
        modifier = Modifier.padding(bottom = (LocalConfiguration.current.screenHeightDp / 17).dp)
    ) {
        Surface(modifier = Modifier.fillMaxSize()) {
            StuntionText(text = "Home")
        }
    }
}