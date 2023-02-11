package com.killjoy.stuntion.features.presentation.utils.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.killjoy.stuntion.ui.stuntionUI.StuntionText
import com.killjoy.stuntion.ui.theme.LightGray
import com.killjoy.stuntion.ui.theme.Type

@Composable
fun StuntionTopBar(title: String, onBackPressed: () -> Unit, isWithDivider: Boolean = true) {
    Column(Modifier.fillMaxWidth()) {
        Spacer(modifier = Modifier.height(24.dp))
        Box(
            contentAlignment = Alignment.TopCenter,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Image(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Arrow Back",
                modifier = Modifier
                    .align(
                        Alignment.CenterStart
                    )
                    .clickable {
                        onBackPressed()
                    }
            )
            StuntionText(text = title, textStyle = Type.titleLarge())
        }
        Spacer(modifier = Modifier.height(8.dp))
        if (isWithDivider){
            Spacer(modifier = Modifier.height(16.dp))
            Divider(color = LightGray, modifier = Modifier.fillMaxWidth())
        }
    }
}