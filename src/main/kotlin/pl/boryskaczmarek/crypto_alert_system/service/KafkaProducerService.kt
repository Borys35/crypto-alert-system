package pl.boryskaczmarek.crypto_alert_system.service

import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service
import pl.boryskaczmarek.crypto_alert_system.dto.CryptoData

@Service
class KafkaProducerService(private val kafkaTemplate: KafkaTemplate<String, String>) {
    fun sendPriceUpdate(priceData: CryptoData) {
        priceData.data.forEach {
            (crypto, data) ->
                kafkaTemplate.send("crypto-prices", crypto, data.toString())
        }
    }
}