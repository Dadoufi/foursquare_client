package com.dadoufi.foursquare_client.utils

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow

inline fun <T> LiveData<T>.observeK(owner: LifecycleOwner, crossinline observer: (T?) -> Unit) {
    this.observe(owner, Observer { observer(it) })
}

inline fun <T> LiveData<T>.observeNotNull(
    owner: LifecycleOwner,
    crossinline observer: (T) -> Unit
) {
    this.observe(owner, Observer { it?.run(observer) })
}

@ExperimentalCoroutinesApi
fun <T> LiveData<T>.asFlow(): Flow<T> {
    return channelFlow {
        value?.also { send(it) }
        val observer = Observer<T> { v -> offer(v) }
        observeForever(observer)
        awaitClose {
            removeObserver(observer)
        }
    }
}