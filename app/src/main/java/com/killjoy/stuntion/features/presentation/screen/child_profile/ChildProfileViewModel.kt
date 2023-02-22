package com.killjoy.stuntion.features.presentation.screen.child_profile

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChildProfileViewModel @Inject constructor(): ViewModel() {
    val ageInYear = mutableStateOf(0)
    val ageInMonth = mutableStateOf(0)
    val ageInDay = mutableStateOf(0)
    val idealWeight = mutableStateOf(0.0)
    val heightDescription = mutableStateOf("")
    val weightDescription = mutableStateOf("")
}