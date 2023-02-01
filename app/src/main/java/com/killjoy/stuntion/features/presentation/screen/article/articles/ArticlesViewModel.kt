package com.killjoy.stuntion.features.presentation.screen.article.articles

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class ArticlesViewModel @Inject constructor(): ViewModel() {
    val searchState = mutableStateOf("")
}