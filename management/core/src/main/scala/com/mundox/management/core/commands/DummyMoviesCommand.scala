package com.mundox.management.core.commands

import com.mundox.management.core.domain.DummyMovie
import com.mundox.management.core.exceptions.ManagementException
import com.mundox.management.core.services.DummyMoviesService

import scala.concurrent.Future

class DummyMoviesCommand(service: DummyMoviesService) {

  def addMovie(newMovie: DummyMovie):Future[Either[ManagementException,Option[DummyMovie]]] =
    service.addMovie(newMovie)

  def updateMovie(id: String, updatedMovie: DummyMovie):Future[Either[ManagementException,Option[DummyMovie]]] =
    service.updateMovie(id, updatedMovie)

  def deleteMovie(id: String): Future[Either[ManagementException,Option[DummyMovie]]] =
    service.deleteMovie(id)
}
