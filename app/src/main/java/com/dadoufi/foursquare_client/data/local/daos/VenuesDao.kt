package com.dadoufi.foursquare_client.data.local.daos

import androidx.room.*
import com.dadoufi.foursquare_client.data.local.entities.VenuesEntity


@Dao
interface VenuesDao {

    @Query("SELECT * FROM venues WHERE `query` = :query")
    suspend fun getAllVenues(query: String): List<VenuesEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun storeVenues(venues: List<VenuesEntity>)

    @Query("DELETE FROM venues WHERE `query` = :query")
    suspend fun deleteVenuesForQuery(query: String)

    @Transaction
    suspend fun deleteAndStore(query: String, venues: List<VenuesEntity>) {
        deleteVenuesForQuery(query)
        storeVenues(venues)
    }

}