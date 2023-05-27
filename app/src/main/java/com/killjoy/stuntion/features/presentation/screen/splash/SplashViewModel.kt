package com.killjoy.stuntion.features.presentation.screen.splash

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.killjoy.stuntion.features.data.repository.user.UserRepository
import com.killjoy.stuntion.features.data.source.remote.api.response.user.UserResponse
import com.killjoy.stuntion.features.data.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val repository: UserRepository) : ViewModel() {

    val uid = MutableStateFlow<String?>(null)
    val isHaveRunAppBefore = MutableStateFlow(false)
    private val _userResponse = MutableStateFlow<Resource<UserResponse?>>(Resource.Empty())
    val userResponse = _userResponse.asStateFlow()
    private fun fetchUserDetail() {
        viewModelScope.launch {
            val uid = repository.readUid().first()
            if (uid != null) {
                repository.fetchUserDetail(uid).collect {
                    _userResponse.value = it
                }
            }
        }
    }

    private fun readHaveRunAppBefore() = viewModelScope.launch {
        repository.readHaveRunAppBefore().collect {
            isHaveRunAppBefore.value = it
        }
    }
    suspend fun saveUserIndex(index: Int) {
        Log.d("Index", index.toString())
        repository.saveRegisterProgressIndex(index)
    }

    init {
        readHaveRunAppBefore()
        fetchUserDetail()
    }
}