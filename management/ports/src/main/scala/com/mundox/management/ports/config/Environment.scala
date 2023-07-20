package com.mundox.management.ports.config

import com.mundox.management.core.commands.DummyMoviesCommand
import com.mundox.management.core.queries.{DummyMoviesQuery, SnacksQuery}
import com.mundox.management.core.services.{DummyMoviesService, SnacksService}
import com.mundox.management.ports.api.http.{HttpServer, ManagementAPI}
import com.mundox.management.ports.config.properties.{Configuration, Server}
import monix.eval.Task

import scala.concurrent.Future

trait Environment {

  def server(api:ManagementAPI, serverConf: Server) = new HttpServer(api, serverConf)

  // Services
  val dummyMoviesService: DummyMoviesService
  val snacksService: SnacksService[Task]

  // Queries
  lazy val dummyMoviesQuery: DummyMoviesQuery = new DummyMoviesQuery(dummyMoviesService)
  lazy val snacksQuery: SnacksQuery[Task] = new SnacksQuery[Task](snacksService)

  // Commands
  lazy val dummyMoviesCommand: DummyMoviesCommand = new DummyMoviesCommand(dummyMoviesService)

  def startEnvironment(api: ManagementAPI, config: Configuration): Unit = {
    server(api, config.server).startHttpServer()
  }

}
