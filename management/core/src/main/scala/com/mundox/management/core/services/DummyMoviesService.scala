package com.mundox.management.core.services

import com.mundox.management.core.domain.DummyMovie
import com.mundox.management.core.exceptions.ManagementException

import scala.concurrent.Future

trait DummyMoviesService {

  def getMovies: Future[Either[ManagementException, List[DummyMovie]]]

  def getMovieById(id: String): Future[Either[ManagementException,Option[DummyMovie]]]

  def addMovie(newMovie: DummyMovie): Future[Either[ManagementException,Option[DummyMovie]]]

  def updateMovie(id: String, updatedMovie: DummyMovie): Future[Either[ManagementException,Option[DummyMovie]]]

  def deleteMovie(id: String): Future[Either[ManagementException,Option[DummyMovie]]]
}
