package com.orderbook.domain.gateway

import com.orderbook.domain.entities.Order

interface OrderGateway {
    fun findByUserId(orderId: String): Order
    fun save(orderEntity: Order)
    fun findMatchingOrder(orderEntity: Order): Order?
    fun remove(orderEntity: Order)
}