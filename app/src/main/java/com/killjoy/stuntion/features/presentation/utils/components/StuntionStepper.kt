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
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.killjoy.stuntion.ui.stuntionUI.StuntionText
import com.killjoy.stuntion.ui.theme.PrimaryBlue
import com.killjoy.stuntion.ui.theme.Type

@Composable
fun StuntionStepper(
    modifier: Modifier = Modifier,
    index: Int,
    currentStep: Int,
    numberOfSteps: Int,
    stepItems: List<String>? = null,
    isWithNumber: Boolean = false,
) {

    val isStart = index == 0
    val isEnd = index == numberOfSteps
    val isCompleted = index < currentStep
    val isCurrent = index == currentStep
    val color = if (isCurrent || isCompleted) PrimaryBlue else Color.LightGray
    val backgroundColor = if (isCurrent || isCompleted) PrimaryBlue else Color.White
    val textIndexColor = if (isCurrent || isCompleted) Color.White else PrimaryBlue
    val shape =
        if (isStart) RoundedCornerShape(topStart = 8.dp, bottomStart = 8.dp)
        else if (isEnd) RoundedCornerShape(topEnd = 8.dp, bottomEnd = 8.dp)
        else RectangleShape

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        if (isWithNumber && stepItems != null)
            Row(
                modifier,
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                stepItems.forEachIndexed { index, item ->
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceEvenly
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
                            Divider(
                                modifier = Modifier
                                    .width(16.dp)
                                    .height(4.dp)
                                    .clip(shape = shape),
                                color = color,
                                thickness = 2.dp
                            )
                        }
                        StuntionText(
                            text = item,
                            textStyle = Type.labelMedium(),
                            color = PrimaryBlue
                        )
                    }
                }
            }
        else
            Divider(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .width(48.dp)
                    .height(4.dp)
                    .clip(shape = shape),
                color = color,
                thickness = 2.dp
            )
    }
}