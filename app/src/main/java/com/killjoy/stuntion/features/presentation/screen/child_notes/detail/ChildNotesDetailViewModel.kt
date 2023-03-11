package com.killjoy.stuntion.features.presentation.screen.child_notes.detail

import androidx.lifecycle.ViewModel
import com.killjoy.stuntion.features.data.repository.user.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChildNotesDetailViewModel @Inject constructor(private val userRepository: UserRepository): ViewModel() {
}