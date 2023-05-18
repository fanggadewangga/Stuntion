package com.killjoy.stuntion.features.data.repository.payment

import android.util.Log
import com.killjoy.stuntion.features.data.source.remote.api.response.payment.PaymentResponse
import com.killjoy.stuntion.features.data.source.remote.api.service.StuntionApi
import com.killjoy.stuntion.features.data.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class PaymentRepository @Inject constructor(private val stuntionApi: StuntionApi) :
    IPaymentRepository {
    override suspend fun fetchPayments(): Flow<Resource<List<PaymentResponse>>> = flow {
        emit(Resource.Loading())
        try {
            val response = stuntionApi.fetchPayments()
            if (!response.isError) {
                Log.d("FETCH PAYMENTS", response.message)
                emit(Resource.Success(response.data))
            } else
                emit(Resource.Error(response.message))
        } catch (e: Exception) {
            emit(Resource.Error(e.message.toString()))
        }
    }.flowOn(Dispatchers.IO)
}