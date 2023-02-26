package com.killjoy.stuntion.features.data.source.remote.api.response.expert

import com.google.gson.annotations.SerializedName

data class ExpertListResponse(
    @field:SerializedName("expert_id")
    val expertId: String,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("avatar_url")
    val avatarUrl: String,

    @field:SerializedName("experience_year")
    val experienceYear: Int,

    @field:SerializedName("fee")
    val fee: Int,

    @field:SerializedName("rating")
    val rating: Double,

    @field:SerializedName("categories")
    val categories: List<String>,
)
