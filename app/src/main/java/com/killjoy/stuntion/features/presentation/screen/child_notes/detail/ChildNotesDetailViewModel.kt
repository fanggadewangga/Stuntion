package com.killjoy.stuntion.features.presentation.screen.child_notes.detail

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.killjoy.stuntion.features.data.repository.note.NoteRepository
import com.killjoy.stuntion.features.data.repository.task.TaskRepository
import com.killjoy.stuntion.features.data.repository.user.UserRepository
import com.killjoy.stuntion.features.data.source.remote.api.response.note.NoteResponse
import com.killjoy.stuntion.features.data.source.remote.api.response.task.TaskListResponse
import com.killjoy.stuntion.features.data.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChildNotesDetailViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val noteRepository: NoteRepository,
    private val taskRepository: TaskRepository,
) : ViewModel() {
    private val _noteResponse = MutableStateFlow<Resource<NoteResponse>>(Resource.Loading())
    val noteResponse = _noteResponse.asStateFlow()

    private val _fetchTaskResponse =
        MutableStateFlow<Resource<List<TaskListResponse>>>(Resource.Loading())
    val fetchTaskResponse = _fetchTaskResponse.asStateFlow()

    fun fetchNoteDetail(noteId: String) {
        viewModelScope.launch {
            val uid = userRepository.readUid().first()
            if (uid != null) {
                noteRepository.fetchNoteDetail(uid, noteId).collect {
                    _noteResponse.value = it
                }
            }
        }
    }

    private fun fetchAllTask() {
        viewModelScope.launch {
            taskRepository.fetchAllTasks().collect {
                _fetchTaskResponse.value = it
            }
        }
    }

    init {
        fetchAllTask()
    }
}