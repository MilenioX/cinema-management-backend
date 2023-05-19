package com.mundox.management.ports.api.http

import com.mundox.management.ports.api.http.routes.MovieRoutes
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route

class ManagementAPI {

  private val movieRoutes = new MovieRoutes()

  def routes(): Route = {
    pathPrefix("management") {
      movieRoutes.getRoutes
    }
  }
}
