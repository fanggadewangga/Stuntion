package com.killjoy.stuntion.features.domain.model.support_nominal

import androidx.annotation.DrawableRes
import com.killjoy.stuntion.R

data class SupportNominal(
    @DrawableRes val image: Int,
    val nominal: Int,
)
