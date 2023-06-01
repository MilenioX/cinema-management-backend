package com.mundox.management.ports

import com.mundox.management.core.commands.DummyMoviesCommand
import com.mundox.management.core.queries.DummyMoviesQuery
import com.mundox.management.core.services.DummyMoviesService
import com.mundox.management.ports.api.http.{HttpServer, ManagementAPI}

trait Environment {

  def server(api:ManagementAPI) = new HttpServer(api)

  // Services
  val dummyMoviesService: DummyMoviesService

  // Queries
  lazy val dummyMoviesQuery: DummyMoviesQuery = new DummyMoviesQuery(dummyMoviesService)

  def startEnvironment(api: ManagementAPI): Unit = {
    server(api).startHttpServer()
  }

}
