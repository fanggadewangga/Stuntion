package com.killjoy.stuntion.features.presentation.screen.healthy_tips

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.killjoy.stuntion.features.data.repository.task.TaskRepository
import com.killjoy.stuntion.features.data.repository.user.UserRepository
import com.killjoy.stuntion.features.data.source.remote.api.response.task.TaskResponse
import com.killjoy.stuntion.features.data.source.remote.api.response.task.UserTaskBody
import com.killjoy.stuntion.features.data.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HealthyTipsDetailViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val taskRepository: TaskRepository,
) : ViewModel() {

    private val _fetchTaskDetailResponse = MutableStateFlow<Resource<TaskResponse>>(Resource.Loading())
    val fetchTaskDetailResponse = _fetchTaskDetailResponse.asStateFlow()

    private val _updateTaskStatusResponse = MutableStateFlow<Resource<String>>(Resource.Loading())
    val updateTaskStatusResponse = _updateTaskStatusResponse.asStateFlow()

    fun fetchTaskDetail(taskId: String) {
        viewModelScope.launch {
            val uid = userRepository.readUid().first()
            if (uid != null)
                taskRepository.fetchTaskDetail(uid, taskId).collect {
                    _fetchTaskDetailResponse.value = it
                }
        }
    }

    fun updateTaskStatus(taskId: String) {
        viewModelScope.launch {
            val uid = userRepository.readUid().first()
            if (uid != null) {
                val body = UserTaskBody(uid, taskId)
                taskRepository.postNewUserTask(uid, body).collect {
                    _updateTaskStatusResponse.value = it
                }
            }
        }
    }
}