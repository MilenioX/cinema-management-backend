package com.mundox.management.ports.api.http

import akka.http.scaladsl.model.MediaTypes
import akka.http.scaladsl.model.headers.`Content-Type`
import com.mundox.management.ports.api.http.routes.{MovieRoutes, SnackRoutes}
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import com.mundox.management.ports.Environment

import scala.concurrent.ExecutionContext

class ManagementAPI(environment: Environment)(implicit ec: ExecutionContext) {

  private val movieRoutes = new MovieRoutes(environment.dummyMoviesQuery, environment.dummyMoviesCommand)
  private val snackRoutes = new SnackRoutes(environment.snacksQuery)

  def routes(): Route = {
    pathPrefix("management") {
      respondWithHeader(`Content-Type`(MediaTypes.`application/json`)) {
        movieRoutes.getRoutes ~ snackRoutes.getRoutes
      }
    }
  }
}
