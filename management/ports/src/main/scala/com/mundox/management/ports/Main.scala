package com.mundox.management.ports

import com.mundox.management.core.env.log.Logger
import com.mundox.management.core.services.DummyMoviesService
import com.mundox.management.ports.adapters.DummyMoviesAdapter
import com.mundox.management.ports.api.http.ManagementAPI

object Main extends Logger {

  private def createEnvironment: Environment = new Environment {
    override val dummyMoviesService: DummyMoviesService = new DummyMoviesAdapter
  }

  def main(args: Array[String]): Unit = {
    loggerInfo("Management service starting...");
    val env = createEnvironment

    val api: ManagementAPI = new ManagementAPI(env)

    env.startEnvironment(api)
  }
}
