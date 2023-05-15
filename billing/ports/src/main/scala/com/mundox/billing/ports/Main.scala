package com.mundox.billing.ports

import com.mundox.billing.core.domain.DummyOrder

object Main extends App {

  println("Billing service starting...")
  val dummyOrder: DummyOrder = DummyOrder(0)
  println(dummyOrder)
}
