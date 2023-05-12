package com.killjoy.stuntion.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(
    includes = [
        DatastoreModule::class,
        FirebaseModule::class,
        RemoteModule::class
    ]
)

@InstallIn(SingletonComponent::class)
object StuntionModule