package com.killjoy.stuntion.features.data.source.remote.api.service

import com.killjoy.stuntion.features.data.source.remote.api.response.BaseResponse
import com.killjoy.stuntion.features.data.source.remote.api.response.user.UserBody
import com.killjoy.stuntion.features.data.source.remote.api.response.user.UserResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface StuntionApi {

    @POST("/user")
    suspend fun postNewUser(
        @Body body: UserBody
    ): BaseResponse<String>

    @GET("/user/{uid}")
    suspend fun fetchUserDetail(
        @Path("uid") uid: String
    ): BaseResponse<UserResponse>
}