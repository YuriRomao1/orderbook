package com.orderbook.infrastructure.entity

import com.orderbook.domain.entities.Transaction
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "transactions")
data class TransactionEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: String,
    val buyOrderId: String?,
    val sellOrderId: String?,
    val quantity: Double,
    val price: Double,
    val timestamp: LocalDateTime
) {

    companion object {
        fun fromDomain(transaction: Transaction): TransactionEntity {
            return TransactionEntity(
                id = transaction.id,
                buyOrderId = transaction.buyOrderEntity,
                sellOrderId = transaction.sellOrderEntity,
                quantity = transaction.quantity,
                price = transaction.price,
                timestamp = transaction.timestamp
            )
        }
    }
}