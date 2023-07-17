package com.mundox.management.ports

import com.mundox.management.core.services.{DummyMoviesService, SnacksService}
import com.mundox.management.ports.config.Environment
import com.mundox.management.ports.mocks.adapters.DummyMoviesServiceMock.getMock

import scala.concurrent.Future

class TestEnvironment extends Environment {

  val serviceMock: DummyMoviesService = getMock

  override val dummyMoviesService: DummyMoviesService = serviceMock
  override val snacksService: SnacksService[Future] = ???
}

object TestEnvironment extends TestEnvironment