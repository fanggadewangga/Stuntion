package com.killjoy.stuntion.features.data.source.remote.api.response.voucher

import com.google.gson.annotations.SerializedName

data class VoucherResponse(
    @field:SerializedName("voucher_id")
    val voucherId: String,

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("voucher_type")
    val type: Int,

    @field:SerializedName("discount")
    val discount: Int?,

    @field:SerializedName("free_balance")
    val freeBalance: Double?,

    @field:SerializedName("valid_level")
    val validLevel: Int,

    @field:SerializedName("is_valid")
    val isValid: Boolean,

    @field:SerializedName("image_url")
    val imageUrl: String,
)
