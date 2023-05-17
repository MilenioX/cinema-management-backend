package com.mundox.management.ports

import com.mundox.management.core.domain.DummyMovie
import com.mundox.management.core.env.log.Logger

object Main extends App with Logger {
  loggerInfo("Management service starting...");
  val movie: DummyMovie = DummyMovie("123", "The crow")
  println("movie")
}
