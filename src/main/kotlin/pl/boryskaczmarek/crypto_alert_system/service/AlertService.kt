package pl.boryskaczmarek.crypto_alert_system.service

import org.springframework.stereotype.Service
import pl.boryskaczmarek.crypto_alert_system.model.Alert
import pl.boryskaczmarek.crypto_alert_system.model.dto.AlertDto
import pl.boryskaczmarek.crypto_alert_system.repository.AlertRepository
import java.util.*

@Service
class AlertService(
    val alertRepository: AlertRepository,
    private val userService: UserService
) {
    fun findByUserId(userId: Int): List<Alert> {
        return alertRepository.findByUserId(userId)
    }

    fun save(alertDto: AlertDto, userId: Int): Alert {
        val user = userService.findById(userId)
        if (user.isEmpty)
            throw RuntimeException("User $userId does not exist")
        val alert = Alert(
            null,
            user.get(),
            alertDto.ids,
            alertDto.vsCurrencies,
            alertDto.threshold,
            alertDto.comparison,
        )
        return alertRepository.save(alert)
    }

    fun findById(id: Long): Optional<Alert> {
        return alertRepository.findById(id)
    }
}