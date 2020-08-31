package com.dadoufi.foursquare_client.data.remote

import com.dadoufi.foursquare_client.utils.Constants
import com.dadoufi.foursquare_client.utils.Constants.CLIENT_ID
import com.dadoufi.foursquare_client.utils.Constants.CLIENT_SECRET
import com.dadoufi.foursquare_client.utils.Constants.FOURSQUARE_API_VERSION
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response


class ApiInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request: Request = chain.request()
        val url: HttpUrl = request.url.newBuilder().apply {
            addQueryParameter("client_id", CLIENT_ID)
            addQueryParameter("client_secret", CLIENT_SECRET)
            addQueryParameter("v", FOURSQUARE_API_VERSION)


        }.build()
        request = request.newBuilder().url(url).build()
        return chain.proceed(request)
    }
}