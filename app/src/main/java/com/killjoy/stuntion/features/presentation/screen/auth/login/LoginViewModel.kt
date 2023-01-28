package com.killjoy.stuntion.features.presentation.screen.auth.login

import android.util.Patterns
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(): ViewModel() {
    val emailState = mutableStateOf("")
    val isValidEmailState = derivedStateOf {
         emailState.value.isNotEmpty() && !Patterns.EMAIL_ADDRESS.matcher(emailState.value).matches()
    }
    val passwordState = mutableStateOf("")
    val isValidPasswordState = derivedStateOf {
         passwordState.value.isNotEmpty() && passwordState.value.length < 6
    }
}