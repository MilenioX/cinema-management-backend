package com.mundox.security.ports

import com.mundox.security.core.DummyUser

object Main extends App {
  println("Security service starting...")
  val dummyUser: DummyUser = DummyUser("123","user1")
  println(dummyUser)
}
