package com.killjoy.stuntion.features.presentation.screen.request_help

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.killjoy.stuntion.features.presentation.utils.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RequestHelpViewModel @Inject constructor() : ViewModel() {
    val currentStep = mutableStateOf(1)
    val listOfStep = listOf(
        "Personal Data",
        "Help Targets",
        "Tittle",
        "Information",
    )

    // Personal Data
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

    // Help targets
    val listOfDuration = listOf(
        "10 days",
        "30 days",
        "60 days",
        "Choose a date",
    )

    val selectedDuration = mutableStateOf(listOfDuration[0])

    val foodState = mutableStateOf("")
    val isFoodFieldClicked = mutableStateOf(false)
    val isValidFood = derivedStateOf {
        foodState.value.isNotEmpty() || !isFoodFieldClicked.value
    }

    val costState = mutableStateOf("")
    val isCostFieldClicked = mutableStateOf(false)
    val isValidCost = derivedStateOf {
        costState.value.isNotEmpty() || !isCostFieldClicked.value
    }

    // Title
    val titleState = mutableStateOf("")
    val isTitleFieldClicked = mutableStateOf(false)
    val isValidTitle = derivedStateOf {
        titleState.value.isNotEmpty() || !isTitleFieldClicked.value
    }

    // Detail Information
    val storyState = mutableStateOf("")
    val isStoryFieldClicked = mutableStateOf(false)
    val isValidStory = derivedStateOf {
        storyState.value.isNotEmpty() || !isStoryFieldClicked.value
    }

    // Confirmation
    val listOfCheck = listOf(
        "Personal data is correct",
        "Your need for help is clear and true",
        "The title and image are according to your request",
        "The story you describe already includes the information in your request for help",
        "You agree to the Terms & Conditions of Stuntion",
    )

    val listOfChecked = mutableStateListOf<String>()
}