package com.mundox.management.ports

import cats.data.EitherT
import com.mundox.management.core.domain.DummyMovie
import com.mundox.management.core.exceptions.ManagementException
import com.mundox.management.core.services.DummyMoviesService
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito.when
import org.scalatestplus.mockito.MockitoSugar.mock

import scala.concurrent.Future

class TestEnvironment extends Environment {

  val serviceMock: DummyMoviesService = mock[DummyMoviesService]
  when(serviceMock.getMovies).thenReturn {
    Future.successful {Right(List(DummyMovie("123", "Movie 1"),DummyMovie("321", "Movie 2")))}
  }
  when(serviceMock.addMovie(any())).thenReturn {
    EitherT[Future, ManagementException, Option[DummyMovie]] {
      Future.successful {Right(Option(DummyMovie("912", "Testing Movie")))}
    }
  }

  override val dummyMoviesService: DummyMoviesService = serviceMock
}

object TestEnvironment extends TestEnvironment