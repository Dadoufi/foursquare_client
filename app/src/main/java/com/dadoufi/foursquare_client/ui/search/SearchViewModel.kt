package com.dadoufi.foursquare_client.ui.search

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.dadoufi.foursquare_client.core.ResultWrapper
import com.dadoufi.foursquare_client.data.repositories.VenuesRepository
import com.dadoufi.foursquare_client.utils.AppCoroutineDispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart

@FlowPreview
@ExperimentalCoroutinesApi
class SearchViewModel @ViewModelInject constructor(
    private val repository: VenuesRepository,
    private val dispatchers: AppCoroutineDispatchers,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val searchQuery: MutableLiveData<String> =
        savedStateHandle.getLiveData("query", "pizza")


    val viewState: LiveData<SearchViewState> =
        searchQuery.distinctUntilChanged().switchMap { query ->
            liveData(viewModelScope.coroutineContext + dispatchers.io) {
                if (query.isNullOrEmpty()) {
                    emit(SearchViewState.VenuesLoaded())
                } else {
                    repository.getVenues(query)
                        .onStart { emit(SearchViewState.Loading) }
                        .collect {
                            if (it is ResultWrapper.Success) {
                                emit(SearchViewState.VenuesLoaded(it.data))
                            } else if (it is ResultWrapper.Error) {
                                emit(SearchViewState.VenuesLoadedError(it.throwable.message))
                            }
                        }
                }

            }.distinctUntilChanged()
        }

    fun setQuery(query: String) {
        savedStateHandle.set("query", query)
        searchQuery.value = query
    }

}