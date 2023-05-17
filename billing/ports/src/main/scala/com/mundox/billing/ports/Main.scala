package com.mundox.billing.ports

import com.mundox.billing.core.domain.DummyOrder
import com.mundox.billing.core.env.log.Logger

object Main extends App with Logger {

  loggerInfo("Billing service starting...")
  val dummyOrder: DummyOrder = DummyOrder(0)
  println(dummyOrder)
}
