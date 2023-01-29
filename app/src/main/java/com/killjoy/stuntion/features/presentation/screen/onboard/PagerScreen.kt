package com.killjoy.stuntion.features.presentation.screen.onboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.killjoy.stuntion.features.presentation.utils.OnBoardingPage
import com.killjoy.stuntion.ui.stuntionUI.StuntionText
import com.killjoy.stuntion.ui.theme.Type

@Composable
fun PagerScreen(onBoardingPage: OnBoardingPage) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.wrapContentHeight()) {
        Image(
            painter = painterResource(id = onBoardingPage.image),
            contentDescription = "Onboard image",
            modifier = Modifier.size(100.dp)
        )
        StuntionText(text = onBoardingPage.title, textStyle = Type.titleLarge())
        StuntionText(text = onBoardingPage.description, textStyle = Type.bodyMedium())
    }
}