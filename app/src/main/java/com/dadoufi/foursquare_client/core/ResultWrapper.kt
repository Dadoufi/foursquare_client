package com.dadoufi.foursquare_client.core

sealed class ResultWrapper<out R> {

    data class Success<out T>(val data: T) : ResultWrapper<T>()
    data class Error(val throwable: Throwable) : ResultWrapper<Nothing>() {
        override fun toString() = "${throwable.message}"
    }
}

internal val ResultWrapper<*>.succeeded
    get() = this is ResultWrapper.Success && data != null



