package com.killjoy.stuntion.features.presentation.screen.auth.signup

import android.util.Patterns
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor() : ViewModel() {
    val emailState = mutableStateOf("")
    val passwordState = mutableStateOf("")
    val passwordConfirmState = mutableStateOf("")
    val isValidEmailState = derivedStateOf {
        emailState.value.isNotEmpty() && !Patterns.EMAIL_ADDRESS.matcher(emailState.value).matches()
    }
    val isValidPasswordState = derivedStateOf {
        passwordState.value.isNotEmpty() && passwordState.value.length < 6
    }
    val isValidConfirmPasswordState = derivedStateOf {
        passwordConfirmState.value.isNotEmpty() && (passwordState.value == passwordConfirmState.value)
    }
}