package com.killjoy.stuntion.features.presentation.screen.auth.login

import android.util.Patterns
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.killjoy.stuntion.features.data.source.remote.firebase.FirebaseDataSource
import com.killjoy.stuntion.features.data.source.remote.firebase.FirebaseResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val firebaseDataSource: FirebaseDataSource,
) : ViewModel() {
    val emailState = mutableStateOf("")
    val isValidEmailState = derivedStateOf {
        emailState.value.isNotEmpty() && !Patterns.EMAIL_ADDRESS.matcher(emailState.value).matches()
    }
    val passwordState = mutableStateOf("")
    val isValidPasswordState = derivedStateOf {
        passwordState.value.isNotEmpty() && passwordState.value.length < 6
    }

    private val _firebaseLoginResponse =
        MutableStateFlow<FirebaseResponse<String>>(FirebaseResponse.Empty)
    val firebaseLoginResponse = _firebaseLoginResponse.asStateFlow()

    fun login() = viewModelScope.launch {
        firebaseDataSource.signInWithEmailAndPassword(
            email = emailState.value,
            password = passwordState.value
        ).collect {
            _firebaseLoginResponse.value = it
        }
    }

}