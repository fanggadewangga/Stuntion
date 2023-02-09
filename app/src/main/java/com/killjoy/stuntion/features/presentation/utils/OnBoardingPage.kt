package com.killjoy.stuntion.features.presentation.utils

import androidx.annotation.DrawableRes
import com.killjoy.stuntion.R

sealed class OnBoardingPage(
    @DrawableRes val image: Int,
    val title: String,
    val description: String,
) {
    object First : OnBoardingPage(
        image = R.drawable.ic_onboard_1,
        title = "Uncovering the Fascinating World of Stunting",
        description = "Discovering the tips to stopping stunting: A collection of reliable and informative articles to prevent and treat stunting"
    )

    object Second : OnBoardingPage(
        image = R.drawable.ic_onboard_2,
        title = "Early Detection of Stunting in Your Little Baby",
        description = "Accurate calculations to detect stunting in your baby along with suggestions that you can run to prevent stunting early"
    )

    object Third : OnBoardingPage(
        image = R.drawable.ic_onboard_3,
        title = "A Guide to Self-Monitoring with Expert Advice",
        description = "You can ask about the health of you and your baby to the experts and you can ask in more detail by chatting with an expert of your choice"
    )

    object Fourth : OnBoardingPage(
        image = R.drawable.ic_onboard_4,
        title = "Onboarding 3",
        description = "Onboarding 3 desc"
    )
}
