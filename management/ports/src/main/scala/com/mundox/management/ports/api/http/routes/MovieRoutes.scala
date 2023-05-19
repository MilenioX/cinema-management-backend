package com.mundox.management.ports.api.http.routes

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.Directives._
import com.mundox.management.core.env.log.Logger

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.DurationInt
import scala.concurrent.{Await, Future}
import scala.util.{Failure, Success}

class MovieRoutes extends Logger {

  def getRoutes: Route =
    getMovies

  private def getMovies: Route =
    path("movies") {
      get {
        loggerInfo("getMovies service invoked")
        val result = Await.ready(Future {
          Some(List(1))
        }, 10.seconds)

        onComplete(result) {
          case Success(value) =>
            loggerInfo("success response in getMovies service")
            complete(StatusCodes.OK, value.toString)
          case Failure(ex) =>
            loggerError(s"getMovies service has an error: $ex")
            complete(StatusCodes.InternalServerError, ex.getMessage)
        }
      }
    }

}
