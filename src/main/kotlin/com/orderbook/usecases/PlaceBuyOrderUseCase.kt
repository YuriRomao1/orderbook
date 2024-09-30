package com.orderbook.usecases

import com.orderbook.common.exception.InsufficientFundsException
import com.orderbook.common.exception.UserNotFoundException
import com.orderbook.domain.entities.Order
import com.orderbook.domain.entities.enum.OrderType
import com.orderbook.domain.gateway.OrderGateway
import com.orderbook.domain.gateway.UserGateway
import com.orderbook.domain.gateway.WalletGateway
import org.springframework.stereotype.Component

@Component
class PlaceBuyOrderUseCase(
    private val userGateway: UserGateway,
    private val walletGateway: WalletGateway,
    private val orderGateway: OrderGateway,
    private val matchOrdersUseCase: MatchOrdersUseCase
) {

    fun execute(input: Input) {
        val user = userGateway.findById(input.userId)
            ?: throw UserNotFoundException("Usuário não encontrado.")

        val wallet = walletGateway.findByUserId(user.id.toString())
            ?: throw UserNotFoundException("Carteira não encontrada.")
        //alterar essa tratativa de erro depois

        val totalCost = input.quantity * input.price
        if (wallet.balance >= totalCost) {
            wallet.balance -= totalCost
            walletGateway.update(wallet)

            val order = Order(
                id = generateOrderId().toString(),
                userId = user.id.toString(),
                type = OrderType.BUY,
                quantity = input.quantity,
                price = input.price
            )
            orderGateway.save(order)

            matchOrdersUseCase.execute(order)
        } else {
            throw InsufficientFundsException("Saldo insuficiente.")
        }
    }

    class Input(
        val userId: String,
        val quantity: Double,
        val price: Double
    )

    private fun generateOrderId(): Long {
        return System.currentTimeMillis()
    }
}