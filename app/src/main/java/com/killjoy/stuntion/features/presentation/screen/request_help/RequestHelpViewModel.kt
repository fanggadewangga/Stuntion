package com.killjoy.stuntion.features.presentation.screen.request_help

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
}