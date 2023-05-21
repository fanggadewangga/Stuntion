package com.killjoy.stuntion.features.presentation.screen.donor

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.killjoy.stuntion.features.data.repository.donation.DonationRepository
import com.killjoy.stuntion.features.data.source.remote.api.response.donation.DonorResponse
import com.killjoy.stuntion.features.data.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DonorViewModel @Inject constructor(private val donationRepository: DonationRepository): ViewModel() {
    val sortBy = listOf("Latest", "Biggest")
    val selectedSortState = mutableStateOf("")

    private val _donorResponse = MutableStateFlow<Resource<List<DonorResponse>>>(Resource.Loading())
    val donorResponse = _donorResponse.asStateFlow()

    suspend fun fetchDonationDonors(donationId: String) {
        viewModelScope.launch {
            donationRepository.fetchDonationDonors(donationId).collect {
                _donorResponse.value = it
            }
        }
    }
}