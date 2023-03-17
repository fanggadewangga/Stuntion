package com.killjoy.stuntion.features.presentation.screen.add_question

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.killjoy.stuntion.features.data.repository.question.QuestionRepository
import com.killjoy.stuntion.features.data.repository.user.UserRepository
import com.killjoy.stuntion.features.data.source.remote.api.response.question.QuestionBody
import com.killjoy.stuntion.features.data.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddQuestionViewModel @Inject constructor(
    private val questionRepository: QuestionRepository,
    private val userRepository: UserRepository,
) : ViewModel() {
    val considerDescriptions = listOf(
        "Please write down the questions clearly so that the discussion process is more interactive with the health professional.",
        "For questions regarding brands of milk, medicine, vitamins, and supplements, please consult directly with a health professional through private consultation.",
        "We have absolute authority to delete accounts and/or questions if, based on our judgment, we have violated the Terms.",
        "Please ask questions politely."
    )
    val categories = listOf("Stunting", "Nutrition", "Pregnant", "Child")

    val checkedState = mutableStateOf(false)
    val selectedCategoryItems = mutableStateListOf<String>()
    val selectedCategory = derivedStateOf {
        selectedCategoryItems.joinToString(", ")
    }
    val isConsiderDescriptionVisible = mutableStateOf(false)
    val questionTitleState = mutableStateOf("")
    val questionDescriptionState = mutableStateOf("")

    private val _questionResponse = MutableStateFlow<Resource<String>>(Resource.Empty())
    val questionResponse = _questionResponse.asStateFlow()

    fun postNewQuestion() {
        viewModelScope.launch {
            val uid = userRepository.readUid().first()
            if (uid != null) {
                val body = QuestionBody(
                    uid = uid,
                    title = questionTitleState.value,
                    question = questionDescriptionState.value,
                    categories = selectedCategoryItems.toList(),
                    isAnonymous = checkedState.value
                )
                questionRepository.postNewQuestion(body).collectLatest {
                    _questionResponse.value = it
                }
            }
        }
    }
}