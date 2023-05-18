package com.killjoy.stuntion.features.presentation.utils.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.killjoy.stuntion.ui.theme.PrimaryBlue
import com.killjoy.stuntion.ui.theme.Type

@Composable
fun WalletNominalSelector(modifier: Modifier = Modifier, selected: Boolean, nominal: String) {
    Box(contentAlignment = Alignment.Center,
        modifier = modifier
            .width((LocalConfiguration.current.screenWidthDp / 3.4).dp)
            .height(48.dp)
            .border(
                width = 1.dp,
                color = if (selected) PrimaryBlue else Color.LightGray,
                shape = RoundedCornerShape(100.dp)
            )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            //modifier = Modifier.padding(horizontal = 6.dp, vertical = 4.dp)
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