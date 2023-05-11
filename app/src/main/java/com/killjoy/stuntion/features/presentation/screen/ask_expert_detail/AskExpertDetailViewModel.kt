package com.killjoy.stuntion.features.presentation.screen.ask_expert_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.killjoy.stuntion.features.data.repository.question.QuestionRepository
import com.killjoy.stuntion.features.data.source.remote.api.response.question.QuestionListResponse
import com.killjoy.stuntion.features.data.source.remote.api.response.question.QuestionResponse
import com.killjoy.stuntion.features.data.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AskExpertDetailViewModel @Inject constructor(private val repository: QuestionRepository) : ViewModel() {

    private val _questionListResponse = MutableStateFlow<Resource<List<QuestionListResponse>>>(Resource.Loading())
    val questionListResponse = _questionListResponse.asStateFlow()

    private val _questionResponse = MutableStateFlow<Resource<QuestionResponse?>>(Resource.Loading())
    val questionResponse = _questionResponse.asStateFlow()
    private fun fetchQuestions() {
        viewModelScope.launch {
            repository.fetchQuestions().collect {
                _questionListResponse.value = it
            }
        }
    }

    fun fetchQuestionDetail(questionId: String) {
        viewModelScope.launch {
            repository.fetchQuestionDetail(questionId).collect {
                _questionResponse.value = it
            }
        }
    }

    init {
        fetchQuestions()
    }
}