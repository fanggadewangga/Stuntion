package com.killjoy.stuntion.features.presentation.screen.check

import android.content.Context
import android.util.Log
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.killjoy.stuntion.features.domain.model.zscore.ZScoreStandard
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVParser
import java.io.BufferedReader
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.Period
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class CheckViewModel @Inject constructor() : ViewModel() {
    val genderState = mutableStateOf("Male")
    private val isGenderValid = derivedStateOf {
        genderState.value.isEmpty()
    }

    val dateState = mutableStateOf("")
    val isDateFieldClicked = mutableStateOf(false)
    val isDateValid = derivedStateOf {
        isDateFieldClicked.value && dateState.value.isEmpty()
    }

    val heightState = mutableStateOf("")
    val isHeightFieldClicked = mutableStateOf(false)
    val isHeightValid = derivedStateOf {
        isHeightFieldClicked.value && heightState.value.isEmpty()
    }

    val weightState = mutableStateOf("")
    val isWeightFieldClicked = mutableStateOf(false)
    val isWeightValid = derivedStateOf {
        isWeightFieldClicked.value && weightState.value.isEmpty()
    }

    val nameState = mutableStateOf("")
    val isNameFieldClicked = mutableStateOf(false)
    val isNameValid = derivedStateOf {
        isNameFieldClicked.value && nameState.value.isEmpty()
    }

    val isFormValid = derivedStateOf {
        isGenderValid.value && isNameValid.value && isDateValid.value && isWeightValid.value && isHeightValid.value
    }
}