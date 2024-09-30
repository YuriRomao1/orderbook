package com.orderbook.domain.entities
import java.time.LocalDateTime

data class Transaction(
    val id: String,
    //Colocando como string, mas vai dar errado na conta
    val buyOrderEntity: String?,
    val sellOrderEntity: String?,
    val quantity: Double,
    val price: Double,
    val timestamp: LocalDateTime
)