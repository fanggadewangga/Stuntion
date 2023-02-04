package com.killjoy.stuntion.features.presentation.screen.auth.signup

import android.util.Log
import android.util.Patterns
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.killjoy.stuntion.features.data.source.remote.firebase.FirebaseDataSource
import com.killjoy.stuntion.features.data.source.remote.firebase.FirebaseResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(private val firebaseDataSource: FirebaseDataSource) :
    ViewModel() {
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
        passwordConfirmState.value.isNotEmpty() && (passwordState.value != passwordConfirmState.value)
    }

    private val _firebaseSignUpResponse = MutableStateFlow<FirebaseResponse<String>>(FirebaseResponse.Empty)
    val firebaseSignUpResponse = _firebaseSignUpResponse.asStateFlow()
    fun signUp() = viewModelScope.launch {
        firebaseDataSource.createUserWithEmailAndPassword(
            email = emailState.value,
            password = passwordState.value
        ).collect {
            _firebaseSignUpResponse.value = it
        }
    }
}