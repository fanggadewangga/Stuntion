package com.killjoy.stuntion.features.presentation.utils.zscore_util

sealed class HeightCategory(val description: String) {
    object SeverelyUnderheight: HeightCategory("Severely Underheight")
    object Underheight : HeightCategory("Underheight")
    object Normal : HeightCategory("Normal")
    object High : HeightCategory("High")
    object Undefined: HeightCategory("Undefined")
}
