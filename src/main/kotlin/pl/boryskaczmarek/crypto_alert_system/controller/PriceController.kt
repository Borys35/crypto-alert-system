package pl.boryskaczmarek.crypto_alert_system.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import pl.boryskaczmarek.crypto_alert_system.dto.CryptoData
import pl.boryskaczmarek.crypto_alert_system.service.CryptoPriceFetcher

@RestController
@RequestMapping("/api/prices")
class PriceController {
    private val cryptoPriceFetcher = CryptoPriceFetcher()

    @GetMapping
    fun getPrices(
        @RequestParam ids: String,
        @RequestParam(name = "vs_currencies") vsCurrencies: String
    ): CryptoData {
        val prices = cryptoPriceFetcher.fetchPrices(ids, vsCurrencies)
        return prices
    }
}