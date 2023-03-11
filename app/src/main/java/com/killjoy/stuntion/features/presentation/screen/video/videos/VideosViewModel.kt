package com.killjoy.stuntion.features.presentation.screen.video.videos

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.killjoy.stuntion.features.data.repository.article.ArticleRepository
import com.killjoy.stuntion.features.data.source.remote.api.response.article.ArticleListResponse
import com.killjoy.stuntion.features.data.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VideosViewModel @Inject constructor(private val repository: ArticleRepository): ViewModel() {
    val videosCategory = listOf(
        "All",
        "Stunting",
        "Nutrition",
        "Pregnant",
        "Child",
    )
    val selectedCategory = mutableStateOf("All")

    val searchState = mutableStateOf("")

    private val _smartstunResponse =
        MutableStateFlow<Resource<List<ArticleListResponse>>>(Resource.Loading())
    val smartstunResponse = _smartstunResponse.asStateFlow()
    private fun fetchSmartstuns() {
        viewModelScope.launch {
            repository.fetchArticles().collect {
                _smartstunResponse.value = it
            }
        }
    }

    fun searchSmartstuns() {
        viewModelScope.launch {
            repository.searchArticle(searchState.value).collect {
                _smartstunResponse.value = it
            }
        }
    }

    init {
        fetchSmartstuns()
    }
}