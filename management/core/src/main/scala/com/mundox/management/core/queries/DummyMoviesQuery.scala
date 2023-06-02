package com.mundox.management.core.queries

import com.mundox.management.core.domain.DummyMovie
import com.mundox.management.core.exceptions.ManagementException
import com.mundox.management.core.services.DummyMoviesService

import scala.concurrent.Future

class DummyMoviesQuery(service: DummyMoviesService) {

  def getMovies: Future[Either[ManagementException,List[DummyMovie]]] =
    service.getMovies

  def getMoviesById(id: String): Future[Either[ManagementException,Option[DummyMovie]]] =
    service.getMovieById(id)
}
