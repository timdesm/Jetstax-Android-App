package be.timdesmet.hogent_mobielplus_project.ui.viewmodels.shop

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import be.timdesmet.hogent_mobielplus_project.domain.shop.Banner
import be.timdesmet.hogent_mobielplus_project.domain.shop.ProductGroup
import be.timdesmet.hogent_mobielplus_project.network.presistence.getDatabase
import be.timdesmet.hogent_mobielplus_project.network.presistence.repositories.BannerRepository
import be.timdesmet.hogent_mobielplus_project.network.presistence.repositories.ProductGroupRepository
import be.timdesmet.hogent_mobielplus_project.utils.NetworkUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import java.lang.Exception

class HomeViewModel(private val app: Application) : ViewModel() {

    private val viewModalJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(viewModalJob + Dispatchers.Main)

    private val database by lazy { getDatabase(app) }
    private val bannerRepository by lazy { BannerRepository(database) }
    private val productGroupRepository by lazy { ProductGroupRepository(database) }

    val banner: LiveData<Banner> get() = bannerRepository.banner
    val productGroups: LiveData<List<ProductGroup>> get() = productGroupRepository.groups

    init {
        if(NetworkUtil.isConnected(app)) {
            loadProductGroups()
            loadBanner()
        }
    }

    private fun loadProductGroups() {
        viewModelScope.launch {
            try {
                productGroupRepository.refreshGroups()
            } catch (e: Exception) {
                e.printStackTrace()
                if(e.message == "timeout") loadProductGroups()
                Toast.makeText(app, "${e.message}", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun loadBanner() {
        viewModelScope.launch {
            try {
                bannerRepository.refreshBanner()
            }catch (e: Exception) {
                e.printStackTrace()
                if(e.message == "timeout") loadProductGroups()
                Toast.makeText(app, "${e.message}", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModalJob.cancel()
    }
}