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

    fun getPricing(currency: String): ProductPricingCurrency {
        when (currency) {
            "cad" -> return this.cad
            "eur" -> return this.eur
            "gbp" -> return this.gbp
            "usd" -> return this.gbp
        }
        return this.eur
    }

    fun getDisplayPrice(currency: String): Price {
        val prices = getPricing(currency).getPriceList()
        prices.forEach {
            if(it.returning >= 0.0) return it
        }
        return Price(0.0, 0.0, "", "", BillingCycle.FREE)
    }
}