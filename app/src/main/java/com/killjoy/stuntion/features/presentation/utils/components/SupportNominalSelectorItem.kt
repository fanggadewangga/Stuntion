package com.killjoy.stuntion.features.presentation.utils.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.killjoy.stuntion.features.domain.model.support_nominal.SupportNominal
import com.killjoy.stuntion.ui.theme.LightGray
import com.killjoy.stuntion.ui.theme.PrimaryBlue
import com.killjoy.stuntion.ui.theme.Type

@Composable
fun SupportNominalSelectorItem(
    modifier: Modifier = Modifier,
    nominal: SupportNominal,
    isSelected: Boolean,
    onSelectorClicked: () -> Unit,
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(100.dp))
            .border(
                width = if (isSelected) 3.dp else 1.dp,
                color = if (isSelected) PrimaryBlue else LightGray,
                shape = RoundedCornerShape(100.dp)
            )
            .clickable {
                onSelectorClicked()
            }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 18.dp, top = 12.dp, bottom = 12.dp)
        ) {

            // Emoji & Nominal
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                AsyncImage(
                    model = nominal.image,
                    contentDescription = "Emoji",
                    modifier = Modifier.size(28.dp)
                )
                StuntionText(
                    text = "IDR",
                    textStyle = Type.bodyLarge(),
                    color = LightGray,
                    modifier = Modifier.padding(start = 12.dp, end = 6.dp)
                )
                StuntionText(text = nominal.nominal.toString(), textStyle = Type.titleMedium())
            }

            // Arrow
            Image(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = "Arrow icon",
                colorFilter = ColorFilter.tint(PrimaryBlue),
                modifier = Modifier.padding(end = 16.dp)
            )
        }
    }
}