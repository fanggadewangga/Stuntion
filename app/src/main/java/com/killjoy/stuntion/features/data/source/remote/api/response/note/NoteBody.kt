package com.killjoy.stuntion.features.data.source.remote.api.response.note

import com.google.gson.annotations.SerializedName

data class NoteBody(
    @field:SerializedName("child_name")
    val childName: String,

    @field:SerializedName("gender")
    val gender: String,

    @field:SerializedName("birthday")
    val birthday: String,

    @field:SerializedName("age_year")
    val ageYear: Int,

    @field:SerializedName("age_month")
    val ageMonth: Int,

    @field:SerializedName("age_day")
    val ageDay: Int,

    @field:SerializedName("height")
    val height: Double,

    @field:SerializedName("height_desc")
    val heightDescription: String,

    @field:SerializedName("weight")
    val weight: Double,

    @field:SerializedName("weight_desc")
    val weightDescription: String,

    @field:SerializedName("ideal_height")
    val idealHeight: Double,

    @field:SerializedName("ideal_weight")
    val idealWeight: Double
)
