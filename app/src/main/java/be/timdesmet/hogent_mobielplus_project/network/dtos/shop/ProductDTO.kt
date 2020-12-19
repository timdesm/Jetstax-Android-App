package be.timdesmet.hogent_mobielplus_project.network.dtos.shop

import be.timdesmet.hogent_mobielplus_project.domain.shop.product.Product
import be.timdesmet.hogent_mobielplus_project.domain.shop.product.ProductPricing
import be.timdesmet.hogent_mobielplus_project.domain.shop.product.ProductPricingCurrency
import be.timdesmet.hogent_mobielplus_project.network.presistence.dbos.shop.product.ProductDBO
import com.google.gson.Gson
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProductDTO (
    @Json(name = "pid") val pid: Int,
    @Json(name = "gid") val gid: Int,
    @Json(name = "type") val type: String,
    @Json(name = "name") val name: String,
    @Json(name = "description") val description: String,
    @Json(name = "module") val module: String,
    @Json(name = "paytype") val paytype: String,
    @Json(name = "pricing") val pricing: ProductPricingDTO) {

    fun toDomain() : Product {
        return Product(pid, gid, type, name, description, module, paytype, pricing.toDomain())
    }

    fun toDBO() : ProductDBO {
        return ProductDBO(pid, gid, type, name, description, module, paytype, Gson().toJson(pricing.toDomain()))
    }
}

@JsonClass(generateAdapter = true)
data class ProductPricingDTO(
    @Json(name = "CAD") val cad: ProductPricingCurrencyDTO,
    @Json(name = "EUR") val eur: ProductPricingCurrencyDTO,
    @Json(name = "GBP") val gbp: ProductPricingCurrencyDTO,
    @Json(name = "USD") val usd: ProductPricingCurrencyDTO
) {

    fun toDomain() : ProductPricing {
        return ProductPricing(cad.toDomain(), eur.toDomain(), gbp.toDomain(), usd.toDomain())
    }
}

@JsonClass(generateAdapter = true)
data class ProductPricingCurrencyDTO(
    @Json(name = "annually") val annually: String,
    @Json(name = "asetupfee") val asetupfee: String,
    @Json(name = "biennially") val biennially: String,
    @Json(name = "bsetupfee") val bsetupfee: String,
    @Json(name = "monthly") val monthly: String,
    @Json(name = "msetupfee") val msetupfee: String,
    @Json(name = "prefix") val prefix: String,
    @Json(name = "qsetupfee") val qsetupfee: String,
    @Json(name = "quarterly") val quarterly: String,
    @Json(name = "semiannually") val semiannually: String,
    @Json(name = "ssetupfee") val ssetupfee: String,
    @Json(name = "suffix") val suffix: String,
    @Json(name = "triennially") val triennially: String,
    @Json(name = "tsetupfee") val tsetupfee: String
) {

    fun toDomain() : ProductPricingCurrency {
        return ProductPricingCurrency(annually, asetupfee, biennially, bsetupfee, monthly, msetupfee, prefix, qsetupfee, quarterly, semiannually, ssetupfee, suffix, triennially, tsetupfee)
    }

}
