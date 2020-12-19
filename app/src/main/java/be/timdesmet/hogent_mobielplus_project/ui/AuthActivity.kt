package be.timdesmet.hogent_mobielplus_project.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.FragmentTransaction
import be.timdesmet.hogent_mobielplus_project.R
import be.timdesmet.hogent_mobielplus_project.ui.fragment.auth.ConnectionFragment
import be.timdesmet.hogent_mobielplus_project.ui.fragment.auth.LoginFragment
import be.timdesmet.hogent_mobielplus_project.utils.NetworkUtil

class AuthActivity : AppCompatActivity() {

    private var networkConnection: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
    }

    override fun onStart() {
        super.onStart()
        networkMonitor()
    }

    private fun networkMonitor() {
        val app = this
        val mainHandler = Handler(Looper.getMainLooper())
        mainHandler.post(object : Runnable {
            override fun run() {
                if(NetworkUtil.isConnected(application)) {
                    if(!app.networkConnection) {
                        app.networkConnection = true
                        app.supportFragmentManager.beginTransaction()
                            .replace(R.id.drawer_layout, LoginFragment())
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
                            .commit()
                    }
                }
                else {
                    if(app.networkConnection) {
                        app.networkConnection = false
                        app.supportFragmentManager.beginTransaction()
                            .replace(R.id.drawer_layout, ConnectionFragment())
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .commit()
                    }
                }
                mainHandler.postDelayed(this, 1500)
            }
        })
    }

}