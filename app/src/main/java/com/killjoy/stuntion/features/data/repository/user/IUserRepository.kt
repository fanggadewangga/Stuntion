package com.killjoy.stuntion.features.data.repository.user

import com.killjoy.stuntion.features.data.source.remote.api.response.user.UserResponse
import com.killjoy.stuntion.features.data.util.Resource
import kotlinx.coroutines.flow.Flow

interface IUserRepository {
    suspend fun signUpUser(email: String, password: String): Flow<Resource<UserResponse?>>
    suspend fun signInUser(email: String, password: String): Flow<Resource<UserResponse?>>
}