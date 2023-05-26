package com.killjoy.stuntion.features.presentation.screen.general_information

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.killjoy.stuntion.features.data.repository.user.UserRepository
import com.killjoy.stuntion.features.data.source.remote.api.response.user.UserGeneralInfoBody
import com.killjoy.stuntion.features.data.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class GeneralInformationViewModel @Inject constructor(private val repository: UserRepository) :
    ViewModel() {
    val nameState = mutableStateOf("")
    val isNameFieldClicked = mutableStateOf(false)
    val isNameValid = derivedStateOf {
        isNameFieldClicked.value && nameState.value.isEmpty()
    }

    val dateState = mutableStateOf("")
    val isDateFieldClicked = mutableStateOf(false)
    val isDateValid = derivedStateOf {
        isDateFieldClicked.value && dateState.value.isEmpty()
    }

    val selectedGender = mutableStateOf("")
    val pickedDate = mutableStateOf(LocalDate.now())
    val formattedDate = derivedStateOf {
        DateTimeFormatter.ofPattern("MM/dd/yyy").format(pickedDate.value)
    }

    private val _userState = MutableStateFlow<Resource<String?>>(Resource.Empty())
    val userState = _userState.asStateFlow()

    fun updateUserGeneralInformation() {
        val body = UserGeneralInfoBody(
            name = nameState.value,
            birthDate = DateTimeFormatter.ofPattern("MMMM dd yyyy").format(pickedDate.value),
            gender = selectedGender.value,
        )
        viewModelScope.launch {
            val uid = repository.readUid().first()!!
            repository.updateUserGeneralInformation(uid, body).collect {
                _userState.value = it
            }
        }
    }

    suspend fun saveUserIndex(index: Int) {
        repository.saveRegisterProgressIndex(index)
    }
}