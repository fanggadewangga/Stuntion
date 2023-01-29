package com.killjoy.stuntion.features.presentation.screen.calculator

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class CalculatorViewModel @Inject constructor() : ViewModel() {
    val genderState = mutableStateOf("")
    val isGenderValid = derivedStateOf {
        genderState.value.isEmpty()
    }

    val dateState = mutableStateOf("")
    val isDateFieldClicked = mutableStateOf(false)
    val isDateValid = derivedStateOf {
        isDateFieldClicked.value && dateState.value.isEmpty()
    }

    val heightState = mutableStateOf("")
    val isHeightFieldClicked = mutableStateOf(false)
    val isHeightValid = derivedStateOf {
        isHeightFieldClicked.value && heightState.value.isEmpty()
    }

    val weightState = mutableStateOf("")
    val isWeightFieldClicked = mutableStateOf(false)
    val isWeightValid = derivedStateOf {
        isWeightFieldClicked.value && weightState.value.isEmpty()
    }

    val headState = mutableStateOf("")
    val isHeadFieldClicked = mutableStateOf(false)
    val isHeadValid = derivedStateOf {
        isHeadFieldClicked.value && headState.value.isEmpty()
    }

    val armState = mutableStateOf("")
    val isArmFieldClicked = mutableStateOf(false)
    val isArmValid = derivedStateOf {
        isArmFieldClicked.value && armState.value.isEmpty()
    }

}