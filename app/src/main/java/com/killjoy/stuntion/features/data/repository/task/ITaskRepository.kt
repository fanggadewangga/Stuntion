package com.killjoy.stuntion.features.data.repository.task

import com.killjoy.stuntion.features.data.source.remote.api.response.task.TaskListResponse
import com.killjoy.stuntion.features.data.source.remote.api.response.task.TaskResponse
import com.killjoy.stuntion.features.data.source.remote.api.response.task.UserTaskBody
import com.killjoy.stuntion.features.data.util.Resource
import kotlinx.coroutines.flow.Flow

interface ITaskRepository {
    suspend fun postNewUserTask(uid: String, body: UserTaskBody): Flow<Resource<String>>
    suspend fun fetchTaskByUser(uid: String): Flow<Resource<List<TaskListResponse>>>
    suspend fun fetchTaskDetail(uid: String, taskId: String): Flow<Resource<TaskResponse>>
}