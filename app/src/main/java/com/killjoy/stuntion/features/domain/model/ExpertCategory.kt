package com.killjoy.stuntion.features.domain.model

import androidx.annotation.DrawableRes
import com.killjoy.stuntion.R

data class ExpertCategory(
    @DrawableRes val image: Int = R.drawable.iv_expert,
    val title: String,
)
