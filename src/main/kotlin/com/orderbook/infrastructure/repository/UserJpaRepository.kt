package com.orderbook.infrastructure.repository

import com.orderbook.infrastructure.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UserJpaRepository: JpaRepository<UserEntity, Long> {
}