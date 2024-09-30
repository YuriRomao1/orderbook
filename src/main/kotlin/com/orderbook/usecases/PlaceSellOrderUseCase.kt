package com.orderbook.usecases

import com.orderbook.common.exception.UserNotFoundException
import com.orderbook.common.exception.WalletNotFoundException
import com.orderbook.domain.entities.Order
import com.orderbook.domain.entities.enum.OrderType
import com.orderbook.domain.gateway.OrderGateway
import com.orderbook.domain.gateway.UserGateway
import com.orderbook.domain.gateway.WalletGateway

class PlaceSellOrderUseCase(
    private val userGateway: UserGateway,
    private val walletGateway: WalletGateway,
    private val orderGateway: OrderGateway,
    private val matchOrdersUseCase: MatchOrdersUseCase
) {
    fun execute(input: Input) {
        val user = userGateway.findById(input.userId)
            ?: throw UserNotFoundException("Usuário não encontrado.")

        val wallet = walletGateway.findByUserId(user.id.toString())
            ?: throw WalletNotFoundException("Carteira não encontrada.")

        if (wallet.btcCoin >= input.quantity) {
            wallet.btcCoin -= input.quantity
            walletGateway.update(wallet)

            val order = Order(
                id = generateOrderId().toString(),
                userId = user.id.toString(),
                type = OrderType.SELL,
                quantity = input.quantity,
                price = input.price
            )
            orderGateway.save(order)

            matchOrdersUseCase.execute(order)
        } else {
            throw UserNotFoundException("Quantidade de vibranium insuficiente.")
            //corrigir msg de error
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