package com.killjoy.stuntion.features.data.repository.question

import com.killjoy.stuntion.features.data.source.remote.api.response.question.QuestionBody
import com.killjoy.stuntion.features.data.source.remote.api.response.question.QuestionListResponse
import com.killjoy.stuntion.features.data.source.remote.api.response.question.QuestionResponse
import com.killjoy.stuntion.features.data.util.Resource
import kotlinx.coroutines.flow.Flow

interface IQuestionRepository {
    suspend fun postNewQuestion(body: QuestionBody): Flow<Resource<String>>
    suspend fun fetchQuestions(): Flow<Resource<List<QuestionListResponse>>>
    suspend fun searchQuestion(query: String): Flow<Resource<List<QuestionListResponse>>>
    suspend fun fetchQuestionDetail(questionId: String): Flow<Resource<QuestionResponse?>>
}