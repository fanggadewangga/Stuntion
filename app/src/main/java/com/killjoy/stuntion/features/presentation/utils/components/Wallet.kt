package com.killjoy.stuntion.features.presentation.utils.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.killjoy.stuntion.R
import com.killjoy.stuntion.ui.theme.PrimaryBlue
import com.killjoy.stuntion.ui.theme.Type

@Composable
fun Wallet(
    modifier: Modifier = Modifier,
    balance: Double,
    onTopUpClicked: () -> Unit,
) {
    Card(
        elevation = 4.dp,
        shape = RoundedCornerShape(10.dp),
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxSize().padding(horizontal = 12.dp)
        ) {
            // Wallet icon and balance
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxHeight()
            ) {
                AsyncImage(
                    model = R.drawable.ic_support_wallet,
                    contentDescription = "Wallet icon",
                    modifier = Modifier.size(36.dp)
                )
                Column {
                    StuntionText(text = balance.toString(), textStyle = Type.wallet())
                    StuntionText(
                        text = "Your support wallet",
                        textStyle = Type.bodySmall(),
                        color = Color.Gray
                    )
                }
            }

            // Top up button
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .border(width = 1.dp, color = PrimaryBlue, shape = RoundedCornerShape(16.dp))
                    .clickable {
                        onTopUpClicked()
                    }
            ) {
                StuntionText(text = "Top up", textStyle = Type.labelLarge(), color = PrimaryBlue, modifier = Modifier.padding(vertical = 6.dp, horizontal = 16.dp))
            }
        }
    }
}