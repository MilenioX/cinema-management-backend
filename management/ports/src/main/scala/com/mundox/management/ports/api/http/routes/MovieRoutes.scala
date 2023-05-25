package com.mundox.management.ports.api.http.routes

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.Directives._
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.json.JsonMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.mundox.management.core.env.log.Logger
import com.mundox.management.ports.api.http.responses.DummyMovieResponseDTO

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
        val result: Future[List[DummyMovieResponseDTO]] = Await.ready(Future {
          List(
            DummyMovieResponseDTO("123", "Movie 1"),
            DummyMovieResponseDTO("321", "Movie 2")
          )
        }, 10.seconds)

        onComplete(result) {
          case Success(value) =>
            val mapper = JsonMapper
              .builder()
              .addModule(DefaultScalaModule)
              .enable(DeserializationFeature.FAIL_ON_NULL_CREATOR_PROPERTIES)
              .build()

            loggerInfo("success response in getMovies service")
            complete(StatusCodes.OK -> mapper.writeValueAsString(value))
          case Failure(ex) =>
            loggerError(s"getMovies service has an error: $ex")
            complete(StatusCodes.InternalServerError, ex.getMessage)
        }
      }
    }

}
