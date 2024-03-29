package com.killjoy.stuntion.features.presentation.screen.support.supports

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.killjoy.stuntion.features.data.repository.donation.DonationRepository
import com.killjoy.stuntion.features.data.repository.user.UserRepository
import com.killjoy.stuntion.features.data.source.remote.api.response.donation.DonationListResponse
import com.killjoy.stuntion.features.data.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SupportViewModel @Inject constructor(
    private val repository: DonationRepository,
    private val userRepository: UserRepository,
) : ViewModel() {
    val searchState = mutableStateOf("")
    val isPermissionGranted = mutableStateOf(false)
    val userLatState = mutableStateOf(0.0)
    val userLonState = mutableStateOf(0.0)
    val currentRegistrationState = mutableStateOf(0)
    private val _donationResponse =
        MutableStateFlow<Resource<List<DonationListResponse>>>(Resource.Loading())
    val donationResponse = _donationResponse.asStateFlow()

    private fun fetchDonations() {
        viewModelScope.launch {
            repository.fetchDonations().collect {
                _donationResponse.value = it
            }
        }
    }

    fun searchDonations() {
        viewModelScope.launch {
            repository.searchDonation(searchState.value).collect {
                _donationResponse.value = it
            }
        }
    }

    private fun getUserRegisterProgressIndex() {
        viewModelScope.launch {
            val progressIndex = userRepository.readRegisterProgressIndex().first()
            currentRegistrationState.value = progressIndex
        }
    }

    init {
        fetchDonations()
        getUserRegisterProgressIndex()
    }
}