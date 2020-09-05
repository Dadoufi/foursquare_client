package com.dadoufi.foursquare_client.data.repositories

import app.cash.turbine.test
import com.dadoufi.foursquare_client.MainCoroutinesRule
import com.dadoufi.foursquare_client.core.ResultWrapper
import com.dadoufi.foursquare_client.data.local.LocalDataSource
import com.dadoufi.foursquare_client.data.model.asVenueDetailsEntity
import com.dadoufi.foursquare_client.data.model.asVenuesEntity
import com.dadoufi.foursquare_client.data.remote.NetworkDataSource
import com.dadoufi.foursquare_client.utils.ConnectivityStateManager
import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import testshared.SharedTestData.venueDetailResponseTestData
import testshared.SharedTestData.venueSearchResponseTestData

class VenuesRepositoryTest {

    private lateinit var repository: VenuesRepository
    private val networkDataSource: NetworkDataSource = mock()
    private val localDataSource: LocalDataSource = mock()
    private val connectivityStateManager: ConnectivityStateManager = mock()


    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesRule = MainCoroutinesRule()

    @ExperimentalCoroutinesApi
    @Before
    fun setup() {
        repository =
            VenuesRepositoryImpl(networkDataSource, localDataSource, connectivityStateManager)
    }

    @Test
    fun `test fetching venues from network success`() = runBlockingTest {
        val mockData = venueSearchResponseTestData

        val query = "query"
        val mockEntities = mockData.response.venues.asVenuesEntity(query)

        whenever(connectivityStateManager.isConnected()).doReturn(true)
        val flowResponse = flow {
            emit(mockData)
        }

        whenever(networkDataSource.getVenues(query)).doReturn(flowResponse)
        whenever(localDataSource.getStoredVenues(query)).doReturn(ResultWrapper.Success(mockEntities))

        repository.getVenues(query).test {
            assertEquals(expectItem(), ResultWrapper.Success(mockEntities))
        }

        verify(networkDataSource).getVenues(query)
        verify(localDataSource).getStoredVenues(eq(query))
        verify(localDataSource).deleteAndStoreVenues(eq(query), eq(mockEntities))
    }

    @Test
    fun `test fetching venues from network failure`(): Unit = runBlockingTest {
        val query = "query"
        whenever(connectivityStateManager.isConnected()).doReturn(true)
        val error = RuntimeException("ooops!")
        whenever(networkDataSource.getVenues(query)).doThrow(error)

        repository.getVenues(query).test {
            assertEquals(ResultWrapper.Error(error), expectItem())
        }

        verify(networkDataSource).getVenues(query)
    }

    @Test
    fun `test fetching venues from local storage`() = runBlockingTest {
        val mockData = venueSearchResponseTestData

        val query = "query"
        val mockEntities = mockData.response.venues.asVenuesEntity(query)

        whenever(connectivityStateManager.isConnected()).doReturn(false)
        whenever(localDataSource.getStoredVenues(query)).doReturn(ResultWrapper.Success(mockEntities))

        repository.getVenues(query).test {
            assertEquals(expectItem(), ResultWrapper.Success(mockEntities))
        }
        verifyZeroInteractions(networkDataSource)
        verify(localDataSource).getStoredVenues(eq(query))
    }

    @Test
    fun `test fetching venue details from network success`() = runBlockingTest {
        val mockData = venueDetailResponseTestData

        val query = "query"
        val mockEntities = mockData.response.venue.asVenueDetailsEntity()

        whenever(connectivityStateManager.isConnected()).doReturn(true)
        val flowResponse = flow {
            emit(mockData)
        }

        whenever(networkDataSource.getVenueDetails(query)).doReturn(flowResponse)
        whenever(localDataSource.getStoredVenueDetails(query)).doReturn(
            ResultWrapper.Success(
                mockEntities
            )
        )

        repository.getVenueDetail(query).test {
            assertEquals(expectItem(), ResultWrapper.Success(mockEntities))
        }

        verify(networkDataSource).getVenueDetails(query)
        verify(localDataSource).deleteAndStoreVenuesDetails(eq(query), eq(mockEntities))
        verify(localDataSource).getStoredVenueDetails(eq(query))

    }

    @Test
    fun `test fetching venue details from network failure`() = runBlockingTest {
        val query = "query"
        whenever(connectivityStateManager.isConnected()).doReturn(true)
        val error = RuntimeException("ooops!")
        whenever(networkDataSource.getVenueDetails(query)).doThrow(error)

        repository.getVenueDetail(query).test {
            assertEquals(ResultWrapper.Error(error), expectItem())
        }

        verify(networkDataSource).getVenueDetails(query)
    }

    @Test
    fun `test fetching venue details from local storage`() = runBlockingTest {
        val mockData = venueDetailResponseTestData

        val query = "query"
        val mockEntities = mockData.response.venue.asVenueDetailsEntity()

        whenever(connectivityStateManager.isConnected()).doReturn(false)
        whenever(localDataSource.getStoredVenueDetails(query)).doReturn(
            ResultWrapper.Success(
                mockEntities
            )
        )

        repository.getVenueDetail(query).test {
            assertEquals(expectItem(), ResultWrapper.Success(mockEntities))
        }
        verifyZeroInteractions(networkDataSource)
        verify(localDataSource).getStoredVenueDetails(eq(query))
    }
}