package com.orderbook.domain.entities

import com.orderbook.domain.entities.enum.OrderType

data class Order(
    val id: String? = null,
    val userId: String,
    val type: OrderType,
    var quantity: Double,
    val price: Double
)