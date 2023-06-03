package com.mundox.management.core.queries

import com.mundox.management.core.domain.DummyMovie
import com.mundox.management.core.env.log.Logger
import com.mundox.management.core.exceptions.ManagementException
import com.mundox.management.core.services.DummyMoviesService

import scala.concurrent.Future

class DummyMoviesQuery(service: DummyMoviesService) extends Logger {

  def getMovies: Future[Either[ManagementException,List[DummyMovie]]] = {
    loggerInfo("getMovies service in query was invoked.")
    service.getMovies
  }

  def getMoviesById(id: String): Future[Either[ManagementException,Option[DummyMovie]]] = {
    loggerInfo("getMoviesById service in query was invoked.")
    service.getMovieById(id)
  }
}
