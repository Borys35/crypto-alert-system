package pl.boryskaczmarek.crypto_alert_system.service

import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.messaging.handler.annotation.Header
import org.springframework.stereotype.Service

@Service
class KafkaConsumerService {
    @KafkaListener(topics = ["crypto-prices"], groupId = "alert-group")
    fun processPriceUpdate(@Header(KafkaHeaders.RECEIVED_KEY) crypto: String, price: String) {
        println("Alert: $crypto at price: $price")
    }
}