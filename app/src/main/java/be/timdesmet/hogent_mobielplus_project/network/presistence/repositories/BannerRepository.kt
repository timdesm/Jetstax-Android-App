package be.timdesmet.hogent_mobielplus_project.network.presistence.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import be.timdesmet.hogent_mobielplus_project.domain.shop.Banner
import be.timdesmet.hogent_mobielplus_project.network.JetstaxApi
import be.timdesmet.hogent_mobielplus_project.network.ShopEndpoint
import be.timdesmet.hogent_mobielplus_project.network.presistence.LocalDB
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class BannerRepository(private val database: LocalDB) {

    val banner: LiveData<Banner> = Transformations.map(database.bannerDAO.getBanner()) {it?.toDomain()}

    suspend fun refreshBanner() {
        withContext(Dispatchers.IO) {
            val response = JetstaxApi.buildApi(ShopEndpoint::class.java).getBanner()
            if(response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    database.bannerDAO.insertBanner(body.toDBO())
                }
            }
            else {
                throw Exception("${response.code()}: ${response.message()}")
            }
        }
    }
}