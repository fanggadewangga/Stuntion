package com.killjoy.stuntion.features.presentation.utils.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.killjoy.stuntion.ui.theme.Type
import com.killjoy.stuntion.R

@Composable
fun ChildProfileSectionItem(
    icon: @Composable (() -> Unit),
    title: String,
    visibleContent: @Composable (() -> Unit),
    invisibleContent: @Composable (() -> Unit),
    modifier: Modifier = Modifier,
) {

    val isExpanded = remember {
        mutableStateOf(true)
    }

    Card(shape = RoundedCornerShape(16.dp), elevation = 4.dp, modifier = modifier) {
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            Box(modifier = Modifier.fillMaxWidth()) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    icon()
                    StuntionText(text = title, textStyle = Type.titleMedium())
                }
                Image(
                    imageVector = if (isExpanded.value) Icons.Default.KeyboardArrowDown else Icons.Default.KeyboardArrowLeft,
                    contentDescription = "Arrow",
                    colorFilter = ColorFilter.tint(Color.Black),
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .clickable {
                            isExpanded.value = !isExpanded.value
                        }
                )
            }

            // Visible Content
            Spacer(modifier = Modifier.height(6.dp))
            visibleContent()

            // Invisible Content
            if (isExpanded.value)
                invisibleContent()
        }
    }
}

@Preview
@Composable
fun ChildProfileSectionItemPreview() {
    ChildProfileSectionItem(
        icon = {
            Image(
                painter = painterResource(id = R.drawable.ic_notes),
                contentDescription = "Notes icon",
                modifier = Modifier.size(24.dp)
            )
        },
        title = "Notes",
        visibleContent = {
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    StuntionText(text = "Ideal height ", textStyle = Type.titleSmall())
                    StuntionText(text = "between ", textStyle = Type.bodyMedium())
                    StuntionText(text = "70 - 83.1 cm", textStyle = Type.titleSmall())
                }
                Row(modifier = Modifier.fillMaxWidth()) {
                    StuntionText(text = "Ideal weight ", textStyle = Type.titleSmall())
                    StuntionText(text = "between ", textStyle = Type.bodyMedium())
                    StuntionText(text = "7.2 - 10.4 Kg", textStyle = Type.titleSmall())
                }
            }
        },
        invisibleContent = {

        },
        modifier = Modifier.fillMaxWidth()
    )
}