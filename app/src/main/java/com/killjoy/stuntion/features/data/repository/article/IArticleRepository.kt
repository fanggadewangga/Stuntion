package com.killjoy.stuntion.features.data.repository.article

import com.killjoy.stuntion.features.data.source.remote.api.response.article.ArticleListResponse
import com.killjoy.stuntion.features.data.source.remote.api.response.article.ArticleResponse
import com.killjoy.stuntion.features.data.util.Resource
import kotlinx.coroutines.flow.Flow

interface IArticleRepository {
    suspend fun fetchArticles(): Flow<Resource<List<ArticleListResponse>>>
    suspend fun searchArticle(query: String): Flow<Resource<List<ArticleListResponse>>>
    suspend fun fetchArticleDetail(articleId: String): Flow<Resource<ArticleResponse?>>
}