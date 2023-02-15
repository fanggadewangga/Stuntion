package com.killjoy.stuntion.features.presentation.utils.zscore_util

sealed class WeightCategory(val description: String) {
    object SeverelyStunted : HeightCategory("severely stunted")
    object Stunted : HeightCategory("stunted")
    object Normal : HeightCategory("normal")
    object High : HeightCategory("high")
}
