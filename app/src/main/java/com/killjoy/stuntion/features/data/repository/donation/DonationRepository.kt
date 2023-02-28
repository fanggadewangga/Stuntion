package com.killjoy.stuntion.features.data.repository.donation

import android.util.Log
import com.killjoy.stuntion.features.data.source.remote.api.response.donation.DonationBody
import com.killjoy.stuntion.features.data.source.remote.api.response.donation.DonationListResponse
import com.killjoy.stuntion.features.data.source.remote.api.response.donation.DonationResponse
import com.killjoy.stuntion.features.data.source.remote.api.service.StuntionApi
import com.killjoy.stuntion.features.data.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DonationRepository @Inject constructor(private val stuntionApi: StuntionApi) :
    IDonationRepository {
    override suspend fun postNewDonation(uid: String, body: DonationBody): Flow<Resource<String>> =
        flow {
            emit(Resource.Loading())
            try {
                val response = stuntionApi.postNewDonation(body)
                if (!response.isError) {
                    Log.d("POST DONATION", response.message)
                    emit(Resource.Success(response.message))
                } else
                    emit(Resource.Error(response.message))
            } catch (e: Exception) {
                Log.d("POST DONATION", e.message.toString())
                emit(Resource.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)

    override suspend fun fetchDonations(): Flow<Resource<List<DonationListResponse>>> = flow {
        emit(Resource.Loading())
        try {
            val response = stuntionApi.fetchDonations()
            if (!response.isError) {
                Log.d("FETCH DONATION", response.message)
                emit(Resource.Success(response.data))
            } else
                emit(Resource.Error(response.message))
        } catch (e: Exception) {
            Log.d("FETCH DONATION", e.message.toString())
            emit(Resource.Error(e.message.toString()))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun searchDonation(query: String): Flow<Resource<List<DonationListResponse>>> =
        flow {
            emit(Resource.Loading())
            try {
                val response = stuntionApi.searchDonation(query)
                if (!response.isError) {
                    Log.d("SEARCH DONATION", response.message)
                    emit(Resource.Success(response.data))
                } else
                    emit(Resource.Error(response.message))
            } catch (e: Exception) {
                Log.d("SEARCH DONATION", e.message.toString())
                emit(Resource.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)

    override suspend fun fetchDonationDetail(donationId: String): Flow<Resource<DonationResponse?>> =
        flow<Resource<DonationResponse?>> {
            emit(Resource.Loading())
            try {
                val response = stuntionApi.fetchDonationDetail(donationId)
                if (!response.isError) {
                    Log.d("DETAIL DONATION", response.message)
                    emit(Resource.Success(response.data))
                } else
                    emit(Resource.Error(response.message))
            } catch (e: Exception) {
                Log.d("DETAIL DONATION", e.message.toString())
                emit(Resource.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)

    override suspend fun updateDonationCurrentValue(donationId: String): Flow<Resource<String>> =
        flow {
            emit(Resource.Loading())
            try {
                val response = stuntionApi.updateDonationCurrentValue(donationId)
                if (!response.isError) {
                    Log.d("UPDATE DONATION", response.message)
                    emit(Resource.Success(response.data))
                } else
                    emit(Resource.Error(response.message))
            } catch (e: Exception) {
                Log.d("UPDATE DONATION", e.message.toString())
                emit(Resource.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)
}