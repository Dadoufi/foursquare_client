package com.dadoufi.foursquare_client.mockwebserver

import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest
import okio.buffer
import okio.source
import java.nio.charset.StandardCharsets

class SuccessDispatcher : Dispatcher() {
    private val responseFilesByPath: Map<String, String> = mapOf(
        APIPaths.PIZZA_SEARCH to MockFiles.PIZZA_SEARCH_SUCCESS,
        APIPaths.VENUE_DETAILS to ""
    )

    override fun dispatch(request: RecordedRequest): MockResponse {
        val errorResponse = MockResponse().setResponseCode(404)

        val requestUrl = request.requestUrl.toString()
        val responseFile = responseFilesByPath[requestUrl]

        return if (responseFile != null) {
            val response = MockResponse().setResponseCode(200)
            if (responseFile.isNotEmpty()) {
                val inputStream =
                    javaClass.classLoader!!.getResourceAsStream("api-response/$responseFile")
                val source = inputStream.source().buffer()
                response.setBody(source.readString(StandardCharsets.UTF_8))
            }
            response
        } else {
            errorResponse
        }
    }
}