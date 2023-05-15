package com.killjoy.stuntion.features.presentation.screen.support.detail

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.killjoy.stuntion.features.data.repository.donation.DonationRepository
import com.killjoy.stuntion.features.data.source.remote.api.response.donation.DonationResponse
import com.killjoy.stuntion.features.data.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SupportDetailViewModel @Inject constructor(private val repository: DonationRepository): ViewModel() {

    val isDescriptionVisibleState = mutableStateOf(true)
    val isPermissionGranted = mutableStateOf(false)
    private val _donationResponse = MutableStateFlow<Resource<DonationResponse?>>(Resource.Loading())
    val donationResponse = _donationResponse.asStateFlow()

    suspend fun fetchDonationDetail(donationId: String) {
        viewModelScope.launch {
            repository.fetchDonationDetail(donationId).collect {
                _donationResponse.value = it
            }
        }
    }
}