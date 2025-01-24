package pl.boryskaczmarek.crypto_alert_system.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import pl.boryskaczmarek.crypto_alert_system.model.User
import java.util.*

@Repository
interface UserRepository : JpaRepository<User, Long> {
    fun findByUsername(username: String): Optional<User>
}