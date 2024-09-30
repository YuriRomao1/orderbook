package com.orderbook.domain.entities

data class User(
    val id: Long? = null,
    val name: String,
    val wallet: Wallet
)