package com.killjoy.stuntion.features.presentation.screen.payment.instruction

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.killjoy.stuntion.features.data.repository.user.UserRepository
import com.killjoy.stuntion.features.data.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PaymentInstructionViewModel @Inject constructor(private val repository: UserRepository): ViewModel() {
    val totalSecond = mutableStateOf(3600L)
    val formattedTime = mutableStateOf("")
    val bankAccountNumber = mutableStateOf(0)
    private val _walletResponse = MutableStateFlow<Resource<String?>>(Resource.Empty())
    val walletResponse = _walletResponse.asStateFlow()

    fun updateUserWalletBalance(balance: Double) {
        viewModelScope.launch {
            val uid = repository.readUid().first()
            if (uid != null) {
                repository.updateUserWalletBalance(uid = uid, balance = balance).collect {
                    _walletResponse.value = it
                }
            }
        }
    }
}