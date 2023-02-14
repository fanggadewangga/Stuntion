package com.killjoy.stuntion.features.presentation.screen.check

import android.util.Log
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class CheckViewModel @Inject constructor() : ViewModel() {
    val genderState = mutableStateOf("Male")
    private val isGenderValid = derivedStateOf {
        genderState.value.isNotEmpty()
    }

    val dateState = mutableStateOf("")
    val isDateFieldClicked = mutableStateOf(false)
    val isDateValid = derivedStateOf {
        !isDateFieldClicked.value || dateState.value.isNotEmpty()
    }

    val heightState = mutableStateOf("")
    val isHeightFieldClicked = mutableStateOf(false)
    val isHeightValid = derivedStateOf {
        !isHeightFieldClicked.value || heightState.value.isNotEmpty()
    }

    val weightState = mutableStateOf("")
    val isWeightFieldClicked = mutableStateOf(false)
    val isWeightValid = derivedStateOf {
        !isWeightFieldClicked.value || weightState.value.isNotEmpty()
    }

    val nameState = mutableStateOf("")
    val isNameFieldClicked = mutableStateOf(false)
    val isNameValid = derivedStateOf {
        !isNameFieldClicked.value || nameState.value.isNotEmpty()
    }

    val isFormNotValid = derivedStateOf {
        isGenderValid.value && nameState.value.isNotEmpty() && dateState.value.isNotEmpty() && weightState.value.isNotEmpty() && heightState.value.isNotEmpty()
    }
}