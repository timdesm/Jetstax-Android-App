package be.timdesmet.hogent_mobielplus_project.domain.shop.product

import java.io.Serializable
import java.text.DecimalFormat

class Price : Serializable {
    constructor(returning: Double, setup: Double, prefix: String, suffix: String, cycle: BillingCycle) {
        this.returning = returning
        this.setup = setup
        this.prefix = prefix
        this.suffix = suffix
        this.cycle = cycle
    }

    val returning: Double
    val setup: Double
    val prefix: String
    val suffix: String
    val cycle: BillingCycle

    fun formatRecurring() : String {
        val df = DecimalFormat("#,##0.00")
        if(cycle == BillingCycle.FREE) return cycle.getDisplayName()
        return this.prefix + df.format(this.returning) + this.suffix
    }

    fun formatSetup() : String {
        val df = DecimalFormat("#,##0.00")
        return this.prefix + df.format(this.setup) + this.suffix
    }
}
