package com.killjoy.stuntion.features.presentation.screen.question

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.killjoy.stuntion.features.presentation.utils.components.StuntionStepper

@Composable
fun StepsProgressBar(
    modifier: Modifier = Modifier,
    numberOfSteps: Int,
    currentStep: Int,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        for (step in 0..numberOfSteps) {
            StuntionStepper(isCurrent = step == currentStep, isComplete = step < currentStep)
        }
    }
}