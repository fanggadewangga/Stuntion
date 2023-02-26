package com.killjoy.stuntion.features.data.source.remote.api.response.task

import com.google.gson.annotations.SerializedName

data class TaskResponse(
    @field:SerializedName("task_id")
    val taskId: String,

    @field:SerializedName("task")
    val task: String,

    @field:SerializedName("material")
    val material: String,

    @field:SerializedName("lower_age_limit")
    val lowerAgeLimit: Double,

    @field:SerializedName("upper_age_limit")
    val upperAgeLimit: Double,

    @field:SerializedName("instructions")
    val instructions: List<String>,
)
