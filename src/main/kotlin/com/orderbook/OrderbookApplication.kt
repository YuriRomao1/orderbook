package com.orderbook.orderbook

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class OrderbookApplication

fun main(args: Array<String>) {
	runApplication<OrderbookApplication>(*args)
}
