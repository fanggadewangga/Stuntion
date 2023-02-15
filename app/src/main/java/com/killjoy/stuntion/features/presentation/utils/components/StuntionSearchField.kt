package com.killjoy.stuntion.features.presentation.utils.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.killjoy.stuntion.ui.stuntionUI.StuntionText
import com.killjoy.stuntion.ui.theme.LightGray
import com.killjoy.stuntion.ui.theme.Type

@Composable
fun StuntionSearchField(
    modifier: Modifier = Modifier,
    leadingIcon: (@Composable () -> Unit)? = null,
    trailingIcon: (@Composable () -> Unit)? = null,
    valueState: String,
    placeholder: String,
    borderColor: Color = LightGray,
    textStyle: TextStyle = Type.bodyLarge(),
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    onValueChange:(String) -> Unit,
) {
    Card(
        shape = RoundedCornerShape(100.dp),
        backgroundColor = Color.White,
        border = BorderStroke(width = 1.dp, color = borderColor),
        modifier = modifier
            .fillMaxWidth()
    ) {
        BasicTextField(
            modifier = modifier
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(16.dp)
                )
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 4.dp),
            value = valueState,
            onValueChange = onValueChange,
            keyboardActions = keyboardActions,
            keyboardOptions = keyboardOptions,
            singleLine = true,
            cursorBrush = SolidColor(Color.Black),
            textStyle = textStyle,
            decorationBox = { innerTextField ->
                Row(
                    modifier.height(35.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    if (leadingIcon != null) leadingIcon()
                    Spacer(modifier = Modifier.width(4.dp))
                    Box(
                        modifier = Modifier
                            .weight(1f)
                    ) {
                        if (valueState.isEmpty())
                            StuntionText(text = placeholder, textStyle = textStyle, color = LightGray)
                        innerTextField()
                    }
                    if (trailingIcon != null) trailingIcon()
                }
            }
        )
    }
}
