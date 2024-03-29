package com.killjoy.stuntion.features.presentation.screen.ask_expert

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.killjoy.stuntion.features.data.repository.question.QuestionRepository
import com.killjoy.stuntion.features.data.repository.user.UserRepository
import com.killjoy.stuntion.features.data.source.remote.api.response.question.QuestionListResponse
import com.killjoy.stuntion.features.data.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AskExpertViewModel @Inject constructor(private val repository: QuestionRepository, private val userRepository: UserRepository) :
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
    val currentRegistrationState = mutableStateOf(0)
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

    private fun getUserRegisterProgressIndex() {
        viewModelScope.launch {
            val progressIndex = userRepository.readRegisterProgressIndex().first()
            currentRegistrationState.value = progressIndex
        }
    }


    init {
        fetchQuestions()
        getUserRegisterProgressIndex()
    }
}