package com.dadoufi.foursquare_client.ui.search

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.lifecycle.SavedStateHandle
import com.dadoufi.foursquare_client.MainCoroutinesRule
import com.dadoufi.foursquare_client.core.ResultWrapper
import com.dadoufi.foursquare_client.data.local.entities.VenuesEntity
import com.dadoufi.foursquare_client.data.model.asVenuesEntity
import com.dadoufi.foursquare_client.data.repositories.VenuesRepository
import com.dadoufi.foursquare_client.utils.AppCoroutineDispatchers
import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import testshared.SharedTestData

@FlowPreview
class SearchViewModelTest {
    private val repository: VenuesRepository = mock()
    private lateinit var viewModel: SearchViewModel

    private var observer: Observer<SearchViewState> = mock()

    private val dispatchers = AppCoroutineDispatchers(
        main = TestCoroutineDispatcher(),
        io = TestCoroutineDispatcher(),
        computation = TestCoroutineDispatcher(),
    )

    @get:Rule
    var coroutinesRule = MainCoroutinesRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        viewModel = SearchViewModel(repository, dispatchers, SavedStateHandle())
    }


    @Test
    fun `test fetching venue detail success`() = runBlockingTest {
        val mockData = SharedTestData.venueSearchResponseTestData

        val query = "query"
        val mockEntities = mockData.response.venues.asVenuesEntity(query)

        val flowResult = flow {
            emit(ResultWrapper.Success(mockEntities))
        }
        whenever(repository.getVenues(any())).doReturn(flowResult)

        viewModel.setQuery(query)
        viewModel.viewState.observeForever(observer)



        verify(observer).onChanged(eq(SearchViewState.Loading))
        verify(observer).onChanged(eq(SearchViewState.VenuesLoaded(mockEntities)))
        verify(repository).getVenues(eq(query))
        viewModel.viewState.removeObserver(observer)

    }

    @Test
    fun `test fetching venue detail empty result`() = runBlockingTest {
        val query = "query"

        val flowResult = flow {
            emit(ResultWrapper.Success(mutableListOf<VenuesEntity>()))
        }
        whenever(repository.getVenues(any())).doReturn(flowResult)

        viewModel.setQuery(query)
        viewModel.viewState.observeForever(observer)


        verify(observer).onChanged(eq(SearchViewState.Loading))
        verify(observer).onChanged(eq(SearchViewState.VenuesLoaded(mutableListOf())))
        verify(repository).getVenues(eq(query))
        viewModel.viewState.removeObserver(observer)

    }

    @Test
    fun `test fetching venue detail error`() = runBlockingTest {
        val error = RuntimeException("ooops!")
        val flowResult = flow {
            emit(ResultWrapper.Error(error))
        }

        whenever(repository.getVenues(any())).doReturn(flowResult)
        viewModel.setQuery("query")
        viewModel.viewState.observeForever(observer)



        verify(observer).onChanged(eq(SearchViewState.Loading))
        verify(observer).onChanged(eq(SearchViewState.VenuesLoadedError("ooops!")))
        verify(repository).getVenues(eq("query"))
        viewModel.viewState.removeObserver(observer)

    }
}