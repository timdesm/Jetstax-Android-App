package be.timdesmet.hogent_mobielplus_project.network.presistence.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import be.timdesmet.hogent_mobielplus_project.domain.shop.ProductGroup
import be.timdesmet.hogent_mobielplus_project.domain.shop.product.Product
import be.timdesmet.hogent_mobielplus_project.network.JetstaxApi
import be.timdesmet.hogent_mobielplus_project.network.ShopEndpoint
import be.timdesmet.hogent_mobielplus_project.network.presistence.LocalDB
import be.timdesmet.hogent_mobielplus_project.network.presistence.dbos.shop.product.ProductDBO
import be.timdesmet.hogent_mobielplus_project.utils.NetworkUtil
import com.bumptech.glide.load.engine.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class ProductGroupRepository(private val database: LocalDB) {

    val groups: LiveData<List<ProductGroup>> = Transformations.map(database.productGroupDAO.getProductGroups()) {it?.map { g -> g.toDomain() }}

    var selectedGroup = MutableLiveData<ProductGroup>()

    suspend fun refreshGroups() {
        withContext(Dispatchers.IO) {
            val response = JetstaxApi.buildApi(ShopEndpoint::class.java).getProductGroups()
            if(response.isSuccessful) {
                val groups = response.body()
                groups?.forEach {
                    database.productGroupDAO.insertGroup(it.toDBO())
                }
            }
            else {
                throw Exception("${response.code()}: ${response.message()}")
            }
        }
    }

    fun loadGroup(id: Int) {
        selectedGroup.value = null
    }

    fun getGroup(id: Int) : LiveData<ProductGroup> {
        return Transformations.map(database.productGroupDAO.getProductGroup(id)) {it?.toDomain()}
    }

    fun getGroupProducts(id: Int) : LiveData<List<Product>> {
        return Transformations.map(database.productGroupDAO.getGroupProducts(id)) {it?.map { p -> p.toDomain() }}
    }
}