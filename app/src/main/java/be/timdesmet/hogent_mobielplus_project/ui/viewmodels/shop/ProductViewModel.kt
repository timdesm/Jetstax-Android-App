package be.timdesmet.hogent_mobielplus_project.ui.viewmodels.shop

import android.app.Application
import androidx.lifecycle.ViewModel
import be.timdesmet.hogent_mobielplus_project.domain.shop.ProductGroup
import be.timdesmet.hogent_mobielplus_project.network.presistence.getDatabase
import be.timdesmet.hogent_mobielplus_project.network.presistence.repositories.ProductGroupRepository
import be.timdesmet.hogent_mobielplus_project.network.presistence.repositories.ProductRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

class ProductViewModel(private val app: Application, group: ProductGroup) : ViewModel() {

    private val viewModalJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(viewModalJob + Dispatchers.Main)

    private val database by lazy { getDatabase(app) }
    private val productGroupRepository by lazy { ProductGroupRepository(database) }
    private val productRepository by lazy { ProductRepository(database) }


    init {

    }

    override fun onCleared() {
        super.onCleared()
        viewModalJob.cancel()
    }
}