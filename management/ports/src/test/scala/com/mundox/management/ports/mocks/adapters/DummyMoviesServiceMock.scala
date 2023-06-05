package com.mundox.management.ports.mocks.adapters

import cats.data.EitherT
import com.mundox.management.core.domain.DummyMovie
import com.mundox.management.core.exceptions.ManagementException
import com.mundox.management.core.services.DummyMoviesService
import org.mockito.ArgumentMatchers.{any, anyString}
import org.mockito.Mockito.when
import org.scalatestplus.mockito.MockitoSugar.mock

import scala.concurrent.Future

object DummyMoviesServiceMock extends Mock[DummyMoviesService] {

  private val serviceMock:DummyMoviesService = mock[DummyMoviesService]

  when(serviceMock.getMovies).thenReturn {
    Future.successful {Right(List(DummyMovie("123", "Movie 1"),DummyMovie("321", "Movie 2")))}
  }
  when(serviceMock.getMovieById(any())).thenReturn {
    Future.successful {
      Right(Option(DummyMovie("123", "Movie 1")))
    }
  }
  when(serviceMock.addMovie(any())).thenReturn {
    EitherT[Future, ManagementException, Option[DummyMovie]] {
      Future.successful {Right(Option(DummyMovie("912", "Testing Movie")))}
    }
  }
  when(serviceMock.updateMovie(anyString(), any())).thenReturn {
    EitherT[Future, ManagementException, Option[DummyMovie]] {
      Future.successful {Right(Option(DummyMovie("123", "Movie updated")))}
    }
  }
  when(serviceMock.deleteMovie(anyString())).thenReturn {
    EitherT[Future, ManagementException, Option[DummyMovie]] {
      Future.successful {Right(Option(DummyMovie("123", "Movie updated")))}
    }
  }

  override def getMock: DummyMoviesService = serviceMock
}
