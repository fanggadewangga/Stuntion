package com.killjoy.stuntion.features.data.repository.voucher

import com.killjoy.stuntion.features.data.source.remote.api.response.voucher.VoucherResponse
import com.killjoy.stuntion.features.data.util.Resource
import kotlinx.coroutines.flow.Flow

interface IVoucherRepository {
    suspend fun fetchVouchersByUser(uid: String): Flow<Resource<List<VoucherResponse>>>
}