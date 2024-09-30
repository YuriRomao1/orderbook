package com.orderbook.infrastructure.entity

import com.orderbook.domain.entities.Order
import com.orderbook.domain.entities.enum.OrderType
import jakarta.persistence.*

@Entity
@Table(name = "orders")
data class OrderEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: String? = null,
    val userId: String,
    @Enumerated(EnumType.STRING)
    val type: OrderType,
    val quantity: Double,
    val price: Double
) {
    fun toDomain(): Order {
        return Order(
            id = this.id,
            userId = this.userId,
            type = this.type,
            quantity = this.quantity,
            price = this.price
        )
    }

    companion object {
        fun fromDomain(order: Order): OrderEntity {
            return OrderEntity(
                id = order.id,
                userId = order.userId,
                type = order.type,
                quantity = order.quantity,
                price = order.price
            )
        }
    }
}