package com.killjoy.stuntion.features.presentation.utils.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.killjoy.stuntion.R
import com.killjoy.stuntion.ui.theme.Type

@Composable
fun EmptyActivityScreen() {
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            AsyncImage(
                model = R.drawable.ic_empty,
                contentDescription = "Empty icon",
                modifier = Modifier
                    .height((LocalConfiguration.current.screenHeightDp * 0.5).dp)
                    .width((LocalConfiguration.current.screenHeightDp * 0.35).dp)
            )
            StuntionText(text = "Nothing's Happening Now", textStyle = Type.titleLarge())
            StuntionText(
                text = "Discover what's new on the app",
                textStyle = Type.bodySmall(),
                color = Color.Gray,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}