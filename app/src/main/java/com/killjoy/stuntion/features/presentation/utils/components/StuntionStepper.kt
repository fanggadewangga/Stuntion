package com.killjoy.stuntion.features.presentation.utils.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.killjoy.stuntion.ui.stuntionUI.StuntionText
import com.killjoy.stuntion.ui.theme.PrimaryBlue
import com.killjoy.stuntion.ui.theme.Type

@Composable
fun StuntionStepper(
    modifier: Modifier = Modifier,
    stepItems: List<String>? = null,
    isWithNumber: Boolean = false,
    isCurrent: Boolean,
    isComplete: Boolean,
) {
    val color = if (isComplete) PrimaryBlue else Color.LightGray

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Divider(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .width(48.dp)
                .height(3.dp)
                .clip(RoundedCornerShape(8.dp)),
            color = color,
            thickness = 2.dp
        )
        if (isWithNumber && stepItems != null)
            LazyRow(
                modifier,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                itemsIndexed(stepItems) { index, item ->
                    Step(
                        index = index,
                        step = item,
                        isCurrent = isCurrent,
                        isCompleted = isComplete
                    )
                }
            }
    }
}

@Composable
fun Step(index: Int, step: String, isCurrent: Boolean, isCompleted: Boolean) {
    val backgroundColor = if (isCurrent || isCompleted) PrimaryBlue else Color.White
    val textIndexColor = if (isCurrent || isCompleted) Color.White else PrimaryBlue
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(36.dp)
                .background(color = backgroundColor, shape = CircleShape)
                .border(width = 1.dp, color = PrimaryBlue, shape = CircleShape)
        ) {
            StuntionText(
                text = index.toString(),
                textStyle = Type.titleMedium(),
                color = textIndexColor
            )
        }
        StuntionText(text = step, textStyle = Type.labelMedium(), color = PrimaryBlue)
    }
}