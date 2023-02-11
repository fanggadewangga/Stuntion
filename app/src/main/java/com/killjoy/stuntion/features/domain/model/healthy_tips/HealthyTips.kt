package com.killjoy.stuntion.features.domain.model.healthy_tips


data class HealthyTips(
    val title: String,
    val minAge: Double,
    val maxAge: Double,
    val material: String,
    val instructions: List<String>,
    val isDone: Boolean = false
)
