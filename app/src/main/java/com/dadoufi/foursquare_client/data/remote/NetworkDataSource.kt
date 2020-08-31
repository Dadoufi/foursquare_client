package com.dadoufi.foursquare_client.data.remote

import com.dadoufi.foursquare_client.core.Resource
import com.dadoufi.foursquare_client.data.model.VenueSearchResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

@ExperimentalCoroutinesApi
class NetworkDataSource @Inject constructor(
    private val webService: WebService
) {
    suspend fun getVenues(location: String): Flow<Resource<VenueSearchResponse>> =
        callbackFlow {
//            offer(
//                Resource.Success(
//
//                )
//            )
            awaitClose { close() }
        }
}