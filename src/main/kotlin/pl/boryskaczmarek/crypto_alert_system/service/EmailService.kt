package pl.boryskaczmarek.crypto_alert_system.service

import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service

@Service
class EmailService(private val javaMailSender: JavaMailSender) {
    fun sendEmail(email: String) {
        val message = javaMailSender.createMimeMessage()

        message.setFrom("noreply@gmail.com")
        message to "email@gmail.com"
        message.subject = "Update on bitcoin"
        message.setText("Updated")

        javaMailSender.send(message)
    }
}