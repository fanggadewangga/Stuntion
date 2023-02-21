package com.killjoy.stuntion.features.presentation.screen.request_help.help_target

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HelpTargetViewModel @Inject constructor() : ViewModel() {
    val listOfStep = listOf(
        "Personal Data",
        "Help Targets",
        "Tittle",
        "Information",
    )

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
}