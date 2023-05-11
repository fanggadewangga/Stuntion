package com.killjoy.stuntion.features.presentation.screen.expert

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.killjoy.stuntion.features.data.repository.expert.ExpertRepository
import com.killjoy.stuntion.features.data.source.remote.api.response.expert.ExpertResponse
import com.killjoy.stuntion.features.data.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExpertDetailViewModel @Inject constructor(private val repository: ExpertRepository) : ViewModel() {

    private val _expertResponse = MutableStateFlow<Resource<ExpertResponse?>>(Resource.Loading())
    val expertResponse = _expertResponse.asStateFlow()
    fun fetchExpertDetail(expertId: String) {
        viewModelScope.launch {
            repository.fetchExpertDetail(expertId).collect {
                _expertResponse.value = it
            }
        }
    }
}