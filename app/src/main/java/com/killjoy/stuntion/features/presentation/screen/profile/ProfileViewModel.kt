package com.killjoy.stuntion.features.presentation.screen.profile

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
class ProfileViewModel @Inject constructor(private val repository: UserRepository) : ViewModel() {
    private val _userResponse = MutableStateFlow<Resource<UserResponse?>>(Resource.Loading())
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

    init {
        fetchUserDetail()
    }
}