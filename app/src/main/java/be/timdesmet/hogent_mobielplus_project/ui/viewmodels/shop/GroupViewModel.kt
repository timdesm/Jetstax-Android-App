package be.timdesmet.hogent_mobielplus_project.ui.viewmodels.shop

import android.app.Application
import android.net.Network
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import be.timdesmet.hogent_mobielplus_project.domain.shop.ProductGroup
import be.timdesmet.hogent_mobielplus_project.domain.shop.product.Product
import be.timdesmet.hogent_mobielplus_project.network.presistence.getDatabase
import be.timdesmet.hogent_mobielplus_project.network.presistence.repositories.ProductGroupRepository
import be.timdesmet.hogent_mobielplus_project.utils.NetworkUtil
import kotlinx.coroutines.*
import java.lang.Exception

class GroupViewModel(private val app: Application, group: ProductGroup) : ViewModel() {

    private val viewModalJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(viewModalJob + Dispatchers.Main)

    private val database by lazy { getDatabase(app) }
    private val productGroupRepository by lazy { ProductGroupRepository(database) }

    private val _group = MutableLiveData<ProductGroup>()
    val group: LiveData<ProductGroup> get() = _group

    private val _products = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>> get() = _products

    init {
        _group.value = group

        viewModelScope.launch {
            try {
                val products = productGroupRepository.getGroupProducts(group.id)
                products.observeForever {
                    _products.value = products.value
                }
            } catch (e: Exception){
                e.printStackTrace()
                Toast.makeText(app, "Error ${e.message}", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModalJob.cancel()
        viewModelScope.cancel()
    }
}