package com.killjoy.stuntion.features.domain.model.stunting_question

data class StuntingQuestion(
    val question: String,
    val isMultipleChoices: Boolean = false,
    val listOfAnswer: List<String> = listOf("Yes", "No", "Maybe"),
)
