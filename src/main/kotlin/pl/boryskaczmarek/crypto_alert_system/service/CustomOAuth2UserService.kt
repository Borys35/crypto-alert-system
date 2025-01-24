package pl.boryskaczmarek.crypto_alert_system.service

import org.springframework.boot.autoconfigure.security.SecurityProperties
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest
import org.springframework.security.oauth2.core.user.OAuth2User
import pl.boryskaczmarek.crypto_alert_system.model.User

class CustomOAuth2UserService(private val userService: UserService) : DefaultOAuth2UserService() {
    override fun loadUser(userRequest: OAuth2UserRequest): OAuth2User {
        val oAuth2User = super.loadUser(userRequest)

        val user = User(oAuth2User.attributes["login"] as String, oAuth2User.attributes["avatar_url"] as String, null)
        println(userService.save(user))

        return oAuth2User
    }
}