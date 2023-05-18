package com.killjoy.stuntion.features.data.source.remote.api.response.user

import com.google.gson.annotations.SerializedName

data class UserBalanceBody(
    @field:SerializedName("balance")
    val avatarUrl: Double,
)