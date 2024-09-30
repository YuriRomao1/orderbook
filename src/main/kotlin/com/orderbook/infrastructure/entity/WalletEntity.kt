package com.orderbook.infrastructure.entity

import com.orderbook.domain.entities.Wallet
import jakarta.persistence.*

@Entity
@Table(name = "wallets")
data class WalletEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val userId: String,
    val balance: Double,
    var btcCoin: Double,
) {
    fun toDomain(): Wallet {
        return Wallet(
            userId = this.userId,
            balance = this.balance,
            btcCoin = this.btcCoin
        )
    }

    companion object {
        fun fromDomain(wallet: Wallet): WalletEntity {
            return WalletEntity(
                userId = wallet.userId,
                balance = wallet.balance,
                btcCoin = wallet.btcCoin
            )
        }
    }
}