package be.timdesmet.hogent_mobielplus_project.domain.shop.product

import java.io.Serializable

class ProductPricing : Serializable {
    constructor(cad: ProductPricingCurrency, eur: ProductPricingCurrency, gbp: ProductPricingCurrency, usd: ProductPricingCurrency) {
        this.cad = cad
        this.eur = eur
        this.gbp = gbp
        this.usd = usd
    }

    var cad: ProductPricingCurrency
    var eur: ProductPricingCurrency
    var gbp: ProductPricingCurrency
    var usd: ProductPricingCurrency

    fun getPrice(currency: String): String {
        return "0.00"
    }

    fun getFormattedPrice(currency: String): String {
        return getPrice(currency)
    }
}