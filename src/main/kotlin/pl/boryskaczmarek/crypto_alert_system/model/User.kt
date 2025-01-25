package pl.boryskaczmarek.crypto_alert_system.model

import jakarta.persistence.*
import org.hibernate.proxy.HibernateProxy

@Entity
@Table(name = "app_user")
data class User(
    @Id var id : Int,
    var username: String,
    var email: String,
    var avatarUrl: String? = "https://gravatar.com/avatar/${username}?s=400&d=retro&r=x",
    @OneToMany(mappedBy = "user") private val alerts: Set<Alert> = HashSet()
) {


    final override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null) return false
        val oEffectiveClass =
            if (other is HibernateProxy) other.hibernateLazyInitializer.persistentClass else other.javaClass
        val thisEffectiveClass =
            if (this is HibernateProxy) this.hibernateLazyInitializer.persistentClass else this.javaClass
        if (thisEffectiveClass != oEffectiveClass) return false
        other as User

        return id != null && id == other.id
    }

    final override fun hashCode(): Int =
        if (this is HibernateProxy) this.hibernateLazyInitializer.persistentClass.hashCode() else javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(  id = $id   ,   username = $username )"
    }
}