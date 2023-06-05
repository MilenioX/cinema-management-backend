package com.mundox.management.core.commands

import cats.data.EitherT
import com.mundox.management.core.domain.DummyMovie
import com.mundox.management.core.env.log.Logger
import com.mundox.management.core.exceptions.ManagementException
import com.mundox.management.core.services.DummyMoviesService

import scala.concurrent.Future

class DummyMoviesCommand(service: DummyMoviesService) extends Logger {

  def addMovie(newMovie: DummyMovie):EitherT[Future, ManagementException,Option[DummyMovie]] = {
    loggerInfo("addMovie service in command was invoked.")
    service.addMovie(newMovie)
  }

  def updateMovie(id: String, updatedMovie: DummyMovie):EitherT[Future, ManagementException,Option[DummyMovie]] = {
    loggerInfo("updateMovie service in command was invoked.")
    service.updateMovie(id, updatedMovie)
  }

  def deleteMovie(id: String): EitherT[Future, ManagementException,Option[DummyMovie]] = {
    loggerInfo("deleteMovie service in command was invoked.")
    service.deleteMovie(id)
  }
}
