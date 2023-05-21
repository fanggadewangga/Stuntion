package com.killjoy.stuntion.features.presentation.utils.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.killjoy.stuntion.ui.theme.PrimaryBlue

@Composable
fun StuntionSwitch(
    modifier: Modifier = Modifier,
    selected: Boolean,
    onSelectedChange: (Boolean) -> Unit,
) {
    Card(
        shape = RoundedCornerShape(16.dp), elevation = 0.dp,
        modifier = modifier
            .width(48.dp)
            .clickable(
                enabled = true,
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(color = Color.LightGray),
                onClick = { onSelectedChange(!selected) }
            )
    ) {
        Box(
            contentAlignment = if (selected) Alignment.TopEnd else Alignment.TopStart,
            modifier = Modifier
                .background(if (selected) PrimaryBlue else Color.White)
                .border(width = 2.dp, color = PrimaryBlue, shape = RoundedCornerShape(100.dp)),
        ) {
            Card(
                shape = CircleShape,
                elevation = 0.dp,
                modifier = Modifier
                    .padding(5.dp)
                    .size(16.dp),
            ) {
                Box(modifier = Modifier.background(if (selected) Color.White else PrimaryBlue))
            }
        }
    }
}
