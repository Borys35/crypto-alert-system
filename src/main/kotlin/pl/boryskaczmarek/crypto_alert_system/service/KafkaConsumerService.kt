package pl.boryskaczmarek.crypto_alert_system.service

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.messaging.handler.annotation.Header
import org.springframework.stereotype.Service

@Service
class KafkaConsumerService(private val mapper: ObjectMapper) {
    @KafkaListener(topics = ["crypto-prices"], groupId = "alert-group")
    fun processPriceUpdate(@Header(KafkaHeaders.RECEIVED_KEY) crypto: String, dataString: String) {
        /* {
            usd=104142,
            usd_24h_change=-1.685324100729804,
            pln=422205,
            pln_24h_change=-2.2962781286374043,
            last_updated_at=1737578525
        } */

        val pairs = dataString.removeSurrounding("{", "}").split(", ")
        val map = mutableMapOf<String, Any>()
        for (pair in pairs) {
            val (key, value) = pair.split("=")
            map[key] = when {
                value.toBigDecimal() != null -> value.toBigDecimal() // Handle numeric values
                value.toIntOrNull() != null -> value.toInt() // Handle integer values
                else -> value // Treat as string
            }
        }

        // TODO: Send email to a user if usd_24h_change is over a threshold
        // TODO: Create 'Alert' model to the database

        println("Alert: $crypto at price: ${map.toString()}")
    }
}