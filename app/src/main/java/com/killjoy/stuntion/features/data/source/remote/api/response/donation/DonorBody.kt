package com.killjoy.stuntion.features.data.source.remote.api.response.donation

import com.google.gson.annotations.SerializedName

data class DonorBody(
    @field:SerializedName("uid")
    val uid: String,

    @field:SerializedName("nominal")
    val nominal: Double,

    @field:SerializedName("is_anonymous")
    val isAnonymous: Boolean,
)
