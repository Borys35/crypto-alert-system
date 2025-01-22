package pl.boryskaczmarek.crypto_alert_system.controller

import org.springframework.data.repository.query.Param
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import pl.boryskaczmarek.crypto_alert_system.dto.PriceData
import pl.boryskaczmarek.crypto_alert_system.service.CryptoPriceFetcher
import java.math.BigDecimal

@RestController
@RequestMapping("/api/prices")
class PriceController {
    private val cryptoPriceFetcher = CryptoPriceFetcher()

    @GetMapping
    fun getPrices(
        @RequestParam ids: String,
        @RequestParam(name = "vs_currencies") vsCurrencies: String
    ): PriceData {
        val prices = cryptoPriceFetcher.fetchPrices(ids, vsCurrencies)
        return prices
    }
}