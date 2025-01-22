package pl.boryskaczmarek.crypto_alert_system.config

import org.apache.kafka.clients.admin.NewTopic
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class KafkaTopicConfig {
    @Bean
    fun cryptoPrices(): NewTopic {
        return NewTopic("crypto-prices", 1, 1)
    }
}