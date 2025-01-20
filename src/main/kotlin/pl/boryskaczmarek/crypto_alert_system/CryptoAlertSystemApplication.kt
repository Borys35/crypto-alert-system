package pl.boryskaczmarek.crypto_alert_system

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping

@SpringBootApplication
class CryptoAlertSystemApplication

fun main(args: Array<String>) {
	runApplication<CryptoAlertSystemApplication>(*args)
}