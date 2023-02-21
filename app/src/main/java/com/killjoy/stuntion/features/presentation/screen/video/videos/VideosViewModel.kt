package com.killjoy.stuntion.features.presentation.screen.video.videos

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class VideosViewModel @Inject constructor(): ViewModel() {
    val videosCategory = listOf(
        "All",
        "Stunting",
        "Nutrition Consultation",
        "Pregnant",
        "Child",
    )
    val searchState = mutableStateOf("")
}