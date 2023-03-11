package com.killjoy.stuntion.features.data.source.remote.api.response.user

import com.google.gson.annotations.SerializedName

data class UserGeneralInfoBody(
    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("birth_date")
    val birthDate: String,

    @field:SerializedName("gender")
    val gender: String,

    @field:SerializedName("avatar_url")
    val avatarUrl: String = "https://firebasestorage.googleapis.com/v0/b/stuntion-a32cc.appspot.com/o/avatar%2Favatar_1.png?alt=media&token=931a191d-6277-4480-b860-fdf8e0e41dfe",
)
