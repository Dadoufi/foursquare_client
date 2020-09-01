package com.dadoufi.foursquare_client.data.remote

import com.dadoufi.foursquare_client.data.model.VenueDetailResponse
import com.dadoufi.foursquare_client.data.model.VenueSearchResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject


interface NetworkDataSource {
    suspend fun getVenues(query: String): Flow<VenueSearchResponse>

    suspend fun getVenueDetails(venueId: String): Flow<VenueDetailResponse>
}

@ExperimentalCoroutinesApi
class NetworkDataSourceImpl @Inject constructor(
    private val webService: WebService
) : NetworkDataSource {

    override suspend fun getVenues(query: String): Flow<VenueSearchResponse> =
        callbackFlow {
            offer(webService.searchVenues(query = query))

            awaitClose { close() }
        }

    override suspend fun getVenueDetails(venueId: String): Flow<VenueDetailResponse> =
        callbackFlow {
            offer(webService.getVenueDetails(venueId))

            awaitClose { close() }
        }

}