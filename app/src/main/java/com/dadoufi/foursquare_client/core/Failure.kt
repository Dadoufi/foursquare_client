package com.dadoufi.foursquare_client.core

import java.io.IOException

sealed class Failure(override val message: String) : IOException(message) {
    data class ServerFailure(override val message: String) : Failure(message)
    data class SerializationFailure(override val message: String) : Failure(message = message)
    data class CancellationFailure(override val message: String) : Failure(message = message)
    data class UnhandledFailure(override val message: String) : Failure(message = message)
}
