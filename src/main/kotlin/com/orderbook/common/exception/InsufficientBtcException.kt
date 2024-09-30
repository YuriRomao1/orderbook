package com.orderbook.common.exception

import org.apache.coyote.BadRequestException

class InsufficientBtcException(val code: String) : BadRequestException() {

}
