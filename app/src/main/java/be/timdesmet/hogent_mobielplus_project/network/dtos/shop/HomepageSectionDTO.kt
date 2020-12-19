package be.timdesmet.hogent_mobielplus_project.network.dtos.shop

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class HomepageSectionDTO (
    @Json(name = "title") val title: String,
    @Json(name = "subtitle") val subtitle: String,
    @Json(name = "image") val image: String) {

}