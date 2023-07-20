package com.mundox.management.ports

import com.mundox.management.core.env.log.Logger
import com.mundox.management.core.services.data.Repository
import com.mundox.management.core.services.{DummyMoviesService, SnacksService}
import com.mundox.management.ports.adapters.database.SnackRepository
import com.mundox.management.ports.adapters.database.dtos.SnackDTO
import com.mundox.management.ports.adapters.{DummyMoviesAdapter, SnacksAdapter}
import com.mundox.management.ports.api.http.ManagementAPI
import com.mundox.management.ports.config.Environment
import com.mundox.management.ports.config.properties.Configuration
import monix.eval.Task

object Main extends Logger {


  private def createEnvironment(config: Configuration): Environment = new Environment {
    override val dummyMoviesService: DummyMoviesService = new DummyMoviesAdapter
    override val snacksService: SnacksService[Task] = new SnacksAdapter(new SnackRepository(config.database))
  }

  def main(args: Array[String]): Unit = {
    loggerInfo("Management service starting...");

    Configuration.loadConfiguration.fold{
      loggerError("Configuration file could not be loaded.")
    }{config =>
      val env = createEnvironment(config)

      val api: ManagementAPI = new ManagementAPI(env)

      env.startEnvironment(api, config)
    }
  }
}
