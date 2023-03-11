package com.killjoy.stuntion.features.presentation.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.killjoy.stuntion.R
import com.killjoy.stuntion.features.data.repository.article.ArticleRepository
import com.killjoy.stuntion.features.data.repository.donation.DonationRepository
import com.killjoy.stuntion.features.data.repository.user.UserRepository
import com.killjoy.stuntion.features.data.source.remote.api.response.article.ArticleListResponse
import com.killjoy.stuntion.features.data.source.remote.api.response.donation.DonationListResponse
import com.killjoy.stuntion.features.data.source.remote.api.response.user.UserResponse
import com.killjoy.stuntion.features.data.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val donationRepository: DonationRepository,
    private val articleRepository: ArticleRepository,
) : ViewModel() {
    val listOfBanner =
        listOf(R.drawable.iv_banner_1, R.drawable.iv_banner_2, R.drawable.iv_banner_3)

    private val _userResponse = MutableStateFlow<Resource<UserResponse?>>(Resource.Loading())
    val userResponse = _userResponse.asStateFlow()

    private val _donationResponse =
        MutableStateFlow<Resource<List<DonationListResponse>>>(Resource.Loading())
    val donationResponse = _donationResponse.asStateFlow()

    private val _smartstunResponse =
        MutableStateFlow<Resource<List<ArticleListResponse>>>(Resource.Loading())
    val smartstunResponse = _smartstunResponse.asStateFlow()

    fun fetchUserDetail() {
        viewModelScope.launch {
            val uid = userRepository.readUid().first()!!
            userRepository.fetchUserDetail(uid).collect {
                _userResponse.value = it
            }
        }
    }

    fun fetchDonations() {
        viewModelScope.launch {
            donationRepository.fetchDonations().collect {
                _donationResponse.value = it
            }
        }
    }

    fun fetchSmartstuns() {
        viewModelScope.launch {
            articleRepository.fetchArticles().collect {
                _smartstunResponse.value = it
            }
        }
    }

    init {
        fetchUserDetail()
        fetchDonations()
        fetchSmartstuns()
    }
}