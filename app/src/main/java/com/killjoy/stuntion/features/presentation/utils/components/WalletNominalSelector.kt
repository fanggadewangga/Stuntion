package com.killjoy.stuntion.features.presentation.utils.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.killjoy.stuntion.ui.theme.PrimaryBlue
import com.killjoy.stuntion.ui.theme.Type

@Composable
fun WalletNominalSelector(modifier: Modifier = Modifier, selected: Boolean, nominal: String, onSelected:() -> Unit) {
    Box(contentAlignment = Alignment.Center,
        modifier = modifier
            .width((LocalConfiguration.current.screenWidthDp / 3.4).dp)
            .height(48.dp)
            .border(
                width = if (selected) 2.dp else 1.dp,
                color = if (selected) PrimaryBlue else Color.LightGray,
                shape = RoundedCornerShape(100.dp)
            )
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(color = Color.LightGray),
                onClick = onSelected
            )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            StuntionText(
                text = "IDR",
                textStyle = Type.bodyLarge()
            )
            StuntionText(
                text = nominal,
                textStyle = Type.titleMedium()
            )
        }
    }
}