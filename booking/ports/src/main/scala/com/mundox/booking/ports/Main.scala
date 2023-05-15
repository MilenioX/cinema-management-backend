package com.mundox.booking.ports

import com.mundox.booking.core.domain.DummyCart

object Main extends App {

  println("Booking service starting...")
  val dummyCart: DummyCart = DummyCart()
  println(dummyCart)
}
