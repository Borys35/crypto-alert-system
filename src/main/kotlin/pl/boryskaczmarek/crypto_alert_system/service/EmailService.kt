package pl.boryskaczmarek.crypto_alert_system.service

import jakarta.mail.Message
import jakarta.mail.internet.InternetAddress
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class EmailService(private val javaMailSender: JavaMailSender) {
    fun sendEmail(email: String, crypto: String, change: String, price: String) {
        val message = javaMailSender.createMimeMessage()

        message.setFrom("boryskac10@gmail.com")
        message.setRecipient(Message.RecipientType.TO, InternetAddress(email))
        message.subject = "Threshold on ${crypto.uppercase()} has surpassed"
        message.setText("""
            Hello!
            ${crypto.uppercase()} has surpassed the threshold.
            ${crypto.uppercase()} changed by ${change}% and costs ${price}.
            
            If you want to unsubscribe from the alert, you cannot do that for now :)
        """.trimIndent())

        javaMailSender.send(message)
    }
}