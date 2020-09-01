package com.dadoufi.foursquare_client.data.local

import com.dadoufi.foursquare_client.core.ResultWrapper
import com.dadoufi.foursquare_client.data.local.daos.VenueDetailsDao
import com.dadoufi.foursquare_client.data.local.daos.VenuesDao
import com.dadoufi.foursquare_client.data.local.entities.VenueDetailsEntity
import com.dadoufi.foursquare_client.data.local.entities.VenuesEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi

import javax.inject.Inject

interface LocalDataSource {
    suspend fun getStoredVenues(query: String): ResultWrapper<List<VenuesEntity>>

    suspend fun deleteAndStoreVenues(query: String, venues: List<VenuesEntity>)

    suspend fun getStoredVenueDetails(venueId: String): ResultWrapper<VenueDetailsEntity>

    suspend fun deleteAndStoreVenuesDetails(venueId: String, venueDetails: VenueDetailsEntity)
}


@ExperimentalCoroutinesApi
class LocalDataSourceImpl @Inject constructor(
    private val venuesDao: VenuesDao,
    private val venueDetailsDao: VenueDetailsDao
) : LocalDataSource {

    override suspend fun getStoredVenues(query: String): ResultWrapper<List<VenuesEntity>> {
        return ResultWrapper.Success(venuesDao.getAllVenues(query))
    }

    override suspend fun deleteAndStoreVenues(query: String, venues: List<VenuesEntity>) {
        venuesDao.deleteAndStore(query, venues)
    }

    override suspend fun getStoredVenueDetails(venueId: String): ResultWrapper<VenueDetailsEntity> {
        return ResultWrapper.Success(venueDetailsDao.getVenueDetails(venueId))
    }

    override suspend fun deleteAndStoreVenuesDetails(
        venueId: String,
        venueDetails: VenueDetailsEntity
    ) {
        venueDetailsDao.deleteAndStore(venueId, venueDetails)
    }

}