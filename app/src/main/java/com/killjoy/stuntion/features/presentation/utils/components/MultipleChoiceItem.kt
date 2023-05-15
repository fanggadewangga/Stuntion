package com.killjoy.stuntion.features.presentation.utils.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.killjoy.stuntion.ui.theme.PrimaryBlue
import com.killjoy.stuntion.ui.theme.Type

@Composable
fun MultipleChoiceItem(
    title: String,
    onSelected: () -> Unit,
    onUnselected: () -> Unit,
    modifier: Modifier = Modifier,
) {

    val isSelected = remember { mutableStateOf(false) }

    Box(
        modifier = modifier
    ) {

        StuntionText(
            text = title,
            textStyle = Type.bodyMedium(),
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(16.dp)
        )
        Checkbox(
            checked = isSelected.value,
            onCheckedChange = {
                isSelected.value = it
                if (isSelected.value)
                    onSelected()
                else
                    onUnselected()
            },
            colors = CheckboxDefaults.colors(
                checkmarkColor = Color.White,
                checkedColor = PrimaryBlue
            ),
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(end = 16.dp)
        )
    }
}