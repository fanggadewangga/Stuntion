package com.killjoy.stuntion.features.presentation.utils.zscore_util

sealed class WeightCategory(val description: String) {
    object SeverelyStunted : WeightCategory("Severely Stunted")
    object Stunted : WeightCategory("Stunted")
    object Normal : WeightCategory("Normal")
    object High : WeightCategory("High")
    object Undefined : WeightCategory("Undefined")
}
