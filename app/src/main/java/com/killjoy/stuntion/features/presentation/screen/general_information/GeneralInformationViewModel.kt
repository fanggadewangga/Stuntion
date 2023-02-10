package com.killjoy.stuntion.features.presentation.screen.general_information

import android.util.Log
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class GeneralInformationViewModel @Inject constructor() : ViewModel() {
    val nameState = mutableStateOf("")
    val isNameFieldClicked = mutableStateOf(false)
    val isNameValid = derivedStateOf {
        isNameFieldClicked.value && nameState.value.isEmpty()
    }

    val dateState = mutableStateOf("")
    val isDateFieldClicked = mutableStateOf(false)
    val isDateValid = derivedStateOf {
        isDateFieldClicked.value && dateState.value.isEmpty()
    }

    val selectedGender = mutableStateOf("")
}