package be.timdesmet.hogent_mobielplus_project.utils

import android.app.Service
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

object NetworkUtil {
    fun isConnected(context : Context) :Boolean{
        var connectivityManager : ConnectivityManager? = null;
        connectivityManager = context.getSystemService(Service.CONNECTIVITY_SERVICE) as ConnectivityManager;
        if(connectivityManager != null){
            var info = connectivityManager!!.activeNetworkInfo
            if(info != null)
                return (info.state == NetworkInfo.State.CONNECTED)
        }
        return false;
    }
}