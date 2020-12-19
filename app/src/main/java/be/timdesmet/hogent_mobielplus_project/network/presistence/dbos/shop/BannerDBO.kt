package be.timdesmet.hogent_mobielplus_project.network.presistence.dbos.shop

import androidx.room.Entity
import androidx.room.PrimaryKey
import be.timdesmet.hogent_mobielplus_project.domain.shop.Banner

@Entity(tableName = "banners")
class BannerDBO constructor(
    @PrimaryKey
    val id: Int,
    val title: String,
    val subtitle: String,
    val image: String) {

    fun toDomain() : Banner {
        return Banner(id, title, subtitle, image)
    }
}

