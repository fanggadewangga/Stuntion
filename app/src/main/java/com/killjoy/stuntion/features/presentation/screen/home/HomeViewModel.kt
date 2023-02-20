package com.killjoy.stuntion.features.presentation.screen.home

import androidx.lifecycle.ViewModel
import com.killjoy.stuntion.R
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {
    val listOfBanner =
        listOf(R.drawable.iv_banner_1, R.drawable.iv_banner_2, R.drawable.iv_banner_3)
}