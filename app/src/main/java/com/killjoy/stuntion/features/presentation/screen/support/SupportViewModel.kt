package com.killjoy.stuntion.features.presentation.screen.support

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SupportViewModel @Inject constructor(): ViewModel() {
    val searchState = mutableStateOf("")
}