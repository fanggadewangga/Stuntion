package com.killjoy.stuntion.features.data.source.remote.api.response.article

import com.google.gson.annotations.SerializedName

data class ArticleListResponse(
    @field:SerializedName("article_id")
    val articleId: String,

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("description")
    val description: String,

    @field:SerializedName("image_url")
    val thumbnailUrl: String,

    @field:SerializedName("categories")
    val categories: List<String>,

    @field:SerializedName("timestamp")
    val timestamp: String,
)
