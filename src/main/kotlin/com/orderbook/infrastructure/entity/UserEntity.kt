package com.orderbook.infrastructure.entity

import com.orderbook.domain.entities.User
import com.orderbook.domain.entities.Wallet
import jakarta.persistence.*

@Entity
@Table(name = "users")
data class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @Column
    val name: String,

    @Column
    @OneToOne
    val wallet: Wallet
) {
    fun toDomain(): User {
        return User(
            id = this.id,
            name = this.name,
            wallet = this.wallet
        )
    }

    companion object {
        fun fromDomain(user: User): UserEntity {
            return UserEntity(
                id = user.id!!,
                name = user.name,
                wallet = user.wallet
            )
        }
    }
}