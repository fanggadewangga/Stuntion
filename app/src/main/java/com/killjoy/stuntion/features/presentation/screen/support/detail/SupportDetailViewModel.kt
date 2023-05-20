package com.killjoy.stuntion.features.presentation.screen.support.detail

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.killjoy.stuntion.R
import com.killjoy.stuntion.features.data.repository.donation.DonationRepository
import com.killjoy.stuntion.features.data.source.remote.api.response.donation.DonationResponse
import com.killjoy.stuntion.features.data.util.Resource
import com.killjoy.stuntion.features.domain.model.support_nominal.SupportNominal
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SupportDetailViewModel @Inject constructor(private val repository: DonationRepository): ViewModel() {

    val isDescriptionVisibleState = mutableStateOf(true)
    val isPermissionGranted = mutableStateOf(false)
    val supportTypeState = mutableStateOf(0)
    val nominalState = mutableStateOf(0)
    val isNominalFieldClicked = mutableStateOf(false)
    val isNominalValid = derivedStateOf {
        nominalState.value != 0 || !isNominalFieldClicked.value
    }
    val sendSupportStepState = mutableStateOf(1)
    val listOfSupportNominal = listOf(
        SupportNominal(R.drawable.ic_emoji_1, 2000),
        SupportNominal(R.drawable.ic_emoji_2, 5000),
        SupportNominal(R.drawable.ic_emoji_3, 10000),
        SupportNominal(R.drawable.ic_emoji_4, 20000),
        SupportNominal(R.drawable.ic_emoji_5, 50000),
    )

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