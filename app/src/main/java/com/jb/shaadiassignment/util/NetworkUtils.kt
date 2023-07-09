package com.jb.shaadiassignment.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities


 class NetworkUtils(private val context: Context ) {

    fun isNetworkAvailable() : Boolean{
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork
        val networkCapabilities = connectivityManager.getNetworkCapabilities(network)
        val status = networkCapabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) ?: false
        return status
    }
}