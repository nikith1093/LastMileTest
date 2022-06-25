package com.example.lastmiletest.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Singleton
    @Provides
    @Named("api_key")
    fun provideApiKey(): String = "009fef010e427fa9b6fc0ebab4d515a8"
}