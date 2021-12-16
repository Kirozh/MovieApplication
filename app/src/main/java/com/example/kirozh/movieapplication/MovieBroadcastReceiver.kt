package com.example.kirozh.movieapplication

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi

class MovieBroadcastReceiver : BroadcastReceiver() {

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onReceive(context: Context, intent: Intent) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.
        val cm: ConnectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo

        if (activeNetwork?.isConnected == false) {
            Toast.makeText(context, R.string.noInternetConnection, Toast.LENGTH_LONG).show()
        }
    }
}