package com.killjoy.stuntion.features.presentation.screen.add_question

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class AddQuestionViewModel @Inject constructor() : ViewModel() {
    val considerDescriptions = listOf(
        "Please write down the questions clearly so that the discussion process is more interactive with the health professional.",
        "For questions regarding brands of milk, medicine, vitamins, and supplements, please consult directly with a health professional through private consultation.",
        "We have absolute authority to delete accounts and/or questions if, based on our judgment, we have violated the Terms.",
        "Please ask questions politely."
    )
    val categories = listOf("Stunting", "Nutrition Consultation", "Pregnant", "Child")

    val checkedState = mutableStateOf(false)
    val selectedCategoryItems = mutableStateListOf<String>()
    val selectedCategory = derivedStateOf {
        selectedCategoryItems.joinToString(", ")
    }
    val isConsiderDescriptionVisible = mutableStateOf(false)
    val questionTitleState = mutableStateOf("")
    val questionDescriptionState = mutableStateOf("")

}