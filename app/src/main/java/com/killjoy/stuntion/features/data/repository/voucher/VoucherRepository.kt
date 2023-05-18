package com.killjoy.stuntion.features.data.repository.voucher

import android.util.Log
import com.killjoy.stuntion.features.data.source.remote.api.response.voucher.VoucherResponse
import com.killjoy.stuntion.features.data.source.remote.api.service.StuntionApi
import com.killjoy.stuntion.features.data.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class VoucherRepository @Inject constructor(private val stuntionApi: StuntionApi) :
    IVoucherRepository {
    override suspend fun fetchVouchersByUser(uid: String): Flow<Resource<List<VoucherResponse>>> =
        flow {
            emit(Resource.Loading())
            try {
                val response = stuntionApi.fetchVouchersByUser(uid)
                if (!response.isError) {
                    Log.d("FETCH VOUCHER", response.message)
                    emit(Resource.Success(response.data))
                } else
                    emit(Resource.Error(response.message))
            } catch (e: Exception) {
                emit(Resource.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)
}