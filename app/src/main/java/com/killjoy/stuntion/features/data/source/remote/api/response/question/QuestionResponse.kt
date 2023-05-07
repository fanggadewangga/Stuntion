package com.killjoy.stuntion.features.data.source.remote.api.response.question

import com.google.gson.annotations.SerializedName

data class QuestionResponse(
    @field:SerializedName("question_id")
    val questionId: String,

    @field:SerializedName("question")
    val question: String,

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("answer")
    val answer: String?,

    @field:SerializedName("timestamp")
    val timestamp: String,

    @field:SerializedName("user_name")
    val userName: String,

    @field:SerializedName("expert_id")
    val expertId: String,

    @field:SerializedName("user_avatar_url")
    val userAvatarUrl: String,

    @field:SerializedName("expert_name")
    val expertName: String?,

    @field:SerializedName("expert_avatar_url")
    val expertAvatarUrl: String?,

    @field:SerializedName("expert_experience")
    val expertExperience: Int?,

    @field:SerializedName("expert_rating")
    val expertRating: Double?,

    @field:SerializedName("expert_categories")
    val expertCategories: List<String>?,

    )
