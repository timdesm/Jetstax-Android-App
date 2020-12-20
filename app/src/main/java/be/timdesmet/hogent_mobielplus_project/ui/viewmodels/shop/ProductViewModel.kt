package be.timdesmet.hogent_mobielplus_project.ui.viewmodels.shop

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import be.timdesmet.hogent_mobielplus_project.domain.shop.ProductGroup
import be.timdesmet.hogent_mobielplus_project.domain.shop.product.Product
import be.timdesmet.hogent_mobielplus_project.network.presistence.getDatabase
import be.timdesmet.hogent_mobielplus_project.network.presistence.repositories.ProductGroupRepository
import be.timdesmet.hogent_mobielplus_project.network.presistence.repositories.ProductRepository
import kotlinx.coroutines.*
import java.lang.Exception

class ProductViewModel(private val app: Application, product: Product) : ViewModel() {

    private val viewModalJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(viewModalJob + Dispatchers.Main)

    private val database by lazy { getDatabase(app) }
    private val productGroupRepository by lazy { ProductGroupRepository(database) }
    private val productRepository by lazy { ProductRepository(database) }

    private val _group = MutableLiveData<ProductGroup>()
    val group: LiveData<ProductGroup> get() = _group

    private val _product = MutableLiveData<Product>()
    val product: LiveData<Product> get() = _product

    init {
        _product.value = product

        viewModelScope.launch {
            try {
                val group = productGroupRepository.getGroup(product.gid)
                group.observeForever {
                    _group.value = group.value
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
