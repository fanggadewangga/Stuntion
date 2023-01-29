package com.killjoy.stuntion.features.presentation.utils

import androidx.annotation.DrawableRes
import com.killjoy.stuntion.R

sealed class OnBoardingPage(
    @DrawableRes val image: Int,
    val title: String,
    val description: String,
) {
    object First : OnBoardingPage(
        image = R.drawable.ic_baby,
        title = "Onboarding 1",
        description = "Onboarding 1 desc"
    )

    object Second : OnBoardingPage(
        image = R.drawable.ic_baby,
        title = "Onboarding 2",
        description = "Onboarding 2 desc"
    )

    object Third : OnBoardingPage(
        image = R.drawable.ic_baby,
        title = "Onboarding 3",
        description = "Onboarding 3 desc"
    )
}
