package com.killjoy.stuntion.features.data.source.remote.api.response.article

import com.google.gson.annotations.SerializedName

data class ArticleListResponse(
    @field:SerializedName("article_id")
    val articleId: String,

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("description")
    val description: String,

    @field:SerializedName("video_url")
    val videoUrl: String,

    @field:SerializedName("categories")
    val categories: List<String>,
)
