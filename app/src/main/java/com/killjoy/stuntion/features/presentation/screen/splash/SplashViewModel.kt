package com.killjoy.stuntion.features.presentation.screen.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.killjoy.stuntion.features.data.repository.user.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val repository: UserRepository) : ViewModel() {

    val uid = MutableStateFlow<String?>(null)
    val isHaveCreatedAccount = MutableStateFlow(false)
    val isHaveRunAppBefore = MutableStateFlow(false)
    suspend fun readUid() = viewModelScope.launch {
        repository.readUid().collect {
            uid.value = it
        }
    }

    suspend fun readHaveCreatedAccount() = viewModelScope.launch {
        repository.readHaveCreatedAccount().collect {
            isHaveCreatedAccount.value = it
        }
    }

    suspend fun readHaveRunAppBefore() = viewModelScope.launch {
        repository.readHaveRunAppBefore().collect {
            isHaveRunAppBefore.value = it
        }
    }
}