package com.dadoufi.foursquare_client.di

import com.dadoufi.foursquare_client.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@InstallIn(ApplicationComponent::class)
@Module
object BaseUrlModule {

    @Provides
    @BaseUrl
    fun provideBaseUrl(): String = BASE_URL
}