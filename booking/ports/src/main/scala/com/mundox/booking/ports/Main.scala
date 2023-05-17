package com.mundox.booking.ports

import com.mundox.booking.core.domain.DummyCart
import com.mundox.booking.core.env.log.Logger

object Main extends App with Logger {

  loggerInfo("Booking service starting...")
  val dummyCart: DummyCart = DummyCart()
  println(dummyCart)
}
