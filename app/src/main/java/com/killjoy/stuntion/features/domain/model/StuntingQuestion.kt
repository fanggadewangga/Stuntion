package com.killjoy.stuntion.features.domain.model

data class StuntingQuestion(
    val question: String,
    val isMultipleChoices: Boolean = false,
    val listOfAnswer: List<String> = listOf("Yes", "No", "Maybe"),
)
