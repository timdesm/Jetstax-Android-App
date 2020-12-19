package be.timdesmet.hogent_mobielplus_project.domain.shop

import java.io.Serializable

class Banner : Serializable {
    constructor(id: Int, title: String, subtitle: String, image: String) {
        this.id = id
        this.title = title
        this.subtitle = subtitle
        this.image = image
    }

    var id: Int
    var title: String
    var subtitle: String
    var image: String
}