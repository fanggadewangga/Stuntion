package com.killjoy.stuntion.features.data.repository.user

import com.killjoy.stuntion.features.data.source.remote.api.response.user.UserBody
import com.killjoy.stuntion.features.data.source.remote.api.response.user.UserResponse
import com.killjoy.stuntion.features.data.source.remote.api.service.StuntionApi
import com.killjoy.stuntion.features.data.source.remote.firebase.FirebaseDataSource
import com.killjoy.stuntion.features.data.source.remote.firebase.FirebaseResponse
import com.killjoy.stuntion.features.data.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val stuntionApi: StuntionApi,
    private val firebaseDataSource: FirebaseDataSource,
) : IUserRepository {
    override suspend fun signUpUser(
        email: String,
        password: String,
    ): Flow<Resource<UserResponse?>> =
        channelFlow<Resource<UserResponse?>> {
            send(Resource.Loading())
            firebaseDataSource.createUserWithEmailAndPassword(email, password).collect { response ->
                when (response) {
                    is FirebaseResponse.Success -> {
                        val uid = response.data
                        val body = UserBody(uid, email)
                        try {
                            stuntionApi.postNewUser(body).data
                            val user = stuntionApi.fetchUserDetail(uid).data
                            send(Resource.Success(user))
                        } catch (e: Exception) {
                            send((Resource.Error(e.message.toString())))
                        }
                    }
                    is FirebaseResponse.Error -> {
                        send(Resource.Error(response.errorMessage))
                    }
                    is FirebaseResponse.Empty -> send(Resource.Empty())
                }
            }
        }.flowOn(Dispatchers.IO)

    override suspend fun signInUser(
        email: String,
        password: String,
    ): Flow<Resource<UserResponse?>> =
        channelFlow<Resource<UserResponse?>> {
            send(Resource.Loading())
            firebaseDataSource.signInWithEmailAndPassword(email, password).collect { response ->
                when (response) {
                    is FirebaseResponse.Success -> {
                        try {
                            val userResponse = stuntionApi.fetchUserDetail(uid = response.data).data
                            send(Resource.Success(userResponse))
                        } catch (e: Exception) {
                            send(Resource.Error(e.message.toString()))
                        }
                    }
                    is FirebaseResponse.Error -> {
                        send(Resource.Error(response.errorMessage))
                    }
                    is FirebaseResponse.Empty -> send(Resource.Empty())
                }
            }
        }.flowOn(Dispatchers.IO)
}