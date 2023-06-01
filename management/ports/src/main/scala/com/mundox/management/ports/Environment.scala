package com.mundox.management.ports

import com.mundox.management.ports.api.http.{HttpServer, ManagementAPI}

object Environment {

  val apiRoutes: ManagementAPI = new ManagementAPI
  val server = new HttpServer(apiRoutes)

  def startEnvironment(): Unit = {
    server.startHttpServer()
  }

}
