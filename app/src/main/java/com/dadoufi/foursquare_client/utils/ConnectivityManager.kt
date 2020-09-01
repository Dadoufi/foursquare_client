package com.dadoufi.foursquare_client.utils

import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
import android.net.NetworkCapabilities.NET_CAPABILITY_INTERNET
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

interface ConnectivityStateManager {

    fun isConnected(): Boolean
}


class ConnectivityStateManagerImpl @Inject constructor(@ApplicationContext val context: Context) :
    ConnectivityStateManager {

    override fun isConnected(): Boolean {
        val cm = context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities = cm.getNetworkCapabilities(cm.activeNetwork)
        return capabilities?.hasCapability(NET_CAPABILITY_INTERNET) == true
    }
}