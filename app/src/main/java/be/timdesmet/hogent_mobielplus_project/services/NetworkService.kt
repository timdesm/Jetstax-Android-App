package be.timdesmet.hogent_mobielplus_project.services

import android.app.AlertDialog
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

/**
 * Network availability check service
 *
 * This class will check the network availability and report it back to the user with an error message.
 *
 * @property context provider the context
 * @constructor creates an empty networkservice
 */

class NetworkService(private val context: Context) {

    /**
     *  Check if the network is available
     * @return if the network is available
     */
    fun isNetworkConnected(): Boolean {
        val connectivityManager = this.context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetwork
        val networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork)
        return networkCapabilities != null && networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }

    /**
     * Check if the network is available and send alert dialog if not
     * @param unit Function executed on alert dialog retry
     * @return if the network is available
     */
    fun check(unit: () -> Unit):Boolean {
        val connected = this.isNetworkConnected()
        if(!connected) showAlertDialog(unit)
        return connected
    }

    private fun showAlertDialog(unit: () -> Unit) {
        val builder = AlertDialog.Builder(this.context)
        builder.setTitle("No Network Connection")
        builder.setMessage("Make sure to connect to the wifi or mobile network and click Retry.").setCancelable(false)
            .setPositiveButton("Retry"
            ) { dialog, id -> unit() }
        val alert = builder.create()
        alert.show()
    }
}