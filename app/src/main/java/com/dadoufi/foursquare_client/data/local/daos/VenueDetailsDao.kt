package com.dadoufi.foursquare_client.data.local.daos

import androidx.room.*
import com.dadoufi.foursquare_client.data.local.entities.VenueDetailsEntity

@Dao
interface VenueDetailsDao {

    @Query("SELECT * FROM venue_details WHERE `venueId` = :venueId")
    suspend fun getVenueDetails(venueId: String): VenueDetailsEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun storeVenueDetail(venue: VenueDetailsEntity)

    @Query("DELETE FROM venue_details WHERE `venueId` = :venueId")
    suspend fun deleteVenueDetail(venueId: String)

    @Transaction
    suspend fun deleteAndStore(venueId: String, venueDetail: VenueDetailsEntity) {
        deleteVenueDetail(venueId)
        storeVenueDetail(venueDetail)
    }

}