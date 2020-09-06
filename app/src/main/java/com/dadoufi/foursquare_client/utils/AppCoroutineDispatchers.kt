package com.dadoufi.foursquare_client.utils

import kotlinx.coroutines.CoroutineDispatcher

data class AppCoroutineDispatchers(
    val main: CoroutineDispatcher,
    val io: CoroutineDispatcher,
    val computation: CoroutineDispatcher
)