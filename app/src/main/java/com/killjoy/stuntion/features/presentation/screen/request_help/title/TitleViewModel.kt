package com.killjoy.stuntion.features.presentation.screen.request_help.title

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TitleViewModel @Inject constructor(): ViewModel() {
    val titleState = mutableStateOf("")
    val isTitleFieldClicked = mutableStateOf(false)
    val isValidTitle = derivedStateOf {
        titleState.value.isNotEmpty() || !isTitleFieldClicked.value
    }
}