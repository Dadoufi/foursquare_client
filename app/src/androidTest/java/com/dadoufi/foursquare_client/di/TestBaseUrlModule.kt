package com.dadoufi.foursquare_client.di

import com.dadoufi.foursquare_client.mockwebserver.API_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@InstallIn(ApplicationComponent::class)
@Module
object TestBaseUrlModule {

    @Provides
    @BaseUrl
    fun provideBaseUrl(): String = API_URL
}