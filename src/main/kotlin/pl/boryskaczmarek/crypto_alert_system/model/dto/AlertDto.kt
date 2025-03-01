package pl.boryskaczmarek.crypto_alert_system.model.dto

data class AlertDto(
    var ids: String = "",
    var vsCurrencies: String = "",
    var threshold: Float = 0.0f,
    var comparison: Char = '+', // "+" or "-"
)