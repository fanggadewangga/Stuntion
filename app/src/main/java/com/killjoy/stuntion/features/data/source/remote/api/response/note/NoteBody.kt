package com.killjoy.stuntion.features.data.source.remote.api.response.note

import com.google.gson.annotations.SerializedName

data class NoteBody(
    @field:SerializedName("child_name")
    val childName: String,

    @field:SerializedName("gender")
    val gender: String,

    @field:SerializedName("age_year")
    val ageYear: Int,

    @field:SerializedName("age_month")
    val ageMonth: Int,

    @field:SerializedName("age_day")
    val ageDay: Int,

    @field:SerializedName("height")
    val height: Double,

    @field:SerializedName("weight")
    val weight: Double,
)
