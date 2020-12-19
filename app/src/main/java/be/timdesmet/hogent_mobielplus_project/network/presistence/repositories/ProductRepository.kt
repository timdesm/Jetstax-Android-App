package be.timdesmet.hogent_mobielplus_project.network.presistence.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import be.timdesmet.hogent_mobielplus_project.domain.shop.product.Product
import be.timdesmet.hogent_mobielplus_project.network.JetstaxApi
import be.timdesmet.hogent_mobielplus_project.network.ShopEndpoint
import be.timdesmet.hogent_mobielplus_project.network.presistence.LocalDB
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class ProductRepository(private val database: LocalDB) {

    val products: LiveData<List<Product>> = Transformations.map(database.productDAO.getProducts()) {it?.map { p -> p.toDomain() }}

    val selectedProducts = MutableLiveData<Product>()

    suspend fun refreshProducts() {
        withContext(Dispatchers.IO) {
            val response = JetstaxApi.buildApi(ShopEndpoint::class.java).getProducts()
            if(response.isSuccessful) {
                val products = response.body()
                products?.forEach {
                    database.productDAO.insertProduct(it.toDBO())
                }
            }
            else {
                throw Exception("${response.code()}: ${response.message()}")
            }
        }
    }

    suspend fun refreshProductsByGroup(gid: Int) {
        withContext(Dispatchers.Main) {
            val response = JetstaxApi.buildApi(ShopEndpoint::class.java).getGroupProducts(gid)
            if (response.isSuccessful) {
                val products = response.body()
                withContext(Dispatchers.IO) {
                    products?.forEach {
                        database.productDAO.insertProduct(it.toDBO())
                    }
                }
            }
        }
    }

}