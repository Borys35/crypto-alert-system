package pl.boryskaczmarek.crypto_alert_system.dto

import java.math.BigDecimal

data class CryptoData(
    val data: Map<String, Map<String, BigDecimal>>
)