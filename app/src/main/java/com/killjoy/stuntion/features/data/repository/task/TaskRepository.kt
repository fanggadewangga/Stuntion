package com.killjoy.stuntion.features.data.repository.task

import android.util.Log
import com.killjoy.stuntion.features.data.source.remote.api.response.task.TaskListResponse
import com.killjoy.stuntion.features.data.source.remote.api.response.task.TaskResponse
import com.killjoy.stuntion.features.data.source.remote.api.response.task.UserTaskBody
import com.killjoy.stuntion.features.data.source.remote.api.service.StuntionApi
import com.killjoy.stuntion.features.data.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class TaskRepository @Inject constructor(private val stuntionApi: StuntionApi) : ITaskRepository {
    override suspend fun postNewUserTask(uid: String, body: UserTaskBody): Flow<Resource<String>> =
        flow {
            emit(Resource.Loading())
            try {
                val response = stuntionApi.postNewUserTask(uid, body)
                if (!response.isError) {
                    Log.d("POST USER TASK", response.message)
                    emit(Resource.Success(response.message))
                } else
                    emit(Resource.Error(response.message))
            } catch (e: Exception) {
                Log.d("POST USER TASK", e.message.toString())
                emit(Resource.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)

    override suspend fun fetchAllTasks(): Flow<Resource<List<TaskListResponse>>> =
        flow {
            emit(Resource.Loading())
            try {
                val response = stuntionApi.fetchAllTasks()
                if (!response.isError) {
                    emit(Resource.Success(response.data))
                } else
                    emit(Resource.Error(response.message))
            } catch (e: Error) {
                emit(Resource.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)

    override suspend fun fetchTaskByUser(uid: String): Flow<Resource<List<TaskListResponse>>> =
        flow {
            emit(Resource.Loading())
            try {
                val response = stuntionApi.fetchTaskByUser(uid)
                if (!response.isError) {
                    Log.d("FETCH TASK", response.message)
                    emit(Resource.Success(response.data))
                } else
                    emit(Resource.Error(response.message))
            } catch (e: Error) {
                emit(Resource.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)

    override suspend fun fetchTaskDetail(
        uid: String,
        taskId: String,
    ): Flow<Resource<TaskResponse>> = flow {
        emit(Resource.Loading())
        try {
            val response = stuntionApi.fetchTaskDetail(uid, taskId)
            if (!response.isError) {
                Log.d("TASK DETAIL", response.message)
                emit(Resource.Success(response.data))
            } else
                emit(Resource.Error(response.message))
        } catch (e: Exception) {
            emit(Resource.Error(e.message.toString()))
        }
    }.flowOn(Dispatchers.IO)
}