package com.killjoy.stuntion.features.data.source.remote.api.service

import com.killjoy.stuntion.features.data.source.remote.api.response.BaseListResponse
import com.killjoy.stuntion.features.data.source.remote.api.response.BaseResponse
import com.killjoy.stuntion.features.data.source.remote.api.response.article.ArticleListResponse
import com.killjoy.stuntion.features.data.source.remote.api.response.article.ArticleResponse
import com.killjoy.stuntion.features.data.source.remote.api.response.donation.DonationBody
import com.killjoy.stuntion.features.data.source.remote.api.response.donation.DonationListResponse
import com.killjoy.stuntion.features.data.source.remote.api.response.donation.DonationResponse
import com.killjoy.stuntion.features.data.source.remote.api.response.expert.ExpertListResponse
import com.killjoy.stuntion.features.data.source.remote.api.response.expert.ExpertResponse
import com.killjoy.stuntion.features.data.source.remote.api.response.note.NoteBody
import com.killjoy.stuntion.features.data.source.remote.api.response.note.NoteResponse
import com.killjoy.stuntion.features.data.source.remote.api.response.payment.PaymentResponse
import com.killjoy.stuntion.features.data.source.remote.api.response.question.QuestionBody
import com.killjoy.stuntion.features.data.source.remote.api.response.question.QuestionListResponse
import com.killjoy.stuntion.features.data.source.remote.api.response.question.QuestionResponse
import com.killjoy.stuntion.features.data.source.remote.api.response.task.TaskListResponse
import com.killjoy.stuntion.features.data.source.remote.api.response.task.TaskResponse
import com.killjoy.stuntion.features.data.source.remote.api.response.task.UserTaskBody
import com.killjoy.stuntion.features.data.source.remote.api.response.user.UserAvatarBody
import com.killjoy.stuntion.features.data.source.remote.api.response.user.UserBody
import com.killjoy.stuntion.features.data.source.remote.api.response.user.UserGeneralInfoBody
import com.killjoy.stuntion.features.data.source.remote.api.response.user.UserResponse
import com.killjoy.stuntion.features.data.source.remote.api.response.voucher.VoucherResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface StuntionApi {

    // User
    @POST("/user")
    suspend fun postNewUser(
        @Body body: UserBody,
    ): BaseResponse<String>

    @GET("/user/{uid}")
    suspend fun fetchUserDetail(
        @Path("uid") uid: String,
    ): BaseResponse<UserResponse>

    @PUT("/user/{uid}/general")
    suspend fun updateUserGeneralInformation(
        @Path("uid") uid: String,
        @Body body: UserGeneralInfoBody,
    ): BaseResponse<String>

    @PUT("/user/{uid}/avatar")
    suspend fun updateUserAvatar(
        @Path("uid") uid: String,
        @Body body: UserAvatarBody,
    ): BaseResponse<String>

    @PUT("/user/{uid}/level")
    suspend fun updateUserLevel(
        @Path("uid") uid: String,
    ): BaseResponse<String>

    // Article
    @GET("/article")
    suspend fun fetchArticles(): BaseResponse<List<ArticleListResponse>>

    @GET("/article")
    suspend fun searchArticle(
        @Query("q") query: String,
    ): BaseListResponse<List<ArticleListResponse>>

    @GET("/article/{article_id}")
    suspend fun fetchArticleDetail(
        @Path("article_id") articleId: String,
    ): BaseResponse<ArticleResponse>

    // Expert
    @GET("/expert")
    suspend fun fetchExperts(): BaseResponse<List<ExpertListResponse>>

    @GET("/expert")
    suspend fun searchExpert(
        @Query("q") query: String,
    ): BaseListResponse<List<ExpertListResponse>>

    @GET("/expert/{expert_id}")
    suspend fun fetchExpertDetail(
        @Path("expert_id") expertId: String,
    ): BaseResponse<ExpertResponse>

    // Question
    @POST("/question")
    suspend fun postNewQuestion(@Body body: QuestionBody): BaseResponse<String>

    @GET("/question")
    suspend fun fetchQuestions(): BaseResponse<List<QuestionListResponse>>

    @GET("/question")
    suspend fun searchQuestion(@Query("q") query: String): BaseListResponse<List<QuestionListResponse>>

    @GET("/question/{question_id}")
    suspend fun fetchQuestionDetail(@Path("question_id") questionId: String): BaseResponse<QuestionResponse>

    // Donation
    @POST("/donation")
    suspend fun postNewDonation(@Body body: DonationBody): BaseResponse<String>

    @GET("/donation")
    suspend fun fetchDonations(): BaseResponse<List<DonationListResponse>>

    @GET("/donation")
    suspend fun searchDonation(@Query("q") query: String): BaseResponse<List<DonationListResponse>>

    @GET("/donation/{donation_id}")
    suspend fun fetchDonationDetail(@Path("donation_id") donationId: String): BaseResponse<DonationResponse>

    @PUT("/donation/{donation_id}")
    suspend fun updateDonationCurrentValue(@Path("donation_id") donationId: String): BaseResponse<String>

    // Note
    @POST("/user/{uid}/note")
    suspend fun postNewNote(@Path("uid") uid: String, @Body body: NoteBody): BaseResponse<String>

    @GET("/user/{uid}/note")
    suspend fun fetchNoteByUser(@Path("uid") uid: String): BaseResponse<List<NoteResponse>>

    @GET("/user/{uid}/note/{note_id}")
    suspend fun fetchNoteDetail(
        @Path("uid") uid: String,
        @Path("note_id") noteId: String,
    ): BaseResponse<NoteResponse?>

    // Task
    @POST("/task/{uid}")
    suspend fun postNewUserTask(
        @Path("uid") uid: String,
        @Body body: UserTaskBody,
    ): BaseResponse<String>

    @GET("/task/{uid}")
    suspend fun fetchTaskByUser(@Path("uid") uid: String): BaseResponse<List<TaskListResponse>>

    @GET("/task/{uid}/{task_id}")
    suspend fun fetchTaskDetail(
        @Path("uid") uid: String,
        @Path("task_id") taskId: String,
    ): BaseResponse<TaskResponse>

    // Payment
    @GET("/payment")
    suspend fun fetchPayments(): BaseResponse<List<PaymentResponse>>

    // Voucher
    @GET("/user/{uid}/voucher")
    suspend fun fetchVouchersByUser(@Path("uid") uid: String): BaseResponse<List<VoucherResponse>>
}