package pl.boryskaczmarek.crypto_alert_system

import org.springframework.boot.fromApplication
import org.springframework.boot.with


fun main(args: Array<String>) {
	fromApplication<CryptoAlertSystemApplication>().with(TestcontainersConfiguration::class).run(*args)
}
