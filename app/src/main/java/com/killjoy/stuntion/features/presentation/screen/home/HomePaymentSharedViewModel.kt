package com.killjoy.stuntion.features.presentation.screen.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomePaymentSharedViewModel @Inject constructor() : ViewModel() {
    val isHasSelectedAPayment = mutableStateOf(false)
    val selectedPaymentNameState = mutableStateOf("")
    val selectedPaymentImageUrlState = mutableStateOf("")
}