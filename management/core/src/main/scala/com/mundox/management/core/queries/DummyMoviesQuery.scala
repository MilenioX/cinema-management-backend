package com.mundox.management.core.queries

import com.mundox.management.core.domain.DummyMovie
import com.mundox.management.core.services.DummyMoviesService

import scala.concurrent.Future

class DummyMoviesQuery(service: DummyMoviesService) {

  def getMovies: Future[Either[Exception,List[DummyMovie]]] =
    service.getMovies

  def getMoviesById(id: String): Future[Either[Exception,Option[DummyMovie]]] =
    service.getMovieById(id)
}
