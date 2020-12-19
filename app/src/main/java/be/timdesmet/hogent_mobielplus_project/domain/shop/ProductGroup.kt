package be.timdesmet.hogent_mobielplus_project.domain.shop

import be.timdesmet.hogent_mobielplus_project.domain.shop.product.Product
import java.io.Serializable

class ProductGroup : Serializable {
    constructor(id: Int, name: String, description: String, image: String, active: Boolean, weight: Int) {
        this.id = id
        this.name = name
        this.description = description
        this.image = image
        this.active = active
        this.weight = weight
    }

    var id: Int
    var name: String
    var description: String
    var image: String
    var active: Boolean
    var weight: Int

    private var _products = mutableListOf<Product>()
    val products: List<Product> get() = _products

    fun loadProducts(products: List<Product>) : ProductGroup {
        _products = products as MutableList<Product>
        return this
    }
}