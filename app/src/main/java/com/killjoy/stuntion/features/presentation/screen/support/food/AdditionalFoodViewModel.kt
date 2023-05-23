package com.killjoy.stuntion.features.presentation.screen.support.food

import android.net.Uri
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AdditionalFoodViewModel @Inject constructor(): ViewModel() {
    var selectedImageUri = mutableStateOf<Uri?>(null)
    val foodQuantityState = mutableStateOf(0)
    val isFoodFieldClicked = mutableStateOf(false)
    val isValidQuantity = derivedStateOf {
        foodQuantityState.value != 0 || !isFoodFieldClicked.value
    }
}