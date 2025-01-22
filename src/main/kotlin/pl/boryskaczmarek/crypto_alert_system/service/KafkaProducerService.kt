package pl.boryskaczmarek.crypto_alert_system.service

import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service
import pl.boryskaczmarek.crypto_alert_system.dto.PriceData

@Service
class KafkaProducerService(private val kafkaTemplate: KafkaTemplate<String, String>) {
    fun sendPriceUpdate(priceData: PriceData) {
        priceData.data.forEach {
            (crypto, data) ->
                kafkaTemplate.send("crypto-prices", crypto, data.toString())
        }
    }
}