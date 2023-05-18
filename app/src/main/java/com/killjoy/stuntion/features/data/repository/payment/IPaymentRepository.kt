package com.killjoy.stuntion.features.data.repository.payment

import com.killjoy.stuntion.features.data.source.remote.api.response.payment.PaymentResponse
import com.killjoy.stuntion.features.data.util.Resource
import kotlinx.coroutines.flow.Flow

interface IPaymentRepository {
    suspend fun fetchPayments(): Flow<Resource<List<PaymentResponse>>>
}