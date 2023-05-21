package com.killjoy.stuntion.features.data.source.remote.api.response.donation

import com.google.gson.annotations.SerializedName

data class DonorResponse(
    @field:SerializedName("name")
    val donorName: String = "",

    @field:SerializedName("nominal")
    val nominal: Double = 0.0,

    @field:SerializedName("timestamp")
    val timestamp: String = "",

    @field:SerializedName("avatar_url")
    val avatarUrl: String? = "",

    @field:SerializedName("period")
    val dayPeriod: Int = 0,
)