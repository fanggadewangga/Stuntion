package com.killjoy.stuntion.features.presentation.screen.ask_expert

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.killjoy.stuntion.features.data.repository.question.QuestionRepository
import com.killjoy.stuntion.features.data.source.remote.api.response.question.QuestionListResponse
import com.killjoy.stuntion.features.data.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AskExpertViewModel @Inject constructor(private val repository: QuestionRepository) :
    ViewModel() {
    val questionCategories = listOf(
        "All",
        "Stunting",
        "Nutrition",
        "Pregnant",
        "Child",
    )
    val selectedCategory = mutableStateOf("All")
    val searchState = mutableStateOf("")
    private val _questionResponse =
        MutableStateFlow<Resource<List<QuestionListResponse>>>(Resource.Loading())
    val questionResponse = _questionResponse.asStateFlow()
    private fun fetchQuestions() {
        viewModelScope.launch {
            repository.fetchQuestions().collect {
                _questionResponse.value = it
            }
        }
    }

    fun searchQuestion() {
        viewModelScope.launch {
            repository.searchQuestion(searchState.value).collect {
                _questionResponse.value = it
            }
        }
    }

    init {
        fetchQuestions()
    }
}