package com.dadoufi.foursquare_client.ui.search

import android.util.Log
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.dadoufi.foursquare_client.core.ResultWrapper
import com.dadoufi.foursquare_client.data.repositories.VenuesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart

@ExperimentalCoroutinesApi
class SearchViewModel @ViewModelInject constructor(
    private val repository: VenuesRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val queryName =
        savedStateHandle.getLiveData("cocktailName", "tacos")

//    private val _viewState: MutableLiveData<SearchViewState> = MutableLiveData()
//    val viewState: LiveData<SearchViewState>
//        get() = _viewState


    val viewState: LiveData<SearchViewState> =
        queryName.distinctUntilChanged().switchMap { query ->
            liveData(viewModelScope.coroutineContext + Dispatchers.IO) {
                repository.getVenues("pizza")
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