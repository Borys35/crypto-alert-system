package pl.boryskaczmarek.crypto_alert_system.service

import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.*
import org.springframework.stereotype.Service
import org.springframework.web.client.RestClient
import org.springframework.web.client.RestTemplate
import org.springframework.web.servlet.function.ServerRequest.Headers
import pl.boryskaczmarek.crypto_alert_system.dto.PriceDto
import java.awt.PageAttributes
import java.math.BigDecimal
import java.net.http.HttpClient

@Service
class CryptoPriceFetcher() {

    private val apiKey = System.getenv("COINGECKO_API_KEY")
    private val restTemplate = RestTemplate()

    fun fetchPrices(ids: String, vs_currencies: String): Map<String, Map<String, Double>> {
        val headers: HttpHeaders = HttpHeaders()
        headers.set("Accept", "application/json")
        headers.set("x-cg-demo-api-key", apiKey)

        val entity = HttpEntity<String>(headers)

        val response: ResponseEntity<Map<*, *>> = restTemplate
            .exchange("https://api.coingecko.com/api/v3/simple/price?ids=${ids}&vs_currencies=${vs_currencies}", HttpMethod.GET, entity, Map::class.java)

        println("hej")
        println(apiKey)

        if (response.body == null && response.body is Map<*, *>)
            throw RuntimeException("Response body is null")

        return response.body as Map<String, Map<String, Double>>
    }
}