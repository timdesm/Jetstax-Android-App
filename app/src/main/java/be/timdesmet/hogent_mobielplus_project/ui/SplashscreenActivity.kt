package be.timdesmet.hogent_mobielplus_project.ui

import android.app.Application
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import be.timdesmet.hogent_mobielplus_project.R
import be.timdesmet.hogent_mobielplus_project.network.JetstaxApi
import be.timdesmet.hogent_mobielplus_project.network.ShopEndpoint
import be.timdesmet.hogent_mobielplus_project.network.presistence.getDatabase
import be.timdesmet.hogent_mobielplus_project.network.presistence.repositories.BannerRepository
import be.timdesmet.hogent_mobielplus_project.network.presistence.repositories.SessionRepository
import be.timdesmet.hogent_mobielplus_project.services.NetworkService
import be.timdesmet.hogent_mobielplus_project.services.UserService
import be.timdesmet.hogent_mobielplus_project.utils.NetworkUtil
import be.timdesmet.hogent_mobielplus_project.utils.PreloadUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SplashscreenActivity : AppCompatActivity() {

    private val app  = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)
        supportActionBar?.hide()
    }

    override fun onStart() {
        super.onStart()

        Thread {
            val preloadUtil = PreloadUtil()
            preloadUtil.loadPreloaded(this)

            while(!preloadUtil.isReady())
                Thread.sleep(50)

            val database = getDatabase(this)
            val sessionResponse = SessionRepository(database)
            UserService.token = sessionResponse.token
            UserService.userId = sessionResponse.userId

            lifecycleScope.launch {
                withContext(Dispatchers.Main) {
                    UserService.token.observe(app, Observer {
                        if(UserService.loggedIn)
                            startActivity(Intent(app, MainActivity::class.java))
                        else
                            startActivity(Intent(app, AuthActivity::class.java))
                    })
                }
            }
        }.start()
    }
}
