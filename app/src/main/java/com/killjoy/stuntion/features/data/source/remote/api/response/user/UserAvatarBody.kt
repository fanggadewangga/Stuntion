package com.killjoy.stuntion.features.data.source.remote.api.response.user

import com.google.gson.annotations.SerializedName

data class UserAvatarBody(
    @field:SerializedName("avatar_url")
    val avatarUrl: String,
)
