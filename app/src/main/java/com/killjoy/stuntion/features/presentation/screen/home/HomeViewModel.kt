package com.killjoy.stuntion.features.presentation.screen.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.killjoy.stuntion.R
import com.killjoy.stuntion.features.data.repository.article.ArticleRepository
import com.killjoy.stuntion.features.data.repository.donation.DonationRepository
import com.killjoy.stuntion.features.data.repository.task.TaskRepository
import com.killjoy.stuntion.features.data.repository.user.UserRepository
import com.killjoy.stuntion.features.data.source.Dummy
import com.killjoy.stuntion.features.data.source.remote.api.response.article.ArticleListResponse
import com.killjoy.stuntion.features.data.source.remote.api.response.donation.DonationListResponse
import com.killjoy.stuntion.features.data.source.remote.api.response.task.TaskResponse
import com.killjoy.stuntion.features.data.source.remote.api.response.user.UserResponse
import com.killjoy.stuntion.features.data.util.Resource
import com.killjoy.stuntion.features.domain.model.registration.RegistrationStep
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
    private val taskRepository: TaskRepository,
) : ViewModel() {
    val listOfBanner =
        listOf(R.drawable.iv_banner_3, R.drawable.iv_banner_1, R.drawable.iv_banner_2)

    val listOfRegistrationStep = listOf(
        RegistrationStep(title = "Login or create your account", subtitle = "Login to access all features"),
        RegistrationStep(title = "Next, complete your personal data", subtitle = "Personal data to make your account verified"),
        RegistrationStep(title = "Next, enable your location", subtitle = "We need your location if you need help fulfilling stunting nutrition"),
    )

    val currentRegistrationState = mutableStateOf(0)

    private val _userResponse = MutableStateFlow<Resource<UserResponse?>>(Resource.Loading())
    val userResponse = _userResponse.asStateFlow()

    private val _donationResponse =
        MutableStateFlow<Resource<List<DonationListResponse>>>(Resource.Loading())
    val donationResponse = _donationResponse.asStateFlow()

    private val _smartstunResponse =
        MutableStateFlow<Resource<List<ArticleListResponse>>>(Resource.Loading())
    val smartstunResponse = _smartstunResponse.asStateFlow()

    private val _taskResponse = MutableStateFlow<Resource<TaskResponse>>(Resource.Loading())
    val taskResponse = _taskResponse.asStateFlow()

    private fun fetchUserDetail() {
        viewModelScope.launch {
            val uid = userRepository.readUid().first()
            if (uid != null) {
                userRepository.fetchUserDetail(uid).collect {
                    _userResponse.value = it
                }
            }
        }
    }

    private fun fetchDonations() {
        viewModelScope.launch {
            donationRepository.fetchDonations().collect {
                _donationResponse.value = it
            }
        }
    }

    private fun fetchSmartstuns() {
        viewModelScope.launch {
            articleRepository.fetchArticles().collect {
                _smartstunResponse.value = it
            }
        }
    }
    private fun getUserRegisterProgressIndex() {
        viewModelScope.launch {
            val progressIndex = userRepository.readRegisterProgressIndex().first()
            currentRegistrationState.value = progressIndex
        }
    }

    private fun fetchTaskDetail() {
        viewModelScope.launch {
            val uid = userRepository.readUid().first()
            val randomTaskId = Dummy.listOfTaskId.random()
            if (uid != null) {
                taskRepository.fetchTaskDetail(uid = uid, taskId = randomTaskId).collect {
                    _taskResponse.value = it
                }
            }
        }
    }

    init {
        fetchUserDetail()
        fetchDonations()
        fetchSmartstuns()
        getUserRegisterProgressIndex()
        fetchTaskDetail()
    }
}