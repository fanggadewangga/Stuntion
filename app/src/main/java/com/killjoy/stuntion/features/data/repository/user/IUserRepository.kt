package com.killjoy.stuntion.features.data.repository.user

import com.killjoy.stuntion.features.data.source.remote.api.response.donation.DonorBody
import com.killjoy.stuntion.features.data.source.remote.api.response.user.UserBalanceResponse
import com.killjoy.stuntion.features.data.source.remote.api.response.user.UserGeneralInfoBody
import com.killjoy.stuntion.features.data.source.remote.api.response.user.UserResponse
import com.killjoy.stuntion.features.data.util.Resource
import kotlinx.coroutines.flow.Flow

interface IUserRepository {
    suspend fun signUpUser(email: String, password: String): Flow<Resource<UserResponse?>>
    suspend fun signInUser(email: String, password: String): Flow<Resource<UserResponse?>>
    suspend fun giveNewDonation(body: DonorBody, donationId: String): Flow<Resource<String?>>
    suspend fun fetchUserDetail(uid: String): Flow<Resource<UserResponse?>>
    suspend fun fetchUserBalance(uid: String): Flow<Resource<UserBalanceResponse?>>
    suspend fun updateUserGeneralInformation(uid: String, body: UserGeneralInfoBody): Flow<Resource<String?>>
    suspend fun updateUserAvatar(uid: String, avatarUrl: String): Flow<Resource<String?>>
    suspend fun updateUserLevel(uid: String): Flow<Resource<String?>>
    suspend fun updateUserWalletBalance(uid: String, balance: Double): Flow<Resource<String?>>
    suspend fun saveUid(uid: String)
    suspend fun saveHaveRunAppBefore(isPassedOnboard: Boolean)
    suspend fun saveHaveUpdateGeneralInfo(isHaveUpdateGeneralInfo: Boolean)
    suspend fun saveHaveCreatedAccountSuccessfully(isCreatedAccount: Boolean)
    suspend fun saveRegisterProgressIndex(progressIndex: Int)
    suspend fun readUid(): Flow<String?>
    suspend fun readHaveRunAppBefore(): Flow<Boolean>
    suspend fun readHaveUpdateGeneralInfo(): Flow<Boolean>
    suspend fun readHaveCreatedAccount(): Flow<Boolean>
    suspend fun readRegisterProgressIndex(): Flow<Int>
    suspend fun deleteUid()
    suspend fun logout(): Flow<Resource<String>>
}