package com.orderbook.domain.gateway

import com.orderbook.domain.entities.Wallet

interface WalletGateway {
    fun update(wallet: Wallet)
    fun findByUserId(userId: String): Wallet?
}