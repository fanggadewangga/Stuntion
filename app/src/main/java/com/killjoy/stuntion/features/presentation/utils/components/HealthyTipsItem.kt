package com.killjoy.stuntion.features.presentation.utils.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.killjoy.stuntion.features.data.source.remote.api.response.task.TaskListResponse
import com.killjoy.stuntion.ui.theme.PrimaryBlue
import com.killjoy.stuntion.ui.theme.Type

@Composable
fun HealthyTipsItem(
    modifier: Modifier = Modifier,
    tips : TaskListResponse,
    onClick: () -> Unit,
) {
    Column(verticalArrangement = Arrangement.spacedBy(4.dp), modifier = modifier) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .clickable {
                    onClick()
                }
        ) {
            Checkbox(
                checked = tips.done,
                onCheckedChange = {},
                colors = CheckboxDefaults.colors(
                    checkmarkColor = Color.White,
                    checkedColor = PrimaryBlue
                )
            )
            Spacer(modifier = Modifier.width(4.dp))
            StuntionText(
                text = tips.task,
                textStyle = Type.labelLarge(),
                modifier = Modifier.width((LocalConfiguration.current.screenWidthDp * 0.66).dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Image(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = "Arrow",
                colorFilter = ColorFilter.tint(
                    PrimaryBlue
                ),
                modifier = Modifier.size(32.dp)
            )
        }
        Divider(color = Color.LightGray, modifier = modifier)
    }
}