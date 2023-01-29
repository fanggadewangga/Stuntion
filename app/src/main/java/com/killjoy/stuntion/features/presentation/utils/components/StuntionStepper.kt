package com.killjoy.stuntion.features.presentation.utils.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun StuntionStepper(
    isComplete : Boolean
) {
    val color = if (isComplete) Color.Black else Color.Gray
    
    Box {
        Divider(
            modifier = Modifier.align(Alignment.CenterStart).width(48.dp),
            color = color,
            thickness = 2.dp
        )
    }
}