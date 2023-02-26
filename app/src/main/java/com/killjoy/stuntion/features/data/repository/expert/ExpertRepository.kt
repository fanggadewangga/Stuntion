package com.killjoy.stuntion.features.data.repository.expert

import android.util.Log
import com.killjoy.stuntion.features.data.source.remote.api.response.expert.ExpertListResponse
import com.killjoy.stuntion.features.data.source.remote.api.response.expert.ExpertResponse
import com.killjoy.stuntion.features.data.source.remote.api.service.StuntionApi
import com.killjoy.stuntion.features.data.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ExpertRepository @Inject constructor(private val stuntionApi: StuntionApi) :
    IExpertRepository {
    override suspend fun fetchExperts(): Flow<Resource<List<ExpertListResponse>>> = flow {
        emit(Resource.Loading())
        try {
            val response = stuntionApi.fetchExperts()
            if (!response.isError) {
                Log.d("FETCH EXPERTS", response.message)
                emit(Resource.Success(response.data))
            } else
                emit(Resource.Error(response.message))
        } catch (e: Exception) {
            Log.d("FETCH EXPERTS", e.message.toString())
            emit(Resource.Error(e.message.toString()))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun searchExpert(query: String): Flow<Resource<List<ExpertListResponse>>> =
        flow {
            emit(Resource.Loading())
            try {
                val response = stuntionApi.searchExpert(query)
                if (!response.isError) {
                    Log.d("SEARCH EXPERTS", response.message)
                    emit(Resource.Success(response.data))
                } else
                    emit(Resource.Error(response.message))
            } catch (e: Exception) {
                Log.d("SEARCH EXPERTS", e.message.toString())
                emit(Resource.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)

    override suspend fun fetchExpertDetail(expertId: String): Flow<Resource<ExpertResponse?>> =
        flow<Resource<ExpertResponse?>> {
            emit(Resource.Loading())
            try {
                val response = stuntionApi.fetchExpertDetail(expertId)
                if (!response.isError) {
                    Log.d("DETAIL EXPERTS", response.message)
                    emit(Resource.Success(response.data))
                } else
                    emit(Resource.Error(response.message))
            } catch (e: Exception) {
                Log.d("DETAIL EXPERTS", e.message.toString())
                emit(Resource.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)
}