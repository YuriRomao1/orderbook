package com.orderbook.domain.gateway

import com.orderbook.domain.entities.User

interface UserGateway {
    fun findById(userId: String): User?
    fun save(user: User)
}