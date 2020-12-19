package be.timdesmet.hogent_mobielplus_project.network.dtos.shop

import be.timdesmet.hogent_mobielplus_project.domain.shop.ProductGroup
import be.timdesmet.hogent_mobielplus_project.network.presistence.dbos.shop.BannerDBO
import be.timdesmet.hogent_mobielplus_project.network.presistence.dbos.shop.ProductGroupDBO
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProductGroupDTO (
    @Json(name = "id") val id: Int,
    @Json(name = "name") val name: String,
    @Json(name = "description") val description: String,
    @Json(name = "image") val image: String,
    @Json(name = "active") val active: Boolean,
    @Json(name = "weight") val weight: Int) {

    fun toDomain() : ProductGroup {
        return ProductGroup(id, name, description, image, active, weight)
    }

    fun toDBO() : ProductGroupDBO {
        return ProductGroupDBO(id, name, description, image, active, weight)
    }
}