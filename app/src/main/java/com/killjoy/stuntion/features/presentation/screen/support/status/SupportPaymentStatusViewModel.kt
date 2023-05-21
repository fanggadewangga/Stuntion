package com.killjoy.stuntion.features.presentation.screen.support.status

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SupportPaymentStatusViewModel @Inject constructor(): ViewModel() {
    val supportNumberState = mutableStateOf(0)
    val isLoadingStatusState = mutableStateOf(true)
}