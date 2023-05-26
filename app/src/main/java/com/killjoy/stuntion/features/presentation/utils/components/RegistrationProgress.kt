package com.killjoy.stuntion.features.presentation.utils.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.killjoy.stuntion.features.domain.model.registration.RegistrationStep
import com.killjoy.stuntion.ui.theme.PrimaryBlue
import com.killjoy.stuntion.ui.theme.Type

@Composable
fun RegistrationProgress(
    modifier: Modifier = Modifier,
    totalRegistrationSteps: Int = 4,
    currentRegistrationStep: Int,
    registrationStep: RegistrationStep,
    progressBarWidth: Dp,
    progressBarShape: Shape,
    progressBarHeight: Dp,
    onClick: () -> Unit,
) {

    Card(
        elevation = 4.dp,
        shape = RoundedCornerShape(10.dp),
        modifier = modifier.clickable { onClick() }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(vertical = 16.dp, horizontal = 16.dp)
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                // Title
                StuntionText(
                    text = registrationStep.title,
                    textStyle = Type.registrationStepTitle()
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(2.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    for (i in 1 until totalRegistrationSteps) {
                        Divider(
                            modifier = Modifier
                                .width(progressBarWidth)
                                .height(progressBarHeight)
                                .clip(shape = progressBarShape),
                            color = if (i <= currentRegistrationStep) PrimaryBlue else Color.LightGray,
                            thickness = 2.dp
                        )
                    }
                }
                // Subtitle
                StuntionText(
                    text = registrationStep.subtitle,
                    textStyle = Type.bodySmall(),
                    color = Color.Gray
                )
            }
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = "Arrow right",
                tint = PrimaryBlue,
                modifier = Modifier.size(48.dp)
            )
        }
    }
}