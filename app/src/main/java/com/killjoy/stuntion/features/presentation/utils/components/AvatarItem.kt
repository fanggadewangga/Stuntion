package com.killjoy.stuntion.features.presentation.utils.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.killjoy.stuntion.features.presentation.utils.Avatar
import com.killjoy.stuntion.ui.theme.PrimaryBlue

@Composable
fun AvatarItem(
    avatar: Avatar,
    currentSelectedAvatar: MutableState<String>,
) {
    Surface(
        shape = CircleShape,
        border = if (currentSelectedAvatar.value == avatar.id)
            BorderStroke(
                width = 3.dp,
                color = PrimaryBlue
            ) else null,
        modifier = Modifier.wrapContentSize().clickable { currentSelectedAvatar.value = avatar.id }
    ) {
        AsyncImage(
            model = avatar.avatarUrl,
            contentDescription = "Avatar icon",
            modifier = Modifier
                .size(120.dp)
                .padding(8.dp)
                .clip(CircleShape)
        )
    }
}