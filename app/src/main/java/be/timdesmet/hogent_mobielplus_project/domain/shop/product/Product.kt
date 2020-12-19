package be.timdesmet.hogent_mobielplus_project.domain.shop.product

import java.io.Serializable

class Product : Serializable {
    constructor(pid: Int, gid: Int, type: String, name: String, description: String, module: String, paytype: String, pricing: ProductPricing) {
        this.pid = pid
        this.gid = gid
        this.type = type
        this.name = name
        this.description = description
        this.module = module
        this.paytype = paytype
        this.pricing = pricing
    }

    var pid: Int
    var gid: Int
    var type: String
    var name: String
    var description: String
    var module: String
    var paytype: String
    val pricing: ProductPricing
}