package com.dadoufi.foursquare_client.data.repositories

import com.dadoufi.foursquare_client.core.ResultWrapper
import com.dadoufi.foursquare_client.data.local.LocalDataSource
import com.dadoufi.foursquare_client.data.local.entities.VenueDetailsEntity
import com.dadoufi.foursquare_client.data.local.entities.VenuesEntity
import com.dadoufi.foursquare_client.data.model.asVenueDetailsEntity
import com.dadoufi.foursquare_client.data.model.asVenuesEntity
import com.dadoufi.foursquare_client.data.remote.NetworkDataSource
import com.dadoufi.foursquare_client.utils.ConnectivityStateManager
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

interface VenuesRepository : Repository {
    suspend fun getVenues(query: String): Flow<ResultWrapper<List<VenuesEntity>>>

    suspend fun getVenueDetail(venueId: String): Flow<ResultWrapper<VenueDetailsEntity>>
}

@ExperimentalCoroutinesApi
@ActivityRetainedScoped
class VenuesRepositoryImpl
@Inject constructor(
    private val networkDataSource: NetworkDataSource,
    private val localDataSource: LocalDataSource,
    private val connectivityStateManager: ConnectivityStateManager
) : VenuesRepository {

    override suspend fun getVenues(query: String): Flow<ResultWrapper<List<VenuesEntity>>> =
        callbackFlow {
            try {
                if (!connectivityStateManager.isConnected()) {
                    offer(localDataSource.getStoredVenues(query))
                } else {
                    networkDataSource.getVenues(query).collect {
                        localDataSource.deleteAndStoreVenues(
                            query,
                            it.response.venues.asVenuesEntity(query)
                        )
                        offer(localDataSource.getStoredVenues(query))
                    }
                }
            } catch (e: Exception) {
                offer(ResultWrapper.Error(e))
            }
            awaitClose { cancel() }
        }


    override suspend fun getVenueDetail(venueId: String): Flow<ResultWrapper<VenueDetailsEntity>> =
        callbackFlow {
            try {
                if (!connectivityStateManager.isConnected()) {
                    offer(localDataSource.getStoredVenueDetails(venueId))
                } else {
                    networkDataSource.getVenueDetails(venueId).collect {
                        localDataSource.deleteAndStoreVenuesDetails(
                            venueId,
                            it.response.venue.asVenueDetailsEntity()
                        )
                        offer(localDataSource.getStoredVenueDetails(venueId))
                    }
                }
            } catch (e: Exception) {
                offer(ResultWrapper.Error(e))
            }
            awaitClose { cancel() }
        }
}
