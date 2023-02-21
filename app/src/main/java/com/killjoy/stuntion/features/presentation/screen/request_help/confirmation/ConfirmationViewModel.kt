package com.killjoy.stuntion.features.presentation.screen.request_help.confirmation

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ConfirmationViewModel @Inject constructor(): ViewModel() {
    val listOfStep = listOf(
        "Help Targets",
        "Tittle",
        "Information",
        "Confirmation"
    )

}