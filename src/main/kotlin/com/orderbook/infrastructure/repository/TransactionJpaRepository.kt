package com.orderbook.infrastructure.repository

import com.orderbook.infrastructure.entity.TransactionEntity
import org.springframework.data.jpa.repository.JpaRepository

interface TransactionJpaRepository : JpaRepository<TransactionEntity, String>{

}
