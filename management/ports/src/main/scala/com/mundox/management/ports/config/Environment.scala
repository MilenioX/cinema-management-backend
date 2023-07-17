package com.mundox.management.ports.config

import com.mundox.management.core.commands.DummyMoviesCommand
import com.mundox.management.core.queries.{DummyMoviesQuery, SnacksQuery}
import com.mundox.management.core.services.{DummyMoviesService, SnacksService}
import com.mundox.management.ports.api.http.{HttpServer, ManagementAPI}

import scala.concurrent.Future

trait Environment {

  def server(api:ManagementAPI) = new HttpServer(api)

  // Services
  val dummyMoviesService: DummyMoviesService
  val snacksService: SnacksService[Future]

  // Queries
  lazy val dummyMoviesQuery: DummyMoviesQuery = new DummyMoviesQuery(dummyMoviesService)
  lazy val snacksQuery: SnacksQuery[Future] = new SnacksQuery[Future](snacksService)

  // Commands
  lazy val dummyMoviesCommand: DummyMoviesCommand = new DummyMoviesCommand(dummyMoviesService)

  def startEnvironment(api: ManagementAPI): Unit = {
    server(api).startHttpServer()
  }

}
