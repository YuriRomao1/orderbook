package com.orderbook.infrastructure.repository

import com.orderbook.infrastructure.entity.WalletEntity
import org.springframework.data.jpa.repository.JpaRepository

interface WalletJpaRepository : JpaRepository<WalletEntity, String> {
    fun findByUserId(userId: String): WalletEntity?
}