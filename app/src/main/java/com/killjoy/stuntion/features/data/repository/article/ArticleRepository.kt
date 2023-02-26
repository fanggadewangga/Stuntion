package com.killjoy.stuntion.features.data.repository.article

import android.util.Log
import com.killjoy.stuntion.features.data.source.remote.api.response.article.ArticleListResponse
import com.killjoy.stuntion.features.data.source.remote.api.response.article.ArticleResponse
import com.killjoy.stuntion.features.data.source.remote.api.service.StuntionApi
import com.killjoy.stuntion.features.data.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ArticleRepository @Inject constructor(private val stuntionApi: StuntionApi) :
    IArticleRepository {

    override suspend fun fetchArticles(): Flow<Resource<List<ArticleListResponse>>> =
        flow {
            emit(Resource.Loading())
            try {
                val response = stuntionApi.fetchArticles()
                if (!response.isError) {
                    Log.d("FETCH ARTICLE", "SUCCESS")
                    emit(Resource.Success(response.data))
                } else
                    emit(Resource.Empty())
            } catch (e: Exception) {
                Log.d("FETCH ARTICLE", e.message.toString())
                emit(Resource.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)


    override suspend fun searchArticle(query: String): Flow<Resource<List<ArticleListResponse>>> =
        flow {
            emit(Resource.Loading())
            try {
                val response = stuntionApi.searchArticle(query)
                if (!response.isError) {
                    Log.d("SEARCH ARTICLE", "SUCCESS")
                    emit(Resource.Success(response.data))
                } else
                    emit(Resource.Empty())
            } catch (e: Exception) {
                Log.d("SEARCH ARTICLE", e.message.toString())
                emit(Resource.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)

    override suspend fun fetchArticleDetail(articleId: String): Flow<Resource<ArticleResponse?>> =
        flow<Resource<ArticleResponse?>> {
            emit(Resource.Loading())
            try {
                val response = stuntionApi.fetchArticleDetail(articleId)
                if (!response.isError) {
                    Log.d("DETAIL ARTICLE", "SUCCESS")
                    emit(Resource.Success(response.data))
                } else
                    emit(Resource.Empty())
            } catch (e: Exception) {
                Log.d("DETAIL ARTICLE", e.message.toString())
                emit(Resource.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)
}