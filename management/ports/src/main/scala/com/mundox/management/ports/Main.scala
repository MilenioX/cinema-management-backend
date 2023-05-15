package com.mundox.management.ports

import com.mundox.management.core.domain.DummyMovie

object Main extends App {

  println("Management service starting...");
  val movie: DummyMovie = DummyMovie("123", "The crow")
  println("movie")
}
