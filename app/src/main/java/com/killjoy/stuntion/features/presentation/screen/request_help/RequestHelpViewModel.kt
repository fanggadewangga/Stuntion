package com.killjoy.stuntion.features.presentation.screen.request_help

import android.net.Uri
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.killjoy.stuntion.features.data.repository.donation.DonationRepository
import com.killjoy.stuntion.features.data.repository.user.UserRepository
import com.killjoy.stuntion.features.data.source.remote.api.response.donation.DonationBody
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
class RequestHelpViewModel @Inject constructor(
    private val donationRepository: DonationRepository,
    private val userRepository: UserRepository,
) : ViewModel() {
    val currentStep = mutableStateOf(1)
    val listOfStep = listOf(
        "Personal Data",
        "Help Targets",
        "Tittle",
        "Information",
    )

    // Personal Data
    val nameState = mutableStateOf("")
    val isNameFieldClicked = mutableStateOf(false)
    val isValidName = derivedStateOf {
        nameState.value.isNotEmpty() || !isNameFieldClicked.value
    }

    val phoneState = mutableStateOf("")
    val isPhoneFieldClicked = mutableStateOf(false)
    val isValidPhone = derivedStateOf {
        phoneState.value.isNotEmpty() || !isPhoneFieldClicked.value
    }

    val addressState = mutableStateOf("")
    val isAddressFieldClicked = mutableStateOf(false)
    val isValidAddress = derivedStateOf {
        addressState.value.isNotEmpty() || !isAddressFieldClicked.value
    }

    val jobState = mutableStateOf("")
    val isJobFieldClicked = mutableStateOf(false)
    val isValidJob = derivedStateOf {
        jobState.value.isNotEmpty() || !isJobFieldClicked.value
    }

    // Help targets
    val endDate = mutableStateOf(LocalDate.now())
    val isPickedAnEndDate = mutableStateOf(false)

    val listOfDuration = listOf(
        "10 days",
        "30 days",
        "60 days",
        "Choose a date",
    )

    val selectedDuration = mutableStateOf(listOfDuration[0])

    val foodState = mutableStateOf(0)
    val isFoodFieldClicked = mutableStateOf(false)
    val isValidFood = derivedStateOf {
        foodState.value != 0 || !isFoodFieldClicked.value
    }

    val costState = mutableStateOf(0)
    val isCostFieldClicked = mutableStateOf(false)
    val isValidCost = derivedStateOf {
        costState.value != 0 || !isCostFieldClicked.value
    }

    val dateState = mutableStateOf("")

    val formattedEndDate = derivedStateOf {
        DateTimeFormatter.ofPattern("MMMM dd yyyy").format(endDate.value)
    }

    // Title
    val titleState = mutableStateOf("")
    val isTitleFieldClicked = mutableStateOf(false)
    val isValidTitle = derivedStateOf {
        titleState.value.isNotEmpty() || !isTitleFieldClicked.value
    }
    var selectedImageUri = mutableStateOf<Uri?>(null)

    // Detail Information
    val storyState = mutableStateOf("")
    val isStoryFieldClicked = mutableStateOf(false)
    val isValidStory = derivedStateOf {
        storyState.value.isNotEmpty() || !isStoryFieldClicked.value
    }

    // Confirmation
    val listOfCheck = listOf(
        "Personal data is correct",
        "Your need for help is clear and true",
        "The title and image are according to your request",
        "The story you describe already includes the information in your request for help",
        "You agree to the Terms & Conditions of Stuntion",
    )
    val listOfChecked = mutableStateListOf<String>()
    val formValidationCounter = mutableStateOf(0)
    val isFormValid = derivedStateOf {
        formValidationCounter.value == listOfCheck.size
    }

    private val _postDonationResponse = MutableStateFlow<Resource<String>>(Resource.Empty())
    val postDonationResponse = _postDonationResponse.asStateFlow()

    fun postNewDonation() {
        viewModelScope.launch {
            val uid = userRepository.readUid().first()
            if (uid != null && selectedImageUri.value != null) {
                val newDonationBody = DonationBody(
                    uid = uid,
                    phone = phoneState.value,
                    title = titleState.value,
                    address = addressState.value,
                    story = storyState.value,
                    deadlineAt = formattedEndDate.value,
                    maxValue = foodState.value,
                    fee = costState.value,
                )
                donationRepository.postNewDonation(
                    body = newDonationBody,
                    imageUri = selectedImageUri.value!!
                )
                    .collect {
                        _postDonationResponse.value = it
                    }
            }
        }
    }
}