package pl.boryskaczmarek.crypto_alert_system.service

import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.messaging.handler.annotation.Header
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.time.LocalDateTime

@Service
class KafkaConsumerService(
    private val mapper: ObjectMapper,
    private val alertService: AlertService,
    private val emailService: EmailService
) {
    private val logger: Logger = LoggerFactory.getLogger(KafkaConsumerService::class.java)

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
                value.toDoubleOrNull() != null -> value.toDouble() // Handle numeric values
                value.toIntOrNull() != null -> value.toInt() // Handle integer values
                else -> value // Treat as string
            }
        }

        // TODO: Send email to a user if usd_24h_change is over a threshold based on 'Alert'
        val alerts = alertService.findByIdsContains(crypto)
        for (alert in alerts) {
            logger.info("Sending $crypto alert to: ${alert.id}")
            if (alert.lastSent.isBefore(LocalDateTime.now().minusDays(1))) {
                if ((alert.comparison == '+' && (map["usd_24h_change"] as Double).toFloat() >= alert.threshold)
                    || (alert.comparison == '-' && (map["usd_24h_change"] as Double).toFloat() <= alert.threshold)
                ) {
                    alertService.updateLastSentDate(alert.id!!, LocalDateTime.now())
                    emailService.sendEmail(
                        alert.user.email,
                        crypto,
                        map["usd_24h_change"].toString(),
                        map["usd"].toString()
                    )
                }
            }
        }
        logger.info("Alert: $crypto: ${map.toString()}")
    }
}