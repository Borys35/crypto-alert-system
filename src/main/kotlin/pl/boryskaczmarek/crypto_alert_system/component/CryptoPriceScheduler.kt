package pl.boryskaczmarek.crypto_alert_system.component

import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import pl.boryskaczmarek.crypto_alert_system.service.CryptoPriceFetcher
import pl.boryskaczmarek.crypto_alert_system.service.KafkaProducerService

@Component
class CryptoPriceScheduler(
    private val cryptoPriceFetcher: CryptoPriceFetcher,
    private val kafkaProducerService: KafkaProducerService
) {
    @Scheduled(fixedRate = 180_000) // run every 3 minutes
    fun schedulePriceUpdates() {
        val priceData = cryptoPriceFetcher.fetchPrices("bitcoin,ethereum,solana", "usd,pln")
        kafkaProducerService.sendPriceUpdate(priceData)
    }
}