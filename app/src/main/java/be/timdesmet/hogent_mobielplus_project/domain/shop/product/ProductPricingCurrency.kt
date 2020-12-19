package be.timdesmet.hogent_mobielplus_project.domain.shop.product

import java.io.Serializable

class ProductPricingCurrency : Serializable {
    constructor(annually: String,
                asetupfee: String,
                biennially: String,
                bsetupfee: String,
                monthly: String,
                msetupfee: String,
                prefix: String,
                qsetupfee: String,
                quarterly: String,
                semiannually: String,
                ssetupfee: String,
                suffix: String,
                triennially: String,
                tsetupfee: String) {
        this.annually = annually
        this.asetupfee = asetupfee
        this.biennially = biennially
        this.bsetupfee = bsetupfee
        this.monthly = monthly
        this.msetupfee = msetupfee
        this.prefix = prefix
        this.qsetupfee = qsetupfee
        this.quarterly = quarterly
        this.semiannually = semiannually
        this.ssetupfee = ssetupfee
        this.suffix = suffix
        this.triennially = triennially
        this.tsetupfee = tsetupfee
    }

    var annually: String
    var asetupfee: String
    var biennially: String
    var bsetupfee: String
    var monthly: String
    var msetupfee: String
    var prefix: String
    var qsetupfee: String
    var quarterly: String
    var semiannually: String
    var ssetupfee: String
    var suffix: String
    var triennially: String
    var tsetupfee: String
}