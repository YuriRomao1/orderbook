package com.orderbook.infrastructure.controller

import com.orderbook.usecases.PlaceBuyOrderUseCase
import com.orderbook.usecases.PlaceBuyOrderUseCase.Input
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/orders")
class PlaceBuyOrderController(
    private val placeBuyOrderUseCase: PlaceBuyOrderUseCase
) {
    @PostMapping("/buy")
    fun placeBuyOrder(@RequestBody request: Request): ResponseEntity<String> {

        placeBuyOrderUseCase
            .execute(
                Input(
                    userId = request.userId,
                    price = request.price,
                    quantity = request.quantity

                )
            )

        return ResponseEntity.ok("Ordem de compra colocada com sucesso.")

    }

    class Request(
        val userId: String,
        val quantity: Double,
        val price: Double
    )
}