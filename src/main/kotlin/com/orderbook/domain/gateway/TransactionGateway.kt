package com.orderbook.domain.gateway

import com.orderbook.domain.entities.Transaction

interface TransactionGateway {
    fun save(transaction: Transaction)

}