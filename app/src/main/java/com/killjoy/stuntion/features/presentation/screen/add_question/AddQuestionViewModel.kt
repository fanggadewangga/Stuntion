package com.killjoy.stuntion.features.presentation.screen.add_question

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class AddQuestionViewModel @Inject constructor() : ViewModel() {
    val considerDescriptions = listOf(
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla justo purus, tincidunt at ligula eu, rhoncus vulputate libero. ",
        "Integer lobortis nisl augue, sit amet auctor elit sollicitudin sed. Nunc nisl dolor, aliquam eget leo venenatis, fringilla cursus nisi. ",
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus nec tempor lorem, sit amet pulvinar tortor. Integer blandit efficitur sodales. ",
        "Donec lacus metus, tristique vel est eget, tempor aliquet justo. Cras finibus dolor eu mauris tempus condimentum."
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