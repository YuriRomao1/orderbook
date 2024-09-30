package com.orderbook.infrastructure.repository

import com.orderbook.domain.entities.enum.OrderType
import com.orderbook.infrastructure.entity.OrderEntity
import org.springframework.data.jpa.repository.JpaRepository

interface OrderJpaRepository: JpaRepository<OrderEntity, String> {
    fun findFirstByTypeAndPrice(type: OrderType, price: Double): OrderEntity?
}