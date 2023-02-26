package com.killjoy.stuntion.features.data.source.remote.api.response.article

import com.google.gson.annotations.SerializedName

data class ArticleResponse(
    @field:SerializedName("article_id")
    val articleId: String,

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("description")
    val description: String,

    @field:SerializedName("video_url")
    val videoUrl: String,

    @field:SerializedName("reviewer")
    val reviewer: String,

    @field:SerializedName("timestamp")
    val timestamp: String,
)