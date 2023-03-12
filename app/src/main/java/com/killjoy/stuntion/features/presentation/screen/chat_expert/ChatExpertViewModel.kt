package com.killjoy.stuntion.features.presentation.screen.chat_expert

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.killjoy.stuntion.features.data.repository.expert.ExpertRepository
import com.killjoy.stuntion.features.data.source.remote.api.response.article.ArticleListResponse
import com.killjoy.stuntion.features.data.source.remote.api.response.expert.ExpertListResponse
import com.killjoy.stuntion.features.data.util.Resource
import com.killjoy.stuntion.features.domain.model.expert.ExpertCategory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatExpertViewModel @Inject constructor(private val repository: ExpertRepository) :
    ViewModel() {
    val searchState = mutableStateOf("")
    val listOfExpertCategory = listOf(
        ExpertCategory(title = "Nutritionist"),
        ExpertCategory(title = "General Practitioner"),
        ExpertCategory(title = "Midwife"),
        ExpertCategory(title = "Pediatrician"),
        ExpertCategory(title = "Obgyn"),
        ExpertCategory(title = "Obstetricians")
    )
    private val _expertResponse =
        MutableStateFlow<Resource<List<ExpertListResponse>>>(Resource.Loading())
    val expertResponse = _expertResponse.asStateFlow()

    private fun fetchExperts() {
        viewModelScope.launch {
            repository.fetchExperts().collect {
                _expertResponse.value = it
            }
        }
    }

    fun searchExpert() {
        viewModelScope.launch {
            repository.searchExpert(searchState.value).collect {
                _expertResponse.value = it
            }
        }
    }

    init {
        fetchExperts()
    }
}