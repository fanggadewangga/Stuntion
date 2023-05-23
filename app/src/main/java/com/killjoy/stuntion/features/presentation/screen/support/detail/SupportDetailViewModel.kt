package com.killjoy.stuntion.features.presentation.screen.support.detail

import android.content.Context
import android.location.Geocoder
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.killjoy.stuntion.R
import com.killjoy.stuntion.features.data.repository.donation.DonationRepository
import com.killjoy.stuntion.features.data.repository.user.UserRepository
import com.killjoy.stuntion.features.data.source.remote.api.response.donation.DonationResponse
import com.killjoy.stuntion.features.data.source.remote.api.response.donation.DonorBody
import com.killjoy.stuntion.features.data.source.remote.api.response.donation.DonorResponse
import com.killjoy.stuntion.features.data.source.remote.api.response.user.UserBalanceResponse
import com.killjoy.stuntion.features.data.util.Resource
import com.killjoy.stuntion.features.domain.model.support_nominal.SupportNominal
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(ExperimentalMaterialApi::class)
@HiltViewModel
class SupportDetailViewModel @Inject constructor(private val donationRepository: DonationRepository, private val userRepository: UserRepository) : ViewModel() {

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
    val sheetState = mutableStateOf(ModalBottomSheetValue.Hidden)
    val isAnonymous = mutableStateOf(false)
    val userLatState = mutableStateOf(0.0)
    val userLonState = mutableStateOf(0.0)
    val userAddress = mutableStateOf("")
    val isUserAddressFieldClicked = mutableStateOf(false)
    val isUserAddressValid = derivedStateOf {
        userAddress.value != "" || !isUserAddressFieldClicked.value
    }

    private val _donationResponse =
        MutableStateFlow<Resource<DonationResponse?>>(Resource.Loading())
    val donationResponse = _donationResponse.asStateFlow()

    private val _donorResponse = MutableStateFlow<Resource<List<DonorResponse>>>(Resource.Loading())
    val donorResponse = _donorResponse.asStateFlow()

    private val _giveDonationResponse = MutableStateFlow<Resource<String?>>(Resource.Empty())
    val giveDonationResponse = _giveDonationResponse.asStateFlow()

    private val _userBalanceResponse = MutableStateFlow<Resource<UserBalanceResponse?>>(Resource.Empty())
    val userBalanceResponse = _userBalanceResponse.asStateFlow()
    suspend fun fetchDonationDetail(donationId: String) {
        viewModelScope.launch {
            donationRepository.fetchDonationDetail(donationId).collect {
                _donationResponse.value = it
            }
        }
    }

    suspend fun fetchDonationDonors(donationId: String) {
        viewModelScope.launch {
            donationRepository.fetchDonationDonors(donationId).collect {
                _donorResponse.value = it
            }
        }
    }

    suspend fun fetchUserWalletBalance() {
        viewModelScope.launch {
            val uid = userRepository.readUid().first()
            if (uid != null) {
                userRepository.fetchUserBalance(uid).collect {
                    _userBalanceResponse.value = it
                }
            }
        }
    }

    suspend fun giveNewDonation(donationId: String, isAnonymous: Boolean) {
        viewModelScope.launch {
            val uid = userRepository.readUid().first()
            if (uid != null) {
                val donorBody = DonorBody(uid, nominalState.value.toDouble(), isAnonymous)
                userRepository.giveNewDonation(donorBody, donationId).collect {
                    _giveDonationResponse.value = it
                }
            }
        }
    }

    @Suppress("DEPRECATION")
    fun getAddressFromCoordinate(context: Context) {
        viewModelScope.launch {
            val geoCoder = Geocoder(context)
            val address = geoCoder.getFromLocation(userLatState.value, userLonState.value, 1)
            userAddress.value = address?.get(0)?.getAddressLine(0).toString()
        }
    }
}