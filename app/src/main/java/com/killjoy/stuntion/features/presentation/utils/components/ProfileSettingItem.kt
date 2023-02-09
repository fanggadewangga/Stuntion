package com.killjoy.stuntion.features.presentation.utils.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.killjoy.stuntion.ui.stuntionUI.StuntionText
import com.killjoy.stuntion.ui.theme.LightBlue
import com.killjoy.stuntion.ui.theme.Type
import com.killjoy.stuntion.R
import com.killjoy.stuntion.ui.theme.PrimaryBlue

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
            modifier = Modifier
                .background(color = LightBlue, shape = RoundedCornerShape(8.dp))
                .padding(4.dp)
        ) {
            AsyncImage(
                model = item.icon,
                contentDescription = "Item",
                colorFilter = ColorFilter.tint(PrimaryBlue),
                modifier = Modifier.size(32.dp)
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