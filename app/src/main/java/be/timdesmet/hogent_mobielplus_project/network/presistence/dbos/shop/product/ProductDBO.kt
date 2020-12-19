package be.timdesmet.hogent_mobielplus_project.network.presistence.dbos.shop.product

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey
import be.timdesmet.hogent_mobielplus_project.domain.shop.product.Product
import be.timdesmet.hogent_mobielplus_project.domain.shop.product.ProductPricing
import be.timdesmet.hogent_mobielplus_project.network.presistence.dbos.shop.ProductGroupDBO
import com.google.gson.Gson

@Entity(tableName = "products")
class ProductDBO constructor(
    @PrimaryKey
    val pid: Int,
    val gid: Int,
    val type: String,
    val name: String,
    val description: String,
    val module: String,
    val paytype: String,
    val pricing: String) {

    fun toDomain() : Product {
        return Product(pid, gid, type, name, description, module, paytype, Gson().fromJson(pricing, ProductPricing::class.java))
    }
}