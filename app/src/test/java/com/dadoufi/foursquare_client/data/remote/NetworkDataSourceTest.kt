package com.dadoufi.foursquare_client.data.remote

import com.dadoufi.foursquare_client.MainCoroutinesRule
import com.nhaarman.mockitokotlin2.mock
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import testshared.SharedTestData.venueDetailResponseTestData
import testshared.SharedTestData.venueSearchResponseTestData
import java.io.IOException
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
class NetworkDataSourceTest : ApiMocker<WebService>() {

    private lateinit var service: WebService
    private val networkDataSource: NetworkDataSource = mock()

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesRule = MainCoroutinesRule()


    @Before
    fun initService() {
        service = createService(WebService::class.java)
    }

    @Throws(IOException::class)
    @Test
    fun `fetch venues from network test`(): Unit = runBlocking {
        enqueueResponse("/venues.json")
        val response = service.searchVenues(query = "pizza")
        mockWebServer.takeRequest()

        networkDataSource.getVenues("pizza")
        assertEquals(response.meta.code, 200)
        assertEquals(response.response.venues.size, 10)
        val testData = venueSearchResponseTestData.response.venues[0]
        assertEquals(response.response.venues[0], testData)
    }

    @Throws(IOException::class)
    @Test
    fun `fetch venues details from network test`(): Unit = runBlocking {
        enqueueResponse("/venueDetails.json")
        val response = service.getVenueDetails(venueId = "4a27db7bf964a52016941fe3")
        mockWebServer.takeRequest()

        networkDataSource.getVenueDetails("4a27db7bf964a52016941fe3")
        assertEquals(response.meta.code, 200)
        assertEquals(response.response.venue, venueDetailResponseTestData.response.venue)
    }
}
