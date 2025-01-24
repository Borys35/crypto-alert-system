package pl.boryskaczmarek.crypto_alert_system.controller

import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.web.bind.annotation.*
import pl.boryskaczmarek.crypto_alert_system.model.Alert
import pl.boryskaczmarek.crypto_alert_system.model.dto.AlertDto
import pl.boryskaczmarek.crypto_alert_system.service.AlertService
import pl.boryskaczmarek.crypto_alert_system.service.UserService

@RestController
@RequestMapping("/api/alerts")
class AlertController(
    private val alertService: AlertService
) {
    @GetMapping("/me")
    fun getAlertsByUserId(@AuthenticationPrincipal user: OAuth2User?): List<Alert> {
        return alertService.findByUserId(user!!.attributes["id"] as Int)
    }

    @PostMapping("/create")
    fun createAlert(@AuthenticationPrincipal user: OAuth2User?, @RequestBody alertDto: AlertDto): Alert {
        return alertService.save(alertDto, user!!.attributes["id"] as Int)
    }
}