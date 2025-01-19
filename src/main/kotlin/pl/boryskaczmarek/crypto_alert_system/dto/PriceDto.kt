package pl.boryskaczmarek.crypto_alert_system.dto

import java.math.BigDecimal

data class PriceDto (
    private val currency: String,
    private val price: BigDecimal
)