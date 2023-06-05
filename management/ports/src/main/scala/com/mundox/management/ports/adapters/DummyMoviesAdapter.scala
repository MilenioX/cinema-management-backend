package com.mundox.management.ports.adapters

import cats.data.EitherT
import com.mundox.management.core.domain.DummyMovie
import com.mundox.management.core.env.log.Logger
import com.mundox.management.core.exceptions.{AdapterError, ManagementException}
import com.mundox.management.core.services.DummyMoviesService

import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.DurationInt

class DummyMoviesAdapter extends DummyMoviesService with Logger {

  private var moviesList: List[DummyMovie] = List(
    DummyMovie("123", "Movie 1"),
    DummyMovie("321", "Movie 2"),
    DummyMovie("987", "Movie 3"),
    DummyMovie("789", "Movie 4")
  )

  override def getMovies: Future[Either[ManagementException, List[DummyMovie]]] = {
    loggerInfo("getMovies service in adapter was invoked.")
    Await.ready(Future {
      try {
        Right(moviesList)
      } catch {
        case e: Exception => Left(AdapterError(e.getMessage))
      }
    }, 10.seconds)
  }

  override def getMovieById(id: String): Future[Either[ManagementException, Option[DummyMovie]]] = {
    loggerInfo("getMoviesById service in adapter was invoked.")
    Await.ready(Future {
      try {
        Right(moviesList.find(_.id.equals(id)))
      } catch {
        case e: Exception => Left(AdapterError(e.getMessage))
      }
    }, 5.seconds)
  }

  override def addMovie(newMovie: DummyMovie): EitherT[Future, ManagementException, Option[DummyMovie]] = {
    loggerInfo("addMovie service in adapter was invoked.")
    EitherT {
      Await.ready(Future[Either[ManagementException, Option[DummyMovie]]] {
        try {
          moviesList = moviesList :+ newMovie
          Right(Option(newMovie))
        } catch {
          case e: Exception => Left(AdapterError(e.getMessage))
        }
      }, 5.seconds)
    }
  }

  override def updateMovie(id: String, updatedMovie: DummyMovie): EitherT[Future, ManagementException, Option[DummyMovie]] = {
    loggerInfo("updateMovie service in adapter was invoked.")
    EitherT {
      Await.ready(Future {
        try {
          moviesList = moviesList.map(value => if (value.id.equals(id)) updatedMovie else value)
          Right(Option(updatedMovie))
        } catch {
          case e: Exception => Left(AdapterError(e.getMessage))
        }
      }, 5.seconds)
    }
  }

  override def deleteMovie(id: String): Future[Either[ManagementException, Option[DummyMovie]]] = {
    loggerInfo("deleteMovie service in adapter was invoked.")
    Await.ready(Future {
      try {
        val deletedElement = moviesList.find(_.id.equals(id))
        moviesList = moviesList.filter(!_.id.equals(id))
        Right(deletedElement)
      } catch {
        case e: Exception => Left(AdapterError(e.getMessage))
      }
    }, 5.seconds)
  }
}
