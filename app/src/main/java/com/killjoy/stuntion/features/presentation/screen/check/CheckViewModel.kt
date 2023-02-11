package com.killjoy.stuntion.features.presentation.screen.check

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class CheckViewModel @Inject constructor() : ViewModel() {
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

    val nameState = mutableStateOf("")
    val isNameFieldClicked = mutableStateOf(false)
    val isNameValid = derivedStateOf {
        isNameFieldClicked.value && nameState.value.isEmpty()
    }
}