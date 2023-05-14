package com.killjoy.stuntion.features.data.source.remote.api.response.donation

import com.google.gson.annotations.SerializedName

data class DonationBody(
    @field:SerializedName("uid")
    val uid: String,

    @field:SerializedName("phone")
    val phone: String = "",

    @field:SerializedName("address")
    val address: String = "",

    @field:SerializedName("title")
    val title: String = "",

    @field:SerializedName("story")
    val story: String = "",

    @field:SerializedName("image_url")
    var imageUrl: String = "",

    @field:SerializedName("deadline_at")
    val deadlineAt: String = "",

    @field:SerializedName("max_value")
    val maxValue: Int = 0,

    @field:SerializedName("fee")
    val fee: Int = 0,

    @field:SerializedName("lat")
    val lat: Double = 0.0,

    @field:SerializedName("lon")
    val lon: Double = 0.0,
)