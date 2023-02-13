package com.killjoy.stuntion.features.domain.model.zscore

data class ZScoreStandard(
    val age: Int,
    val low3SD: Double,
    val low2SD: Double,
    val low1SD: Double,
    val median: Double,
    val high1SD: Double,
    val high2SD: Double,
    val high3SD: Double,
)
