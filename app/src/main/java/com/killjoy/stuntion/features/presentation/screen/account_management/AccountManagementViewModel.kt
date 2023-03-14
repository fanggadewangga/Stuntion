package com.killjoy.stuntion.features.presentation.screen.account_management

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.killjoy.stuntion.features.data.repository.user.UserRepository
import com.killjoy.stuntion.features.data.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountManagementViewModel @Inject constructor(private val repository: UserRepository): ViewModel() {

    val logoutResponse = MutableStateFlow<Resource<String>>(Resource.Empty())

    fun logout() {
        viewModelScope.launch {
            repository.logout().collect {
                logoutResponse.value = it
            }
        }
    }
}