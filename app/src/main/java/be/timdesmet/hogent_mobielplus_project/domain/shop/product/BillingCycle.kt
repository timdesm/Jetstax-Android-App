package be.timdesmet.hogent_mobielplus_project.domain.shop.product

enum class BillingCycle {
    MONTHLY {
        override fun getDisplayName()  = "Monthly"
        override fun getShortened() = "Mo"
    },
    QUARTERLY {
        override fun getDisplayName()  = "Quarterly"
        override fun getShortened() = "Mo"
    },
    SEMIANNUALLY {
        override fun getDisplayName()  = "Semi-Annually"
        override fun getShortened() = "Yr"
    },
    ANNUALLY {
        override fun getDisplayName()  = "Annually"
        override fun getShortened() = "Yr"
    },
    BIENNIALLY {
        override fun getDisplayName()  = "Biennially"
        override fun getShortened() = "Yr"
    },
    TRIENNIALLY {
        override fun getDisplayName()  = "Triennially"
        override fun getShortened() = "Yr"
    },
    FREE {
        override fun getDisplayName()  = "Free"
        override fun getShortened() = "Free"
    };

    abstract fun getDisplayName() : String
    abstract fun getShortened() : String
}
