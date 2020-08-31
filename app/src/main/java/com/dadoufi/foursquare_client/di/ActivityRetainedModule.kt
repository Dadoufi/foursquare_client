package com.dadoufi.foursquare_client.di

import com.dadoufi.foursquare_client.data.repositories.VenuesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class ActivityRetainedModule {
    @Binds
    abstract fun venuesRepository(repository: VenuesRepository): VenuesRepository
}