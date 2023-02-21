package com.killjoy.stuntion.features.presentation.screen.request_help.personal_data

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PersonalDataViewModel @Inject constructor() : ViewModel() {
    val listOfStep = listOf(
        "Personal Data",
        "Help Targets",
        "Tittle",
        "Information",
    )

    val nameState = mutableStateOf("")
    val isNameFieldClicked = mutableStateOf(false)
    val isValidName = derivedStateOf {
        nameState.value.isNotEmpty() || !isNameFieldClicked.value
    }

    val phoneState = mutableStateOf("")
    val isPhoneFieldClicked = mutableStateOf(false)
    val isValidPhone = derivedStateOf {
        phoneState.value.isNotEmpty() || !isPhoneFieldClicked.value
    }

    val addressState = mutableStateOf("")
    val isAddressFieldClicked = mutableStateOf(false)
    val isValidAddress = derivedStateOf {
        addressState.value.isNotEmpty() || !isAddressFieldClicked.value
    }

    val jobState = mutableStateOf("")
    val isJobFieldClicked = mutableStateOf(false)
    val isValidJob = derivedStateOf {
        jobState.value.isNotEmpty() || !isJobFieldClicked.value
    }
}