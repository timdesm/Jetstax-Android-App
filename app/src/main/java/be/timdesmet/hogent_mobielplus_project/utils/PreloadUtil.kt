package be.timdesmet.hogent_mobielplus_project.utils

import android.content.Context
import be.timdesmet.hogent_mobielplus_project.domain.shop.Banner
import be.timdesmet.hogent_mobielplus_project.network.presistence.getDatabase
import be.timdesmet.hogent_mobielplus_project.network.presistence.repositories.BannerRepository
import be.timdesmet.hogent_mobielplus_project.network.presistence.repositories.ProductRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PreloadUtil {

    private var isReady: Boolean = false

    fun loadPreloaded(context: Context) {
        val database =  getDatabase(context)
        val bannerRepository = BannerRepository(database)
        val productRepository = ProductRepository(database)

        GlobalScope.launch {
            if(NetworkUtil.isConnected(context)) {
                // productRepository.refreshProducts()
            }
            isReady = true
        }
    }

    fun isReady(): Boolean {
        return isReady
    }
}