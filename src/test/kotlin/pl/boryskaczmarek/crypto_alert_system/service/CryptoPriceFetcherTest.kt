package pl.boryskaczmarek.crypto_alert_system.service

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import java.math.BigDecimal

class CryptoPriceFetcherTest {

    private val cryptoPriceFetcher = mock(CryptoPriceFetcher::class.java)
    private val prices = HashMap<String, HashMap<String, Double>>()

    @BeforeEach
    fun setUp() {
        prices["bitcoin"] = HashMap()
        prices["bitcoin"]!!["usd"] = 106112.0
        prices["solana"] = HashMap()
        prices["solana"]!!["usd"] = 274.71

        Mockito.`when`(cryptoPriceFetcher.fetchPrices("bitcoin,solana", "usd")).thenReturn(prices)
    }

    @Test
    fun `should fetch prices`() {
        cryptoPriceFetcher.fetchPrices("bitcoin,solana", "usd")
        assert(prices["bitcoin"] != null)
        assert(prices["bitcoin"]!!["usd"] != null)
        assert(prices["bitcoin"]!!["usd"] == 106112.0)
    }
}