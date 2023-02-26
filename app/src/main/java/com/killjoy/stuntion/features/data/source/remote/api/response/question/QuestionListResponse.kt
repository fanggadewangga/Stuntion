package com.killjoy.stuntion.features.data.source.remote.api.response.question

import com.google.gson.annotations.SerializedName

data class QuestionListResponse(
    @field:SerializedName("question_id")
    val questionId: String,

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("question")
    val question: String,

    @field:SerializedName("timestamp")
    val timestamp: String,

    @field:SerializedName("categories")
    val categories: List<String>,

    @field:SerializedName("user_name")
    val userName: String,

    @field:SerializedName("user_avatar_url")
    val userAvatarUrl: String,

    @field:SerializedName("expert_name")
    val expertName: String?,

    @field:SerializedName("expert_avatar_url")
    val expertAvatarUrl: String?,
)
