package com.killjoy.stuntion.features.presentation.screen.ask_expert

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class AskExpertViewModel @Inject constructor() : ViewModel() {
    val searchState = mutableStateOf("")
}