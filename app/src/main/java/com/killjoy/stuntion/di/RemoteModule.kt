package com.killjoy.stuntion.di

import com.killjoy.stuntion.features.data.repository.user.IUserRepository
import com.killjoy.stuntion.features.data.repository.user.UserRepository
import com.killjoy.stuntion.features.data.source.remote.api.service.StuntionApi
import com.killjoy.stuntion.features.data.source.remote.firebase.FirebaseDataSource
import com.killjoy.stuntion.features.data.util.Util.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteModule {
    @Provides
    @Singleton
    fun provideStuntionApi(): StuntionApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(StuntionApi::class.java)
    }

    @Provides
    fun provideUserUserRepository(
        stuntionApi: StuntionApi,
        firebaseDataSource: FirebaseDataSource,
    ): IUserRepository = UserRepository(stuntionApi, firebaseDataSource)
}