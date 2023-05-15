package com.killjoy.stuntion.features.presentation.screen.onboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.killjoy.stuntion.features.presentation.utils.OnBoardingPage
import com.killjoy.stuntion.features.presentation.utils.components.StuntionText
import com.killjoy.stuntion.ui.theme.Type

@Composable
fun PagerScreen(onBoardingPage: OnBoardingPage) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .wrapContentHeight()
            .padding(start = 16.dp, top = 32.dp, end = 16.dp)
    ) {
        // Onboard image
        Image(
            painter = painterResource(id = onBoardingPage.image),
            contentDescription = "Onboard image"
        )

        // Title
        Spacer(modifier = Modifier.height(24.dp))
        StuntionText(
            text = onBoardingPage.title,
            textAlign = TextAlign.Center,
            textStyle = Type.titleLarge(),
            modifier = Modifier.padding(horizontal = 24.dp)
        )

        // Description
        Spacer(modifier = Modifier.height(16.dp))
        StuntionText(
            text = onBoardingPage.description,
            textAlign = TextAlign.Center,
            textStyle = Type.bodyLarge()
        )
    }
}