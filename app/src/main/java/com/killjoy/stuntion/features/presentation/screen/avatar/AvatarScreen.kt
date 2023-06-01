package com.killjoy.stuntion.features.presentation.screen.avatar

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.killjoy.stuntion.features.data.util.Resource
import com.killjoy.stuntion.features.presentation.utils.Avatar
import com.killjoy.stuntion.features.presentation.utils.Screen
import com.killjoy.stuntion.features.presentation.utils.components.AvatarItem
import com.killjoy.stuntion.features.presentation.utils.components.StuntionButton
import com.killjoy.stuntion.features.presentation.utils.components.StuntionText
import com.killjoy.stuntion.ui.theme.PrimaryBlue
import com.killjoy.stuntion.ui.theme.Type

@Composable
fun AvatarScreen(navController: NavController) {
    val viewModel = hiltViewModel<AvatarViewModel>()
    val listOfAvatar: List<Avatar> = viewModel.listOfAvatar
    val updateAvatarResponse = viewModel.updateAvatarResponse.collectAsState()

    LaunchedEffect(updateAvatarResponse.value) {
        when (val response = updateAvatarResponse.value) {
            is Resource.Success -> {
                Log.d("Update Avatar", "Success")
                navController.navigate(Screen.LocationPermissionScreen.route) {
                    popUpTo(Screen.AvatarScreen.route) {
                        inclusive = true
                    }
                }
            }
            is Resource.Loading -> Log.d("Update Avatar", "Loading")
            is Resource.Error -> Log.d("Update Avatar", response.message.toString())
            is Resource.Empty -> Log.d("Update Avatar", "Empty")
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(PrimaryBlue)
    ) {
        // Avatar Text
        Spacer(modifier = Modifier.height(72.dp))
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            StuntionText(
                text = "Avatar",
                color = Color.White,
                textStyle = Type.displayMedium()
            )
        }

        // Box
        Spacer(modifier = Modifier.height(32.dp))
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Color.White, RoundedCornerShape(topStart = 48.dp, topEnd = 48.dp)
                )
                .padding(horizontal = 16.dp, vertical = 24.dp)
        ) {
            Column(modifier = Modifier.matchParentSize()) {

                // Choose avatar
                StuntionText(
                    text = "Choose Avatar",
                    textStyle = Type.headlineLarge(),
                )

                // Please choose
                Spacer(modifier = Modifier.height(8.dp))
                StuntionText(
                    text = "Please choose your avatar that will be displayed throughout the application",
                    textStyle = Type.bodyMedium(),
                    color = Color.Gray,
                    modifier = Modifier.width((LocalConfiguration.current.screenWidthDp * 0.75).dp)
                )

                // Avatars
                LazyVerticalGrid(
                    columns = GridCells.Fixed(3),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    content = {
                        items(count = listOfAvatar.size) {
                            AvatarItem(
                                avatar = listOfAvatar[it],
                                currentSelectedAvatar = viewModel.selectedAvatar
                            )
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp)
                )

                // Button
                Spacer(modifier = Modifier.height(32.dp))
                StuntionButton(
                    onClick = {
                        viewModel.updateUserAvatar()
                    }, modifier = Modifier.fillMaxWidth()
                ) {
                    StuntionText(
                        text = "Confirm", color = Color.White, textStyle = Type.labelLarge()
                    )
                }


                // Skip
                Spacer(modifier = Modifier.height(16.dp))
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    StuntionText(
                        text = "Skip",
                        color = PrimaryBlue,
                        textStyle = Type.bodyMedium(),
                        modifier = Modifier.clickable { navController.navigate(Screen.LocationPermissionScreen.route) }
                    )
                }
            }
        }

    }
}