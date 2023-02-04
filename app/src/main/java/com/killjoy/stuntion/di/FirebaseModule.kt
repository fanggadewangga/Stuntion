package com.killjoy.stuntion.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.storage.FirebaseStorage
import com.killjoy.stuntion.features.data.source.remote.firebase.FirebaseDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class FirebaseModule {
    @Provides
    fun provideFirebaseAuth() = FirebaseAuth.getInstance()

    @Provides
    fun provideFirebaseStorage() = FirebaseStorage.getInstance()

    @Provides
    fun provideFirebaseMessaging() = FirebaseMessaging.getInstance()

    @Provides
    fun provideFirebaseDataSource(
        firebaseAuth: FirebaseAuth,
        firebaseStorage: FirebaseStorage,
        firebaseMessaging: FirebaseMessaging,
    ) = FirebaseDataSource(
        firebaseAuth = firebaseAuth,
        firebaseStorage = firebaseStorage,
        firebaseMessaging = firebaseMessaging
    )
}