package pl.boryskaczmarek.crypto_alert_system.controller

import io.opentelemetry.api.OpenTelemetry
import io.opentelemetry.api.metrics.Meter
import io.opentelemetry.api.trace.Tracer
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import pl.boryskaczmarek.crypto_alert_system.model.dto.AlertDto

@Controller
class HomeController(
    private val openTelemetry: OpenTelemetry,
) {
    private val tracer: Tracer = openTelemetry.getTracer("application")
    private val meter: Meter = openTelemetry.getMeter("application")

    @GetMapping("/")
    fun home(@AuthenticationPrincipal user: OAuth2User?, model: Model): String {
        model.addAttribute("user", user)
        return "home"
    }

    @GetMapping("/alerts")
    fun alerts(@AuthenticationPrincipal user: OAuth2User?, model: Model): String {
        model.addAttribute("user", user)
        model.addAttribute("alertDto", AlertDto())
        return "alerts"
    }
}