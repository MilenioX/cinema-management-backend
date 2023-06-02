package com.mundox.management.ports.api.http.routes

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.Directives._
import com.mundox.management.core.commands.DummyMoviesCommand
import com.mundox.management.core.env.log.Logger
import com.mundox.management.core.queries.DummyMoviesQuery
import com.mundox.management.ports.api.http.JsonSupport
import com.mundox.management.ports.api.http.requests.DummyCreateMovieRequestDTO
import com.mundox.management.ports.api.http.requests.DummyCreateMovieRequestDTO.toDomain
import com.mundox.management.ports.api.http.responses.DummyMovieResponseDTO

import java.util.UUID
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.DurationInt
import scala.concurrent.{Await, Future}
import scala.util.{Failure, Success}

class MovieRoutes(query: DummyMoviesQuery, command: DummyMoviesCommand) extends Logger with JsonSupport {

  def getRoutes: Route =
    getMovies ~ addMovie()

  private def getMovies: Route =
    path("movies") {
      get {
        loggerInfo("getMovies service invoked")
        val result = query.getMovies

        onComplete(result) {
          case Success(value) =>
            value match {
              case Left(err) =>
                loggerError(s"getMovies service has an error: $err")
                complete(StatusCodes.BadRequest, err.getMessage)
              case Right(v) =>
                loggerInfo("success response in getMovies service")
                complete(StatusCodes.OK -> v.map(DummyMovieResponseDTO(_)))
            }
          case Failure(ex) =>
            loggerError(s"getMovies service has an error: $ex")
            complete(StatusCodes.InternalServerError, ex.getMessage)
        }
      }
    }

  private def addMovie(): Route =
    path("movies") {
      post {
        entity(as[DummyCreateMovieRequestDTO]) { movie =>
          loggerInfo("addMovie service invoked")
          val result =
            for {
              validation <- DummyCreateMovieRequestDTO.validateNec(movie)
              res <- command.addMovie(toDomain(validation))
            } yield res

          onComplete(result.value) {
            case Success(value) =>
              value match {
                case Left(err) =>
                  loggerError(s"addMovie service has an error: ${err.errorMsg}")
                  complete(StatusCodes.BadRequest, err.errorMsg)
                case Right(value) =>
                  loggerInfo("success response in addMovie service")
                  complete(StatusCodes.OK -> value.map(DummyMovieResponseDTO(_)))
              }
            case Failure(ex) =>
              loggerError(s"addMovie service has an error: $ex")
              complete(StatusCodes.InternalServerError, ex.getMessage)
          }
        }
      }
    }
}
