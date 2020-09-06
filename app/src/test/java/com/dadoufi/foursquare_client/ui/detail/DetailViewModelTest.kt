package com.dadoufi.foursquare_client.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.lifecycle.SavedStateHandle
import com.dadoufi.foursquare_client.MainCoroutinesRule
import com.dadoufi.foursquare_client.core.ResultWrapper
import com.dadoufi.foursquare_client.data.model.asVenueDetailsEntity
import com.dadoufi.foursquare_client.data.repositories.VenuesRepository
import com.dadoufi.foursquare_client.utils.AppCoroutineDispatchers
import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import testshared.SharedTestData

@FlowPreview
@ExperimentalCoroutinesApi
class DetailViewModelTest {

    private val repository: VenuesRepository = mock()
    private lateinit var viewModel: DetailViewModel

    private var observer: Observer<DetailViewState> = mock()

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
        viewModel = DetailViewModel(repository, dispatchers, SavedStateHandle())
    }


    @Test
    fun `test fetching venue detail success`() = runBlockingTest {
        val mockData = SharedTestData.venueDetailResponseTestData

        val mockEntities = mockData.response.venue.asVenueDetailsEntity()

        val flowResult = flow {
            emit(ResultWrapper.Success(mockEntities))
        }
        whenever(repository.getVenueDetail(any())).doReturn(flowResult)

        viewModel.setVenueId("id")
        viewModel.viewState.observeForever(observer)



        verify(observer).onChanged(eq(DetailViewState.Loading))
        verify(observer).onChanged(eq(DetailViewState.DetailLoaded(mockEntities)))
        verify(repository).getVenueDetail(eq("id"))
        viewModel.viewState.removeObserver(observer)

    }

    @Test
    fun `test fetching venue detail error`() = runBlockingTest {
        val error = RuntimeException("ooops!")
        val flowResult = flow {
            emit(ResultWrapper.Error(error))
        }

        whenever(repository.getVenueDetail(any())).doReturn(flowResult)
        viewModel.setVenueId("id")
        viewModel.viewState.observeForever(observer)


        verify(observer).onChanged(eq(DetailViewState.Loading))
        verify(observer).onChanged(eq(DetailViewState.DetailError("ooops!")))
        verify(repository).getVenueDetail(eq("id"))
        viewModel.viewState.removeObserver(observer)

    }

}