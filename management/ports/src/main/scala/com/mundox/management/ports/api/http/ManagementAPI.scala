package com.mundox.management.ports.api.http

import akka.http.scaladsl.model.MediaTypes
import akka.http.scaladsl.model.headers.`Content-Type`
import com.mundox.management.ports.api.http.routes.MovieRoutes
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import com.mundox.management.ports.Environment

class ManagementAPI(environment: Environment) {

  private val movieRoutes = new MovieRoutes(environment.dummyMoviesQuery)

  def routes(): Route = {
    pathPrefix("management") {
      respondWithHeader(`Content-Type`(MediaTypes.`application/json`)) {
        movieRoutes.getRoutes
      }
    }
  }
}
