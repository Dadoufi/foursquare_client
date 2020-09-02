package com.dadoufi.foursquare_client.data.remote

import com.dadoufi.foursquare_client.data.model.VenueDetailResponse
import com.dadoufi.foursquare_client.data.model.VenueSearchResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface WebService {

    @GET("venues/search")
    suspend fun searchVenues(
        @Query(value = "near") location: String? = "Amsterdam",
        @Query(value = "radius") radius: Int? = 1000,
        @Query(value = "limit") limit: Int? = 10,
        @Query(value = "query") query: String
    ): VenueSearchResponse

    @GET("venues/{VENUE_ID}")
    suspend fun getVenueDetails(
        @Path(value = "VENUE_ID") venueId: String
    ): VenueDetailResponse
}