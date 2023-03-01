package com.killjoy.stuntion.features.presentation.screen.chat

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChatRoomViewModel @Inject constructor(): ViewModel() {
    val chatTextState = mutableStateOf("")

}