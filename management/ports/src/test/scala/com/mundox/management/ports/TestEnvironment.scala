package com.mundox.management.ports

import com.mundox.management.core.services.DummyMoviesService
import com.mundox.management.ports.mocks.adapters.DummyMoviesServiceMock.getMock

class TestEnvironment extends Environment {

  val serviceMock: DummyMoviesService = getMock

  override val dummyMoviesService: DummyMoviesService = serviceMock
}

object TestEnvironment extends TestEnvironment