package com.killjoy.stuntion.di

import android.content.Context
import com.killjoy.stuntion.features.data.source.local.datastore.StuntionDatastore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DatastoreModule {
    @Provides
    fun provideDatastore(
        @ApplicationContext context: Context,
    ) = StuntionDatastore(context)
}