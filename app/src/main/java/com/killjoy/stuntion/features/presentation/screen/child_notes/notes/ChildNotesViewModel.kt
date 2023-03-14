package com.killjoy.stuntion.features.presentation.screen.child_notes.notes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.killjoy.stuntion.features.data.repository.note.NoteRepository
import com.killjoy.stuntion.features.data.repository.user.UserRepository
import com.killjoy.stuntion.features.data.source.remote.api.response.note.NoteResponse
import com.killjoy.stuntion.features.data.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChildNotesViewModel @Inject constructor(private val noteRepository: NoteRepository, private val userRepository: UserRepository): ViewModel() {
    private val _notesResponse = MutableStateFlow<Resource<List<NoteResponse>>>(Resource.Loading())
    val noteResponse = _notesResponse.asStateFlow()

    private fun fetchUserNotes() {
        viewModelScope.launch {
            val uid = userRepository.readUid().first()
            if (uid != null) {
                noteRepository.fetchNotesByUser(uid).collect {
                    _notesResponse.value = it
                }
            }
        }
    }

    init {
        fetchUserNotes()
    }
}