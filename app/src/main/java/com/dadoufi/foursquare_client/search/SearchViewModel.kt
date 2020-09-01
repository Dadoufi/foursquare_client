package com.dadoufi.foursquare_client.search

import android.util.Log
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.dadoufi.foursquare_client.core.ResultWrapper
import com.dadoufi.foursquare_client.data.local.entities.VenuesEntity
import com.dadoufi.foursquare_client.data.repositories.VenuesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect

class SearchViewModel @ViewModelInject constructor(
    private val repository: VenuesRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val queryName =
        savedStateHandle.getLiveData("cocktailName", "tacos")

    val venuesList: LiveData<ResultWrapper<List<VenuesEntity>>> =
        queryName.distinctUntilChanged().switchMap { query ->
            liveData(viewModelScope.coroutineContext + Dispatchers.IO) {
                repository.getVenues("pizza").collect {
                    Log.d("asd", it.toString())
                    emit(it)
                }
            }
        }


}