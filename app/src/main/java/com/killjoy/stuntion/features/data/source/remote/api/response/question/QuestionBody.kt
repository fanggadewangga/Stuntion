package com.killjoy.stuntion.features.data.source.remote.api.response.question

import com.google.gson.annotations.SerializedName

data class QuestionBody(

    @field:SerializedName("uid")
    val uid: String,

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("question")
    val question: String,

    @field:SerializedName("categories")
    val categories: List<String>,

    @field:SerializedName("isAnonymous")
    val isAnonymous: Boolean,

    )