package pl.boryskaczmarek.crypto_alert_system.controller

import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@Controller
class HomeController {

    @GetMapping("/")
    fun home(@AuthenticationPrincipal user: OAuth2User?, model: Model): String {
        model.addAttribute("user", user)
        return "home"
    }
}