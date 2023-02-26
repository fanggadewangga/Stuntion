package com.killjoy.stuntion.features.data.repository.question

import android.util.Log
import com.killjoy.stuntion.features.data.source.remote.api.response.question.QuestionBody
import com.killjoy.stuntion.features.data.source.remote.api.response.question.QuestionListResponse
import com.killjoy.stuntion.features.data.source.remote.api.response.question.QuestionResponse
import com.killjoy.stuntion.features.data.source.remote.api.service.StuntionApi
import com.killjoy.stuntion.features.data.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class QuestionRepository @Inject constructor(private val stuntionApi: StuntionApi) :
    IQuestionRepository {
    override suspend fun postNewQuestion(body: QuestionBody): Flow<Resource<String>> = flow {
        emit(Resource.Loading())
        try {
            val response = stuntionApi.postNewQuestion(body)
            if (!response.isError) {
                Log.d("POST DONATION", response.message)
                emit(Resource.Success(response.message))
            } else
                emit(Resource.Error(response.message))
        } catch (e: Exception) {
            Log.d("POST DONATION", e.message.toString())
            emit(Resource.Error(e.message.toString()))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun fetchQuestions(): Flow<Resource<List<QuestionListResponse>>> = flow {
        emit(Resource.Loading())
        try {
            val response = stuntionApi.fetchQuestions()
            if (!response.isError) {
                Log.d("FETCH QUESTIONS", response.message)
                emit(Resource.Success(response.data))
            } else
                emit(Resource.Empty())
        } catch (e: Exception) {
            Log.d("FETCH QUESTION", e.message.toString())
            emit(Resource.Error(e.message.toString()))
        }
    }

    override suspend fun searchQuestion(query: String): Flow<Resource<List<QuestionListResponse>>> =
        flow {
            emit(Resource.Loading())
            try {
                val response = stuntionApi.searchQuestion(query)
                if (!response.isError) {
                    Log.d("FETCH QUESTIONS", response.message)
                    emit(Resource.Success(response.data))
                } else
                    emit(Resource.Empty())
            } catch (e: Exception) {
                Log.d("FETCH QUESTION", e.message.toString())
                emit(Resource.Error(e.message.toString()))
            }
        }

    override suspend fun fetchQuestionDetail(questionId: String): Flow<Resource<QuestionResponse?>> =
        flow {
            emit(Resource.Loading())
            try {
                val response = stuntionApi.fetchQuestionDetail(questionId)
                if (!response.isError) {
                    Log.d("DETAIL QUESTION", response.message)
                    emit(Resource.Success(response.data))
                } else
                    emit(Resource.Empty())
            } catch (e: Exception) {
                emit(Resource.Error(e.message.toString()))
            }
        }
}