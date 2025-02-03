package pl.boryskaczmarek.crypto_alert_system.model

import jakarta.persistence.*
import org.hibernate.proxy.HibernateProxy
import org.jetbrains.annotations.NotNull
import java.time.LocalDateTime

@Entity
@Table(name = "alert")
data class Alert(
    @Id @GeneratedValue var id: Long? = null,
    @ManyToOne @JoinColumn(name = "user_id", nullable = false) var user: User,
    @NotNull var lastSent: LocalDateTime = LocalDateTime.MIN,
    @NotNull var ids: String = "bitcoin",
    @NotNull var vsCurrencies: String = "usd",
    @NotNull var threshold: Float = 0.0f,
    @NotNull var comparison: Char = '+', // "+" or "-"
) {
    final override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null) return false
        val oEffectiveClass =
            if (other is HibernateProxy) other.hibernateLazyInitializer.persistentClass else other.javaClass
        val thisEffectiveClass =
            if (this is HibernateProxy) this.hibernateLazyInitializer.persistentClass else this.javaClass
        if (thisEffectiveClass != oEffectiveClass) return false
        other as Alert

        return id != null && id == other.id
    }

    final override fun hashCode(): Int =
        if (this is HibernateProxy) this.hibernateLazyInitializer.persistentClass.hashCode() else javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(  id = $id )"
    }
}