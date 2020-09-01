package com.dadoufi.foursquare_client.di

import com.dadoufi.foursquare_client.data.local.LocalDataSource
import com.dadoufi.foursquare_client.data.local.LocalDataSourceImpl
import com.dadoufi.foursquare_client.data.remote.NetworkDataSource
import com.dadoufi.foursquare_client.data.remote.NetworkDataSourceImpl
import com.dadoufi.foursquare_client.data.repositories.VenuesRepository
import com.dadoufi.foursquare_client.data.repositories.VenuesRepositoryImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class ActivityRetainedModule {

    @ExperimentalCoroutinesApi
    @Binds
    abstract fun localDataSource(localDataSource: LocalDataSourceImpl): LocalDataSource

    @ExperimentalCoroutinesApi
    @Binds
    abstract fun networkDataSource(networkDataSource: NetworkDataSourceImpl): NetworkDataSource

    @ExperimentalCoroutinesApi
    @Binds
    abstract fun venuesRepository(repository: VenuesRepositoryImp): VenuesRepository

}