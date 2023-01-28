package com.killjoy.stuntion.ui.stuntionUI

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.killjoy.stuntion.ui.theme.Type

@Composable
fun StuntionText(
    modifier: Modifier = Modifier,
    text: String,
    textAlign: TextAlign = TextAlign.Start,
    color: Color = Color.Black,
    textStyle: TextStyle = Type.bodyMedium(),
    overflow: TextOverflow = TextOverflow.Visible,
    maxLine: Int = Int.MAX_VALUE,
) {
    Text(
        modifier = modifier,
        text = text,
        textAlign = textAlign,
        color = color,
        style = textStyle,
        overflow = overflow,
        maxLines = maxLine
    )
}