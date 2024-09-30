package com.orderbook.common.exception

import org.apache.coyote.BadRequestException

class WalletNotFoundException(val code: String): BadRequestException(code) {
}