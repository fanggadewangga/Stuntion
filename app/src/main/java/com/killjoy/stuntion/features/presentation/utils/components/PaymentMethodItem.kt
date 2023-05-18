package com.killjoy.stuntion.features.presentation.utils.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.killjoy.stuntion.features.data.source.remote.api.response.payment.PaymentResponse
import com.killjoy.stuntion.ui.theme.PrimaryBlue
import com.killjoy.stuntion.ui.theme.Type

@Composable
fun PaymentMethodItem(
    modifier: Modifier = Modifier,
    payment: PaymentResponse,
    onClick: () -> Unit,
) {
    Column(
        modifier = modifier.clickable { onClick() },
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {

            // Logo and name
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                AsyncImage(
                    model = payment.imageUrl,
                    contentDescription = "Payment method logo",
                    modifier = Modifier.width(64.dp)
                )
                StuntionText(text = payment.paymentName, textStyle = Type.bodyLarge(), modifier = Modifier.padding(start = 24.dp))
            }

            // Arrow
            Image(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = "Arrow icon",
                colorFilter = ColorFilter.tint(PrimaryBlue),
                modifier = Modifier.padding(end = 16.dp)
            )
        }

        Divider(color = Color.LightGray, modifier = Modifier.fillMaxWidth())
    }
}