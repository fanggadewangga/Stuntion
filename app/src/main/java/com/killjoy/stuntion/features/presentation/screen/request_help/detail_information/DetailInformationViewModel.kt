package com.killjoy.stuntion.features.presentation.screen.request_help.detail_information

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailInformationViewModel @Inject constructor(): ViewModel() {
    val listOfStep = listOf(
        "Help Targets",
        "Tittle",
        "Information",
        "Confirmation"
    )

    val storyState = mutableStateOf("")
    val isStoryFieldClicked = mutableStateOf(false)
    val isValidStory = derivedStateOf {
        storyState.value.isNotEmpty() || !isStoryFieldClicked.value
    }
}