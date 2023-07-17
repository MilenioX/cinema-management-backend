package com.mundox.management.ports

import com.mundox.management.core.env.log.Logger
import com.mundox.management.core.services.{DummyMoviesService, SnacksService}
import com.mundox.management.ports.adapters.database.SnackRepository
import com.mundox.management.ports.adapters.{DummyMoviesAdapter, SnacksAdapter}
import com.mundox.management.ports.api.http.ManagementAPI

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
object Main extends Logger {


  private def createEnvironment: Environment = new Environment {
    override val dummyMoviesService: DummyMoviesService = new DummyMoviesAdapter
    override val snacksService: SnacksService[Future] = new SnacksAdapter(new SnackRepository())
  }

  def main(args: Array[String]): Unit = {
    loggerInfo("Management service starting...");
    val env = createEnvironment

    val api: ManagementAPI = new ManagementAPI(env)

    env.startEnvironment(api)
  }
}
