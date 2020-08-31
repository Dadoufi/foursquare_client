package com.dadoufi.foursquare_client.data.local

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
abstract class VenuesDao {

    @Query("SELECT * FROM venues")
    abstract fun getAllVenues(): Flow<List<VenuesEntity>>
}