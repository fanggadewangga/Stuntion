package com.killjoy.stuntion.features.data.source.remote.api.response

import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(
    @SerializedName("isError")
    var isError: Boolean,

    @SerializedName("status")
    var status: String = "",

    @SerializedName("message")
    val message: String = "",

    @SerializedName("count")
    val count: Int? = 0,

    @SerializedName("data")
    val data: T?
)
