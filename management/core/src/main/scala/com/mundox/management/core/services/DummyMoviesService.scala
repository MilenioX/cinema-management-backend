package com.mundox.management.core.services

import com.mundox.management.core.domain.DummyMovie

import scala.concurrent.Future

trait DummyMoviesService {

  def getMovies: Future[Either[Exception, List[DummyMovie]]]

  def getMovieById(id: String): Future[Either[Exception,Option[DummyMovie]]]

  def addMovie(newMovie: DummyMovie): Future[Either[Exception,Option[DummyMovie]]]

  def updateMovie(id: String, updatedMovie: DummyMovie): Future[Either[Exception,Option[DummyMovie]]]

  def deleteMovie(id: String): Future[Either[Exception,Option[DummyMovie]]]
}
