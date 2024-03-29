package com.killjoy.stuntion.features.data.repository.user

import android.util.Log
import com.killjoy.stuntion.features.data.repository.donation.DonationRepository
import com.killjoy.stuntion.features.data.source.local.datastore.StuntionDatastore
import com.killjoy.stuntion.features.data.source.remote.api.response.donation.DonorBody
import com.killjoy.stuntion.features.data.source.remote.api.response.user.UserAvatarBody
import com.killjoy.stuntion.features.data.source.remote.api.response.user.UserBalanceBody
import com.killjoy.stuntion.features.data.source.remote.api.response.user.UserBalanceResponse
import com.killjoy.stuntion.features.data.source.remote.api.response.user.UserBody
import com.killjoy.stuntion.features.data.source.remote.api.response.user.UserGeneralInfoBody
import com.killjoy.stuntion.features.data.source.remote.api.response.user.UserResponse
import com.killjoy.stuntion.features.data.source.remote.api.service.StuntionApi
import com.killjoy.stuntion.features.data.source.remote.firebase.FirebaseDataSource
import com.killjoy.stuntion.features.data.source.remote.firebase.FirebaseResponse
import com.killjoy.stuntion.features.data.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val donationRepository: DonationRepository,
    private val stuntionApi: StuntionApi,
    private val firebaseDataSource: FirebaseDataSource,
    private val datastore: StuntionDatastore,
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
                            datastore.savePrefUid(userResponse!!.uid)
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

    override suspend fun giveNewDonation(
        body: DonorBody,
        donationId: String,
    ): Flow<Resource<String?>> = channelFlow<Resource<String?>> {
        donationRepository.postNewDonor(body = body, donationId = donationId)
            .collect { donationResponse ->
                when (donationResponse) {
                    is Resource.Loading -> send(Resource.Loading())
                    is Resource.Success -> {
                        try {
                            val balanceBody = UserBalanceBody(-body.nominal)
                            val response =
                                stuntionApi.updateUserWalletBalance(body.uid, balanceBody)
                            if (!response.isError)
                                send(Resource.Success(response.message))
                        } catch (e: Exception) {
                            send(Resource.Error(e.message.toString()))
                        }
                    }

                    is Resource.Error -> send(Resource.Error(donationResponse.message))
                    is Resource.Empty -> send(Resource.Empty())
                }
            }
    }.flowOn(Dispatchers.IO)

    override suspend fun fetchUserDetail(uid: String): Flow<Resource<UserResponse?>> =
        flow<Resource<UserResponse?>> {
            emit(Resource.Loading())
            try {
                val user = stuntionApi.fetchUserDetail(uid).data
                if (user != null) {
                    emit(Resource.Success(user))
                    Log.d("FETCH USER : ", user.level.toString())
                } else
                    emit(Resource.Empty())
            } catch (e: Exception) {
                emit(Resource.Error(e.message.toString()))
                Log.d("FETCH USER : ", e.message.toString())
            }
        }.flowOn(Dispatchers.IO)

    override suspend fun fetchUserBalance(uid: String): Flow<Resource<UserBalanceResponse?>> =
        flow<Resource<UserBalanceResponse?>> {
            emit(Resource.Loading())
            try {
                val response = stuntionApi.fetchUserBalance(uid).data
                if (response != null) {
                    emit(Resource.Success(response))
                    Log.d("FETCH BALANCE : ", response.balance.toString())
                } else
                    emit(Resource.Empty())
            } catch (e: Exception) {
                emit(Resource.Error(e.message.toString()))
                Log.d("FETCH BALANCE : ", e.message.toString())
            }
        }.flowOn(Dispatchers.IO)

    override suspend fun updateUserGeneralInformation(
        uid: String,
        body: UserGeneralInfoBody,
    ): Flow<Resource<String?>> = flow<Resource<String?>> {
        emit(Resource.Loading())
        try {
            val response = stuntionApi.updateUserGeneralInformation(uid, body)
            if (!response.isError) {
                emit(Resource.Success(response.message))
                Log.d("UPDATE USER GENERAL: ", "SUCCESS")
            } else
                emit(Resource.Empty())
        } catch (e: Exception) {
            emit(Resource.Error(e.message))
            Log.d("UPDATE USER GENERAL: ", e.message.toString())
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun updateUserAvatar(
        uid: String,
        avatarUrl: String,
    ): Flow<Resource<String?>> = flow<Resource<String?>> {
        emit(Resource.Loading())
        try {
            val body = UserAvatarBody(avatarUrl)
            val response = stuntionApi.updateUserAvatar(uid, body)
            if (!response.isError) {
                emit(Resource.Success(response.message))
                Log.d("UPDATE USER LEVEL: ", "SUCCESS")
            } else
                emit(Resource.Empty())
        } catch (e: Exception) {
            emit(Resource.Error(e.message))
            Log.d("UPDATE USER LEVEL: ", e.message.toString())
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun updateUserLevel(uid: String): Flow<Resource<String?>> =
        flow<Resource<String?>> {
            emit(Resource.Loading())
            try {
                val response = stuntionApi.updateUserLevel(uid)
                if (!response.isError) {
                    emit(Resource.Success(response.message))
                    Log.d("UPDATE USER AVATAR: ", "SUCCESS")
                } else
                    emit(Resource.Empty())
            } catch (e: Exception) {
                emit(Resource.Error(e.message))
                Log.d("UPDATE USER AVATAR: ", e.message.toString())
            }
        }.flowOn(Dispatchers.IO)

    override suspend fun updateUserWalletBalance(
        uid: String,
        balance: Double,
    ): Flow<Resource<String?>> = flow {
        emit(Resource.Loading())
        try {
            val body = UserBalanceBody(balance)
            val response = stuntionApi.updateUserWalletBalance(uid, body)
            if (!response.isError) {
                emit(Resource.Success(response.message))
                Log.d("UPDATE USER BALANCE: ", "SUCCESS")
            } else
                emit(Resource.Empty())
        } catch (e: Exception) {
            emit(Resource.Error(e.message))
            Log.d("UPDATE USER BALANCE: ", e.message.toString())
        }
    }

    override suspend fun logout(): Flow<Resource<String>> =
        channelFlow {
            send(Resource.Loading())
            firebaseDataSource.logout().collect { response ->
                when (response) {
                    is FirebaseResponse.Error -> send(Resource.Error(response.errorMessage))
                    is FirebaseResponse.Empty -> send(Resource.Empty())
                    is FirebaseResponse.Success -> {
                        try {
                            datastore.deleteUid()
                            send(Resource.Success("Logout Success"))
                        } catch (e: Exception) {
                            send(Resource.Error(e.message.toString()))
                        }
                    }
                }
            }
        }

    override suspend fun saveUid(uid: String) = datastore.savePrefUid(uid)
    override suspend fun saveHaveRunAppBefore(isPassedOnboard: Boolean) =
        datastore.savePrefHaveRunAppBefore(isPassedOnboard)

    override suspend fun saveRegisterProgressIndex(progressIndex: Int) {
        datastore.savePrefRegistrationProgress(progressIndex)
    }

    override suspend fun readUid(): Flow<String?> = datastore.readPrevUid()
    override suspend fun readHaveRunAppBefore(): Flow<Boolean> =
        datastore.readPrefHaveRunAppBefore()

    override suspend fun readRegisterProgressIndex(): Flow<Int> =
        datastore.readPrefRegistrationProgress()


    override suspend fun deleteUid() {
        datastore.deleteUid()
    }
}