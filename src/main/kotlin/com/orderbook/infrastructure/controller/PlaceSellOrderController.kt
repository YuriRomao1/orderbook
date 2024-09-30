package com.orderbook.infrastructure.controller

import com.orderbook.usecases.PlaceBuyOrderUseCase.Input
import com.orderbook.usecases.PlaceSellOrderUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/orders")
class PlaceSellOrderController(
    private val placeSellOrderUseCase: PlaceSellOrderUseCase
) {
    @PostMapping("/sell")
    fun placeSellOrder(@RequestBody request: Request): ResponseEntity<String> {

        placeSellOrderUseCase.execute(
            PlaceSellOrderUseCase.Input(
                userId = request.userId,
                price = request.price,
                quantity = request.quantity
            )
        )

        return ResponseEntity.ok("Ordem de venda colocada com sucesso.")

    }

    class Request(
        val userId: String,
        val quantity: Double,
        val price: Double
    )
}