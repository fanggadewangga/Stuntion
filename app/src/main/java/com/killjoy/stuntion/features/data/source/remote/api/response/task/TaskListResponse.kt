package com.killjoy.stuntion.features.data.source.remote.api.response.task

import com.google.gson.annotations.SerializedName

data class TaskListResponse(

    @field:SerializedName("task_id")
    val taskId: String,

    @field:SerializedName("task")
    val task: String,

    @field:SerializedName("done")
    val done: Boolean
)
