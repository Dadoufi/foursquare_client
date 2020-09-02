package com.dadoufi.foursquare_client.ui.detail

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
class DetailViewModel @ViewModelInject constructor(
    private val repository: VenuesRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val venueId: MutableLiveData<String> =
        savedStateHandle.getLiveData("venueId")

    init {

    }


    val viewState: LiveData<DetailViewState> =
        venueId.distinctUntilChanged().switchMap { venueId ->
            liveData(viewModelScope.coroutineContext + Dispatchers.IO) {
                if (venueId.isEmpty()) {
                    emit(DetailViewState.DetailLoaded())
                } else {
                    repository.getVenueDetail(venueId)
                        .onStart { emit(DetailViewState.Loading) }
                        .collect {
                            if (it is ResultWrapper.Success) {
                                emit(DetailViewState.DetailLoaded(it.data))
                            } else if (it is ResultWrapper.Error) {
                                emit(DetailViewState.DetailError(it.throwable.message))
                            }

                            Log.d("asd", it.toString())

                        }
                }

            }
        }

}