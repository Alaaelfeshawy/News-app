package com.example.newsapp.util

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import android.net.ConnectivityManager
import android.net.NetworkInfo
import javax.inject.Inject

class NetworkUtils @Inject constructor(@param:ApplicationContext private val context: Context) {
    val isNetworkConnected: Boolean
        get() {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
            if (cm != null) {
                val activeNetwork = cm.activeNetworkInfo
                return activeNetwork != null && activeNetwork.isConnectedOrConnecting
            }
            return false
        }
}