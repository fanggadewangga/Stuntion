package com.killjoy.stuntion.di

import com.killjoy.stuntion.features.data.repository.article.ArticleRepository
import com.killjoy.stuntion.features.data.repository.article.IArticleRepository
import com.killjoy.stuntion.features.data.repository.donation.DonationRepository
import com.killjoy.stuntion.features.data.repository.donation.IDonationRepository
import com.killjoy.stuntion.features.data.repository.expert.ExpertRepository
import com.killjoy.stuntion.features.data.repository.expert.IExpertRepository
import com.killjoy.stuntion.features.data.repository.note.INoteRepository
import com.killjoy.stuntion.features.data.repository.note.NoteRepository
import com.killjoy.stuntion.features.data.repository.payment.IPaymentRepository
import com.killjoy.stuntion.features.data.repository.payment.PaymentRepository
import com.killjoy.stuntion.features.data.repository.question.IQuestionRepository
import com.killjoy.stuntion.features.data.repository.question.QuestionRepository
import com.killjoy.stuntion.features.data.repository.task.ITaskRepository
import com.killjoy.stuntion.features.data.repository.task.TaskRepository
import com.killjoy.stuntion.features.data.repository.user.IUserRepository
import com.killjoy.stuntion.features.data.repository.user.UserRepository
import com.killjoy.stuntion.features.data.repository.voucher.IVoucherRepository
import com.killjoy.stuntion.features.data.repository.voucher.VoucherRepository
import com.killjoy.stuntion.features.data.source.local.datastore.StuntionDatastore
import com.killjoy.stuntion.features.data.source.remote.api.service.StuntionApi
import com.killjoy.stuntion.features.data.source.remote.firebase.FirebaseDataSource
import com.killjoy.stuntion.features.data.util.Util.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteModule {
    @Provides
    @Singleton
    fun provideStuntionApi(): StuntionApi {
        val client: OkHttpClient =
            OkHttpClient.Builder()
                .addInterceptor {
                    val original = it.request()
                    val requestBuilder = original.newBuilder()
                    val request = requestBuilder.build()
                    it.proceed(request)
                }
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(StuntionApi::class.java)
    }

    @Provides
    fun provideUserRepository(
        stuntionApi: StuntionApi,
        firebaseDataSource: FirebaseDataSource,
        dataStore: StuntionDatastore,
    ): IUserRepository = UserRepository(stuntionApi, firebaseDataSource, dataStore)

    @Provides
    fun provideArticleRepository(
        stuntionApi: StuntionApi,
    ): IArticleRepository = ArticleRepository(stuntionApi)

    @Provides
    fun provideExpertRepository(
        stuntionApi: StuntionApi,
    ): IExpertRepository = ExpertRepository(stuntionApi)

    @Provides
    fun provideQuestionRepository(
        stuntionApi: StuntionApi,
    ): IQuestionRepository = QuestionRepository(stuntionApi)

    @Provides
    fun provideDonationRepository(
        stuntionApi: StuntionApi,
        firebaseDataSource: FirebaseDataSource,
    ): IDonationRepository = DonationRepository(stuntionApi, firebaseDataSource)

    @Provides
    fun provideNoteRepository(
        stuntionApi: StuntionApi,
    ): INoteRepository = NoteRepository(stuntionApi)

    @Provides
    fun provideTaskRepository(
        stuntionApi: StuntionApi,
    ): ITaskRepository = TaskRepository(stuntionApi)

    @Provides
    fun providePaymentRepository(
        stuntionApi: StuntionApi,
    ): IPaymentRepository = PaymentRepository(stuntionApi)

    @Provides
    fun provideVoucherRepository(
        stuntionApi: StuntionApi,
    ): IVoucherRepository = VoucherRepository(stuntionApi)
}