package be.timdesmet.hogent_mobielplus_project.network.dtos.shop

import be.timdesmet.hogent_mobielplus_project.domain.shop.Banner
import be.timdesmet.hogent_mobielplus_project.network.presistence.dbos.shop.BannerDBO
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BannerDTO (
    @Json(name = "id") val id: Int,
    @Json(name = "title") val title: String,
    @Json(name = "subtitle") val subtitle: String,
    @Json(name = "image") val image: String) {

    fun toDomain() : Banner {
        return Banner(id, title, subtitle, image)
    }

    fun toDBO() : BannerDBO {
        return BannerDBO(id, title, subtitle, image)
    }
}