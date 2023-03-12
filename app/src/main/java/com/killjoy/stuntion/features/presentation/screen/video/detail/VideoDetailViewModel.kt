package com.killjoy.stuntion.features.presentation.screen.video.detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.killjoy.stuntion.features.data.repository.article.ArticleRepository
import com.killjoy.stuntion.features.data.source.remote.api.response.article.ArticleListResponse
import com.killjoy.stuntion.features.data.source.remote.api.response.article.ArticleResponse
import com.killjoy.stuntion.features.data.source.remote.api.response.donation.DonationResponse
import com.killjoy.stuntion.features.data.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class VideoDetailViewModel @Inject constructor(private val repository: ArticleRepository) : ViewModel() {
    private val _detailResponse = MutableStateFlow<Resource<ArticleResponse?>>(Resource.Loading())
    val detailResponse = _detailResponse.asStateFlow()

    private val _listResponse = MutableStateFlow<Resource<List<ArticleListResponse>>>(Resource.Loading())
    val listResponse = _listResponse.asStateFlow()

    suspend fun fetchSmartstunDetail(smartstunId: String) {
        viewModelScope.launch {
            repository.fetchArticleDetail(smartstunId).collect {
                _detailResponse.value = it
            }
        }
    }

    private fun fetchSmartstuns() {
        viewModelScope.launch {
            repository.fetchArticles().collect {
                _listResponse.value = it
            }
        }
    }

    init {
        fetchSmartstuns()
    }
}