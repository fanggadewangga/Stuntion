package com.killjoy.stuntion.features.presentation.utils.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.killjoy.stuntion.ui.theme.PrimaryBlue
import com.killjoy.stuntion.ui.theme.Type

@Composable
fun NotificationItem(
    modifier: Modifier = Modifier,
    imageUrl: String,
    title: String,
    description: String,
    time: Int,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = modifier
    ) {
        // Blue dot
        Box(
            modifier = Modifier
                .size(12.dp)
                .background(color = PrimaryBlue, shape = CircleShape)
        )

        // Image
        AsyncImage(
            model = imageUrl,
            contentDescription = "Notification image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape)
        )

        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {

            // Title
            StuntionText(
                text = title,
                textStyle = Type.titleSmall(),
                maxLine = 1,
                overflow = TextOverflow.Ellipsis
            )

            // Description
            StuntionText(
                text = description,
                textStyle = Type.bodyMedium(),
                maxLine = 2,
                overflow = TextOverflow.Ellipsis
            )

            // Time
            StuntionText(
                text = "$time hours ago",
                color = PrimaryBlue,
                textStyle = Type.labelMedium()
            )
        }

    }
}