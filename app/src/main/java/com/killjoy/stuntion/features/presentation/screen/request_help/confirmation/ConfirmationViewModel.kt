package com.killjoy.stuntion.features.presentation.screen.request_help.confirmation

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ConfirmationViewModel @Inject constructor() : ViewModel() {
    val listOfStep = listOf(
        "Help Targets",
        "Tittle",
        "Information",
        "Confirmation"
    )

    val listOfCheck = listOf(
        "Personal data is correct",
        "Your need for help is clear and true",
        "The title and image are according to your request",
        "The story you describe already includes the information in your request for help",
        "You agree to the Terms & Conditions of Stuntion",
    )

    val listOfChecked = mutableStateListOf<String>()
}