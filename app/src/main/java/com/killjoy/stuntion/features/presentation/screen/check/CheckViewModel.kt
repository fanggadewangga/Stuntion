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
    val isDateNotValid = derivedStateOf {
        isDateFieldClicked.value && dateState.value.isEmpty()
    }

    val heightState = mutableStateOf("")
    val isHeightFieldClicked = mutableStateOf(false)
    val isHeightNotValid = derivedStateOf {
        isHeightFieldClicked.value && heightState.value.isEmpty()
    }

    val weightState = mutableStateOf("")
    val isWeightFieldClicked = mutableStateOf(false)
    val isWeightNotValid = derivedStateOf {
        isWeightFieldClicked.value && weightState.value.isEmpty()
    }

    val nameState = mutableStateOf("")
    val isNameFieldClicked = mutableStateOf(false)
    val isNameNotValid = derivedStateOf {
        isNameFieldClicked.value && nameState.value.isEmpty()
    }

    val isFormValid = derivedStateOf {
        Log.d("GENDER", isGenderValid.value.toString())
        Log.d("DATE", isDateNotValid.value.toString())
        Log.d("NAME", isNameNotValid.value.toString())
        Log.d("WEIGHT", isWeightNotValid.value.toString())
        Log.d("HEIGHT", isHeightNotValid.value.toString())
        !isGenderValid.value && !isNameNotValid.value && !isDateNotValid.value && !isWeightNotValid.value && !isHeightNotValid.value
    }
}