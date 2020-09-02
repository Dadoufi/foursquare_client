package com.dadoufi.foursquare_client.ui.search

import android.util.Log
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.dadoufi.foursquare_client.core.ResultWrapper
import com.dadoufi.foursquare_client.data.repositories.VenuesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart

@FlowPreview
@ExperimentalCoroutinesApi
class SearchViewModel @ViewModelInject constructor(
    private val repository: VenuesRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val searchQuery =
        savedStateHandle.getLiveData("query", "")


    val viewState: LiveData<SearchViewState> =
        searchQuery.distinctUntilChanged().switchMap { query ->
            liveData(viewModelScope.coroutineContext + Dispatchers.IO) {
                if (query.isEmpty()) {
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

                            Log.d("asd", it.toString())

                        }
                }

            }
        }

    fun setQuery(query: String) {
        searchQuery.value = query
    }
}