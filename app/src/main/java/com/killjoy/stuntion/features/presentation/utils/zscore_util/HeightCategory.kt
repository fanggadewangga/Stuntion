package com.killjoy.stuntion.features.presentation.utils.zscore_util

sealed class HeightCategory(val description: String) {
    object SeverelyUnderweight: HeightCategory("severely underweight")
    object Underweight : HeightCategory("underweight")
    object Normal : HeightCategory("normal")
    object High : HeightCategory("high")
}
