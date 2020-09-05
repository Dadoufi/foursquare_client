package com.dadoufi.foursquare_client.db


import com.dadoufi.foursquare_client.data.local.daos.VenueDetailsDao
import com.dadoufi.foursquare_client.data.model.asVenueDetailsEntity
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import kotlin.test.assertEquals
import kotlin.test.assertNull

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [23])
class VenueDetailDaoTest : LocalDatabase() {

    private lateinit var dao: VenueDetailsDao

    @Before
    fun init() {
        dao = db.venueDetailsDao()
    }

    @Test
    fun `delete and store venue detail`() = runBlocking {

        val venueDetails =
            testshared.SharedTestData.venueDetailResponseTestData.response.venue.asVenueDetailsEntity()

        val venueId = venueDetails.venueId
        dao.storeVenueDetail(venueDetails)
        assertEquals(dao.getVenueDetails(venueId), venueDetails)

        dao.deleteVenueDetail(venueId)

        assertNull(dao.getVenueDetails(venueId))
    }

}
