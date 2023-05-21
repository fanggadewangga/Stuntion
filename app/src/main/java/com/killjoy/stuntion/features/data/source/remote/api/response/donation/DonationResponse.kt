package com.killjoy.stuntion.features.data.source.remote.api.response.donation

import com.google.gson.annotations.SerializedName

data class DonationResponse(

    @field:SerializedName("donation_id")
    val donationId: String,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("phone")
    val phone: String,

    @field:SerializedName("address")
    val address: String,

    @field:SerializedName("user_avatar_url")
    val userAvatarUrl : String,

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("story")
    val story: String,

    @field:SerializedName("image_url")
    val imageUrl: String,

    @field:SerializedName("current_value")
    val currentValue: Int,

    @field:SerializedName("max_value")
    val maxValue: Int,

    @field:SerializedName("nominal")
    val currentNominal: Double,

    @field:SerializedName("uploaded_at")
    val uploadedAt: String,

    @field:SerializedName("deadline_at")
    val deadlineAt: String,

    @field:SerializedName("day_remaining")
    val dayRemaining: Int,

    @field:SerializedName("fee")
    val fee: Int,

    @field:SerializedName("done")
    val done: Boolean,

    @field:SerializedName("lat")
    val lat: Double,

    @field:SerializedName("lon")
    val lon: Double
)
