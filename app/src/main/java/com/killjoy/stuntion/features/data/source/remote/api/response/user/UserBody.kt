package com.killjoy.stuntion.features.data.source.remote.api.response.user

import com.google.gson.annotations.SerializedName

data class UserBody(
    @field:SerializedName("uid")
    val uid: String,

    @field:SerializedName("email")
    val email: String,
)
