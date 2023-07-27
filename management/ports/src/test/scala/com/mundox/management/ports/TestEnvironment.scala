package com.mundox.management.ports

import com.mundox.management.core.services.{DummyMoviesService, SnacksService}
import com.mundox.management.ports.config.Environment
import com.mundox.management.ports.mocks.adapters.DummyMoviesServiceMock.getMock
import com.mundox.management.ports.mocks.adapters.SnackServiceMock
import monix.eval.Task

class TestEnvironment extends Environment {

  val serviceMock: DummyMoviesService = getMock

  override val dummyMoviesService: DummyMoviesService = serviceMock
  override val snacksService: SnacksService[Task] = new SnackServiceMock
}

object TestEnvironment extends TestEnvironment