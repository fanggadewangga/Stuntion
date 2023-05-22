package com.killjoy.stuntion.features.data.source.remote.api.response.user

import com.google.gson.annotations.SerializedName

data class UserBalanceResponse(
    @field:SerializedName("balance")
    val balance: Double,
)