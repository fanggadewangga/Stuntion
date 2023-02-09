package com.killjoy.stuntion.features.presentation.utils.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.killjoy.stuntion.R
import com.killjoy.stuntion.ui.stuntionUI.StuntionText
import com.killjoy.stuntion.ui.theme.PrimaryBlue
import com.killjoy.stuntion.ui.theme.Type

@Composable
fun StuntionTextField(
    modifier: Modifier = Modifier,
    contentSpacing: Dp = 4.dp,
    showWarningMessage: Boolean = false,
    warningMessage: String = "",
    placeHolder: String,
    label: String? = "",
    textStyle: TextStyle = Type.bodyMedium(),
    value: String,
    onValueChange: (String) -> Unit,
    isPassword: Boolean = false,
    shape: Shape = RoundedCornerShape(100.dp),
    enabled: Boolean = true,
    readOnly: Boolean = false,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = false,
    maxLines: Int = Int.MAX_VALUE,
    textColor: Color = Color.Black,
    placeHolderColor: Color = Color.Gray,
    disablePlaceHolderColor: Color = Color.Transparent,
    disabledTextColor: Color = Color.Gray,
    backgroundColor: Color = if (enabled) Color.White else Color.Gray,
    cursorColor: Color = Color.Black,
    errorCursorColor: Color = Color.Red,
    focusedIndicatorColor: Color = Color.Gray,
    unfocusedIndicatorColor: Color = Color.Gray,
    disabledIndicatorColor: Color = Color.Gray,
    errorIndicatorColor: Color = Color.Red,
) {
    Column(verticalArrangement = Arrangement.spacedBy(contentSpacing)) {

        if (isPassword) {
            val passwordVisibility = remember { mutableStateOf(false) }
            val icon = if (passwordVisibility.value)
                painterResource(id = R.drawable.ic_password_visibility_on)
            else
                painterResource(id = R.drawable.ic_password_visibility_off)

            OutlinedTextField(
                modifier = modifier,
                shape = shape,
                readOnly = readOnly,
                leadingIcon = leadingIcon,
                trailingIcon = {
                    IconButton(
                        onClick = {
                            passwordVisibility.value = !passwordVisibility.value
                        }
                    ) {
                        Icon(painter = icon, contentDescription = "Visibility Icon")
                    }
                },
                isError = isError,
                visualTransformation = if (passwordVisibility.value) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = keyboardOptions,
                keyboardActions = keyboardActions,
                singleLine = singleLine,
                maxLines = maxLines,
                value = value,
                onValueChange = onValueChange,
                colors = TextFieldDefaults.textFieldColors(
                    textColor = textColor,
                    disabledTextColor = disabledTextColor,
                    backgroundColor = backgroundColor,
                    cursorColor = cursorColor,
                    errorCursorColor = errorCursorColor,
                    focusedIndicatorColor = focusedIndicatorColor,
                    unfocusedIndicatorColor = unfocusedIndicatorColor,
                    disabledIndicatorColor = disabledIndicatorColor,
                    errorIndicatorColor = errorIndicatorColor
                ),
                label = {
                    StuntionText(
                        text = placeHolder,
                        color = if (isError) errorIndicatorColor else placeHolderColor
                    )
                },
                textStyle = textStyle
            )
        } else {
            OutlinedTextField(
                modifier = modifier,
                shape = shape,
                readOnly = readOnly,
                leadingIcon = leadingIcon,
                trailingIcon = trailingIcon,
                isError = isError,
                visualTransformation = visualTransformation,
                keyboardOptions = keyboardOptions,
                keyboardActions = keyboardActions,
                singleLine = singleLine,
                maxLines = maxLines,
                value = value,
                onValueChange = onValueChange,
                colors = TextFieldDefaults.textFieldColors(
                    textColor = textColor,
                    disabledTextColor = disabledTextColor,
                    backgroundColor = backgroundColor,
                    cursorColor = cursorColor,
                    errorCursorColor = errorCursorColor,
                    focusedIndicatorColor = focusedIndicatorColor,
                    unfocusedIndicatorColor = unfocusedIndicatorColor,
                    disabledIndicatorColor = disabledIndicatorColor,
                    errorIndicatorColor = errorIndicatorColor,
                    placeholderColor = placeHolderColor,
                    disabledPlaceholderColor = disablePlaceHolderColor
                ),
                label = {
                        StuntionText(
                            text = placeHolder,
                            color = if (isError) errorIndicatorColor else placeHolderColor
                        )
                },
                textStyle = textStyle
            )
        }

        if (showWarningMessage) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(imageVector = Icons.Default.Warning, contentDescription = "Warning Icon")
                StuntionText(
                    text = warningMessage,
                    color = when {
                        isError -> Color.Red
                        else -> Color.Gray
                    }
                )
            }
        }
    }
}