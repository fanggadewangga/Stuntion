package com.killjoy.stuntion.features.presentation.utils.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.killjoy.stuntion.ui.theme.LightGray
import com.killjoy.stuntion.ui.theme.PrimaryBlue
import com.killjoy.stuntion.ui.theme.Type

@Composable
fun CategoryChip(
    category: String,
    selected: String,
    onSelected: (String) -> Unit,
) {

    val isSelected = selected == category
    val backgroundColor = if (isSelected) PrimaryBlue else Color.White
    val textColor = if (isSelected) Color.White else LightGray

    Box(
        contentAlignment = Alignment.TopCenter,
        modifier = Modifier
            .wrapContentWidth()
            .padding(end = 8.dp)
            .background(color = backgroundColor,shape = RoundedCornerShape(100.dp))
            .border(width = 1.dp, color = PrimaryBlue, shape = RoundedCornerShape(100.dp))
            .clickable {
                onSelected(category)
            }
    ) {
        StuntionText(
            text = category,
            textStyle = Type.labelLarge(),
            color = textColor,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )
    }
}