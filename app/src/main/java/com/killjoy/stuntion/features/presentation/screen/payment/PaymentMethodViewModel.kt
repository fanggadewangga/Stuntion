package com.killjoy.stuntion.features.presentation.screen.payment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.killjoy.stuntion.features.data.repository.payment.PaymentRepository
import com.killjoy.stuntion.features.data.source.remote.api.response.payment.PaymentResponse
import com.killjoy.stuntion.features.data.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PaymentMethodViewModel @Inject constructor(private val paymentRepository: PaymentRepository): ViewModel() {
    private val _paymentResponse = MutableStateFlow<Resource<List<PaymentResponse>>>(Resource.Loading())
    val paymentResponse = _paymentResponse.asStateFlow()

    private fun fetchPayments() {
        viewModelScope.launch {
            paymentRepository.fetchPayments().collect {
                _paymentResponse.value = it
            }
        }
    }

    init {
        fetchPayments()
    }
}