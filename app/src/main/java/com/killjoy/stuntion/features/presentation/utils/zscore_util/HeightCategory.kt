package com.killjoy.stuntion.features.presentation.utils.zscore_util

sealed class HeightCategory(val description: String) {
    object SeverelyUnderweight: HeightCategory("Severely Underweight")
    object Underweight : HeightCategory("Underweight")
    object Normal : HeightCategory("Normal")
    object High : HeightCategory("High")
    object Undefined: HeightCategory("Undefined")
}
