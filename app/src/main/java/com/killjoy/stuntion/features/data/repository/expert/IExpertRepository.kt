package com.killjoy.stuntion.features.data.repository.expert

import com.killjoy.stuntion.features.data.source.remote.api.response.expert.ExpertListResponse
import com.killjoy.stuntion.features.data.source.remote.api.response.expert.ExpertResponse
import com.killjoy.stuntion.features.data.util.Resource
import kotlinx.coroutines.flow.Flow

interface IExpertRepository {
    suspend fun fetchExperts(): Flow<Resource<List<ExpertListResponse>>>
    suspend fun searchExpert(query: String): Flow<Resource<List<ExpertListResponse>>>
    suspend fun fetchExpertDetail(expertId: String): Flow<Resource<ExpertResponse?>>
}