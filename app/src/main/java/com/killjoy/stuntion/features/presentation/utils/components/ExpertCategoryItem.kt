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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.killjoy.stuntion.ui.theme.PrimaryBlue
import com.killjoy.stuntion.ui.theme.Type

@Composable
fun ExpertCategoryItem(
    modifier: Modifier = Modifier,
    category: String,
    onClick: () -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.padding(top = 8.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
        ) {
            StuntionText(text = category, textStyle = Type.bodyLarge())
            Image(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = "Arrow Next",
                colorFilter = ColorFilter.tint(
                    PrimaryBlue
                ),
                modifier = Modifier
                    .size(24.dp)
                    .clickable {
                        onClick()
                    }
            )
        }
        Divider(
            color = Color.LightGray,
            modifier = modifier
        )
    }
}

@Preview
@Composable
fun ExpertCategoryItemPreview() {
    ExpertCategoryItem(
        category = "Doctor",
        onClick = {

        }
    )
}