package com.killjoy.stuntion.features.presentation.utils.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.killjoy.stuntion.ui.theme.LightBlue
import com.killjoy.stuntion.ui.theme.Type
import com.killjoy.stuntion.R
import com.killjoy.stuntion.features.presentation.utils.ProfileSetting

@Composable
fun ProfileSettingItem(
    item: ProfileSetting,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.clickable { onClick() }
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .background(color = LightBlue, shape = RoundedCornerShape(8.dp))
                .size(48.dp)
        ) {
            AsyncImage(
                model = item.icon,
                contentDescription = "Item",
                modifier = Modifier.size(28.dp)
            )
        }
        StuntionText(text = item.title, textStyle = Type.bodyLarge())
    }
}

@Preview
@Composable
fun ProfileSettingItemPreview() {
    ProfileSettingItem(
        item = ProfileSetting(icon = R.drawable.ic_support, title = "Setting"),
        onClick = {})
}