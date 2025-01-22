package pl.boryskaczmarek.crypto_alert_system

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.web.bind.annotation.GetMapping

@SpringBootApplication
@EnableScheduling
class CryptoAlertSystemApplication

fun main(args: Array<String>) {
	runApplication<CryptoAlertSystemApplication>(*args)
}