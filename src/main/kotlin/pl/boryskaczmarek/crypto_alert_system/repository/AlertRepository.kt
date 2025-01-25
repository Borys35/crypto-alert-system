package pl.boryskaczmarek.crypto_alert_system.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import pl.boryskaczmarek.crypto_alert_system.model.Alert

@Repository
interface AlertRepository : JpaRepository<Alert, Long> {
    fun findByUserId(userId: Int): List<Alert>

    fun findByIdsContains(ids: String): List<Alert>
}