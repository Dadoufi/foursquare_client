package com.dadoufi.foursquare_client.data

import com.dadoufi.foursquare_client.core.Resource
import com.dadoufi.foursquare_client.data.model.VenueSearchResponse
import kotlinx.coroutines.flow.Flow


interface RemoteDataSource {
    suspend fun fetchVenues(location: String): Flow<Resource<VenueSearchResponse>>

    suspend fun fetchVenueDetails()
}