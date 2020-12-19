package be.timdesmet.hogent_mobielplus_project.network.presistence.dbos.shop

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import be.timdesmet.hogent_mobielplus_project.domain.shop.ProductGroup
import be.timdesmet.hogent_mobielplus_project.domain.shop.product.Product
import be.timdesmet.hogent_mobielplus_project.network.presistence.dbos.shop.product.ProductDBO

@Entity(tableName = "product_groups")
class ProductGroupDBO constructor(
    @PrimaryKey
    val id: Int,
    val name: String,
    val description: String,
    val image: String,
    val active: Boolean,
    val weight: Int) {

    fun toDomain() : ProductGroup {
        return ProductGroup(id, name, description, image, active, weight)
    }
}


class GroupWithProducts {
    @Embedded
    lateinit var group: ProductGroupDBO
    @Embedded
    lateinit var products: List<ProductDBO>

    fun toDomain() : ProductGroup {
        return ProductGroup(group.id, group.name, group.description, group.image, group.active, group.weight)
            .loadProducts(products.map { p -> p.toDomain() })
    }
}