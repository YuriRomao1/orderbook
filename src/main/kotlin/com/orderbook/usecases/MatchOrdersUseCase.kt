package com.orderbook.usecases

import com.orderbook.common.exception.WalletNotFoundException
import com.orderbook.domain.entities.Order
import com.orderbook.domain.entities.Transaction
import com.orderbook.domain.entities.enum.OrderType
import com.orderbook.domain.gateway.OrderGateway
import com.orderbook.domain.gateway.TransactionGateway
import com.orderbook.domain.gateway.WalletGateway
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class MatchOrdersUseCase(
    private val orderGateway: OrderGateway,
    private val transactionGateway: TransactionGateway,
    private val walletGateway: WalletGateway
) {
    fun execute(order: Order) {
        val matchingOrder = orderGateway.findMatchingOrder(order)

        if (matchingOrder != null) {
            val tradedQuantity = minOf(order.quantity, matchingOrder.quantity)

            val transaction = Transaction(
                id = generateTransactionId().toString(),
                buyOrderEntity = if (order.type == OrderType.BUY) order.id else matchingOrder.id,
                sellOrderEntity = if (order.type == OrderType.SELL) order.id else matchingOrder.id,
                quantity = tradedQuantity,
                price = matchingOrder.price,
                timestamp = LocalDateTime.now()
            )
            transactionGateway.save(transaction)

            // Atualiza as carteiras
            val buyerWallet = walletGateway.findByUserId(transaction.buyOrderEntity!!)
                ?: throw WalletNotFoundException("Carteira do comprador não encontrada.")
            val sellerWallet = walletGateway.findByUserId(transaction.sellOrderEntity!!)
                ?: throw WalletNotFoundException("Carteira do vendedor não encontrada.")

            buyerWallet.btcCoin += tradedQuantity
            sellerWallet.balance += tradedQuantity * transaction.price

            walletGateway.update(buyerWallet)
            walletGateway.update(sellerWallet)

            // Atualiza ou remove ordens parcialmente preenchidas
            updateOrders(order, matchingOrder, tradedQuantity)
        }
    }

    private fun updateOrders(order1: Order, order2: Order, tradedQuantity: Double) {
        order1.quantity -= tradedQuantity
        if (order1.quantity <= 0) {
            orderGateway.remove(order1)
        } else {
            orderGateway.save(order1)
        }

        order2.quantity -= tradedQuantity
        if (order2.quantity <= 0) {
            orderGateway.remove(order2)
        } else {
            orderGateway.save(order2)
        }
    }

    private fun generateTransactionId(): Long {
        return System.currentTimeMillis()
    }
}
