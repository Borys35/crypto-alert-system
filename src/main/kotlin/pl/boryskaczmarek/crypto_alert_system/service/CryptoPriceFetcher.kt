package pl.boryskaczmarek.crypto_alert_system.service

import org.springframework.http.*
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import pl.boryskaczmarek.crypto_alert_system.model.dto.CryptoData
import java.math.BigDecimal

@Service
class CryptoPriceFetcher() {

    // private val apiKey = System.getenv("COINGECKO_API_KEY")
    private val restTemplate = RestTemplate()

    fun fetchPrices(ids: String, vsCurrencies: String): CryptoData {
        val headers: HttpHeaders = HttpHeaders()
        headers.set("Accept", "application/json")
        // headers.set("x-cg-demo-api-key", apiKey)

        val entity = HttpEntity<String>(headers)

        val response: ResponseEntity<Map<*, *>> = restTemplate
            .exchange("https://api.coingecko.com/api/v3/simple/price?ids=${ids}&vs_currencies=${vsCurrencies}&include_last_updated_at=true&include_24hr_change=true", HttpMethod.GET, entity, Map::class.java)

        if (response.body == null && response.body is Map<*, *>)
            throw RuntimeException("Response body is null")

        val priceData = CryptoData(response.body as Map<String, Map<String, BigDecimal>>)
        return priceData
    }
}