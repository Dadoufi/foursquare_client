package com.dadoufi.foursquare_client.db

import com.dadoufi.foursquare_client.data.local.daos.VenuesDao
import com.dadoufi.foursquare_client.data.model.asVenuesEntity
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import kotlin.test.assertEquals

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [23])
class VenuesDaoTest : LocalDatabase() {

    private lateinit var dao: VenuesDao

    @Before
    fun init() {
        dao = db.venuesDao()
    }

    @Test
    fun `delete and insert venue list`() = runBlocking {
        val query = "pizza"
        val venues =
            testshared.SharedTestData.venueSearchResponseTestData.response.venues.asVenuesEntity(
                query
            )
        dao.storeVenues(venues)
        assertEquals(dao.getAllVenues(query), venues)

        dao.deleteVenuesForQuery(query)

        assertEquals(dao.getAllVenues(query), mutableListOf())
    }
}
