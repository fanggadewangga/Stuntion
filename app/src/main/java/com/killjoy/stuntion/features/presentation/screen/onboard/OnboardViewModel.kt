package com.killjoy.stuntion.features.presentation.screen.onboard

import androidx.lifecycle.ViewModel
import com.killjoy.stuntion.features.data.repository.user.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OnboardViewModel @Inject constructor(private val repository: UserRepository) : ViewModel() {
    suspend fun saveHaveRunAppBefore() = repository.saveHaveRunAppBefore(true)
}