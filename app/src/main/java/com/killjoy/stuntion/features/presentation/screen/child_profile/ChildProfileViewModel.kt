package com.killjoy.stuntion.features.presentation.screen.child_profile

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.killjoy.stuntion.features.data.repository.note.NoteRepository
import com.killjoy.stuntion.features.data.repository.task.TaskRepository
import com.killjoy.stuntion.features.data.repository.user.UserRepository
import com.killjoy.stuntion.features.data.source.remote.api.response.note.NoteBody
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
class ChildProfileViewModel @Inject constructor(
    private val noteRepository: NoteRepository,
    private val userRepository: UserRepository,
    private val taskRepository: TaskRepository,
) : ViewModel() {
    val ageInYear = mutableStateOf(0)
    val ageInMonth = mutableStateOf(0)
    val ageInDay = mutableStateOf(0)
    val idealWeight = mutableStateOf(0.0)
    val idealHeight = mutableStateOf(0.0)
    val heightDescription = mutableStateOf("")
    val weightDescription = mutableStateOf("")
    val currentRegistrationState = mutableStateOf(0)

    private val _postNoteResponse = MutableStateFlow<Resource<String>>(Resource.Empty())
    val postNoteResponse = _postNoteResponse.asStateFlow()

    private val _fetchTaskResponse = MutableStateFlow<Resource<List<TaskListResponse>>>(Resource.Loading())
    val fetchTaskResponse = _fetchTaskResponse.asStateFlow()


    fun postNewNote(
        childName: String,
        gender: String,
        height: Double,
        weight: Double,
        birthday: String,
    ) {
        viewModelScope.launch {
            val uid = userRepository.readUid().first()
            Log.d("UID", uid.toString())
            if (uid != null) {
                val newNote = NoteBody(
                    childName = childName,
                    gender = gender,
                    ageYear = ageInYear.value,
                    ageMonth = ageInMonth.value,
                    ageDay = ageInDay.value,
                    height = height,
                    heightDescription = heightDescription.value,
                    weight = weight,
                    weightDescription = weightDescription.value,
                    birthday = birthday,
                    idealHeight = idealHeight.value,
                    idealWeight = idealWeight.value,
                )
                noteRepository.postNewNote(uid, newNote).collect {
                    _postNoteResponse.value = it
                }
            }
        }
    }

    fun fetchTaskByUid() {
        viewModelScope.launch {
            val uid = userRepository.readUid().first()
            if (uid != null)
                taskRepository.fetchTaskByUser(uid).collect {
                    _fetchTaskResponse.value = it
                }
        }
    }

    fun fetchAllTask() {
        viewModelScope.launch {
            taskRepository.fetchAllTasks().collect {
                _fetchTaskResponse.value = it
            }
        }
    }

    private fun getUserRegisterProgressIndex() {
        viewModelScope.launch {
            val progressIndex = userRepository.readRegisterProgressIndex().first()
            currentRegistrationState.value = progressIndex
        }
    }

    init {
        getUserRegisterProgressIndex()
    }
}