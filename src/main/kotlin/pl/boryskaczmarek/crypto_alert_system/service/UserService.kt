package pl.boryskaczmarek.crypto_alert_system.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import pl.boryskaczmarek.crypto_alert_system.model.User
import pl.boryskaczmarek.crypto_alert_system.repository.UserRepository
import java.util.*

@Service
class UserService(val userRepository: UserRepository) {
    fun findByUsername(username: String): Optional<User> {
        return userRepository.findByUsername(username)
    }

    fun save(user: User): User {
        return userRepository.save(user)
    }
}