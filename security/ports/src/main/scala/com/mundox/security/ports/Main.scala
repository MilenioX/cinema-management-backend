package com.mundox.security.ports

import com.mundox.security.core.DummyUser
import com.mundox.security.core.env.log.Logger

object Main extends App with Logger {
  loggerInfo("Security service starting...")
  val dummyUser: DummyUser = DummyUser("123","user1")
  println(dummyUser)
}
