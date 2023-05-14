package com.killjoy.stuntion.features.data.repository.donation

import android.net.Uri
import com.killjoy.stuntion.features.data.source.remote.api.response.donation.DonationBody
import com.killjoy.stuntion.features.data.source.remote.api.response.donation.DonationListResponse
import com.killjoy.stuntion.features.data.source.remote.api.response.donation.DonationResponse
import com.killjoy.stuntion.features.data.util.Resource
import kotlinx.coroutines.flow.Flow

interface IDonationRepository {
    suspend fun postNewDonation(body: DonationBody, imageUri: Uri): Flow<Resource<String>>
    suspend fun fetchDonations(): Flow<Resource<List<DonationListResponse>>>
    suspend fun searchDonation(query: String): Flow<Resource<List<DonationListResponse>>>
    suspend fun fetchDonationDetail(donationId: String): Flow<Resource<DonationResponse?>>
    suspend fun updateDonationCurrentValue(donationId: String): Flow<Resource<String>>
}