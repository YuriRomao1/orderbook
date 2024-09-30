package com.orderbook.domain.entities

data class Wallet(
    val id: String?= null,
    val userId: String,
    var balance: Double,
    var btcCoin: Double,
)