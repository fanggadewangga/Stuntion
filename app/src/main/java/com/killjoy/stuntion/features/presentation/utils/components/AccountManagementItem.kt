package com.killjoy.stuntion.features.presentation.utils.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import com.killjoy.stuntion.ui.theme.LightGray
import com.killjoy.stuntion.ui.theme.Type

@Composable
fun AccountManagementItem(
    modifier: Modifier = Modifier,
    title: String,
    textColor: Color = Color.Black,
    description: String? = null,
    onClick: () -> Unit,
) {
    Column(modifier = modifier.clickable { onClick() }) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(3.dp)) {
                StuntionText(
                    text = title,
                    textStyle = Type.bodyLarge(),
                    color = textColor
                )
            }
            Image(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = "Arrow icon",
                colorFilter = ColorFilter.tint(
                    Color.LightGray
                ),
                modifier = Modifier.size(24.dp)
            )
        }

        if (description != null)
            StuntionText(
                text = description,
                textStyle = Type.bodySmall(),
                color = LightGray
            )

        Divider(color = Color.LightGray, modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
        )

    }
}