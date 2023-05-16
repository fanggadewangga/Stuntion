package com.killjoy.stuntion.features.presentation.screen.onboard

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.killjoy.stuntion.features.presentation.utils.OnBoardingPage
import com.killjoy.stuntion.features.presentation.utils.components.StuntionText
import com.killjoy.stuntion.ui.theme.Type

@Composable
fun PagerScreen(onBoardingPage: OnBoardingPage) {
    val screenHeight = LocalConfiguration.current.screenHeightDp
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .wrapContentHeight()
            .padding(start = 16.dp, top = 32.dp, end = 16.dp)
    ) {
        // Onboard image
        AsyncImage(
            model = onBoardingPage.image,
            contentDescription = "Onboard image",
            modifier = Modifier.size(240.dp)
        )

        // Title
        Spacer(modifier = Modifier.height((screenHeight / 18).dp))
        StuntionText(
            text = onBoardingPage.title,
            textAlign = TextAlign.Center,
            textStyle = Type.titleLarge(),
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        // Description
        Spacer(modifier = Modifier.height(8.dp))
        StuntionText(
            text = onBoardingPage.description,
            textAlign = TextAlign.Center,
            textStyle = Type.bodyLarge()
        )
    }
}