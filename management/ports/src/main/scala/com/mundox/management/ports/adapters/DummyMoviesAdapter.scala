package com.mundox.management.ports.adapters

import com.mundox.management.core.domain.DummyMovie
import com.mundox.management.core.services.DummyMoviesService

import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.DurationInt

class DummyMoviesAdapter extends DummyMoviesService {

  private var moviesList: List[DummyMovie] = List(
    DummyMovie("123", "Movie 1"),
    DummyMovie("321", "Movie 2"),
    DummyMovie("987", "Movie 3"),
    DummyMovie("789", "Movie 4")
  )

  override def getMovies: Future[Either[Exception, List[DummyMovie]]] = Await.ready(Future {
    try {
      Right(moviesList)
    } catch {
      case e: Exception => Left(e)
    }
  }, 10.seconds)

  override def getMovieById(id: String): Future[Either[Exception, Option[DummyMovie]]] = Await.ready(Future {
    try {
      Right(moviesList.find(_.id.equals(id)))
    } catch {
      case e: Exception => Left(e)
    }
  }, 5.seconds)

  override def addMovie(newMovie: DummyMovie): Future[Either[Exception, Option[DummyMovie]]] = Await.ready(Future {
    try {
      moviesList = moviesList :+ newMovie
      Right(Option(newMovie))
    } catch {
      case e: Exception => Left(e)
    }
  }, 5.seconds)

  override def updateMovie(id: String, updatedMovie: DummyMovie): Future[Either[Exception, Option[DummyMovie]]] = Await.ready(Future {
    try {
      moviesList = moviesList.map(value => if (value.id.equals(id)) updatedMovie else value)
      Right(Option(updatedMovie))
    } catch {
      case e: Exception => Left(e)
    }
  }, 5.seconds)

  override def deleteMovie(id: String): Future[Either[Exception, Option[DummyMovie]]] = Await.ready(Future {
    try {
      val deletedElement = moviesList.find(_.id.equals(id))
      moviesList = moviesList.filter(!_.id.equals(id))
      Right(deletedElement)
    } catch {
      case e: Exception => Left(e)
    }
  }, 5.seconds)
}
