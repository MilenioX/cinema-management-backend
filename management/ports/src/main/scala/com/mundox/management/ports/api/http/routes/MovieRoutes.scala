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

import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

class MovieRoutes(query: DummyMoviesQuery, command: DummyMoviesCommand) extends Logger with JsonSupport {

  def getRoutes: Route =
    getMovies ~ getMovieById ~ addMovie() ~ updateMovie() ~ deleteMovie()

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

  private def getMovieById: Route =
    path("movies" / Segment) { id =>
      get {
        loggerInfo("getMoviesById service was invoked")
        val result = for {
          res <- query.getMoviesById(id)
        } yield res

        onComplete(result) {
          case Success(either) =>
            either match {
              case Left(err) =>
                loggerError("getMovieById has an error $err")
                complete(StatusCodes.BadRequest, err.errorMsg)
              case Right(value) =>
                loggerInfo("success response in getMovieById service")
                complete(StatusCodes.OK, value.map(DummyMovieResponseDTO(_)))
            }
          case Failure(err) =>
            loggerError("getMoviesById has an error $err")
            complete(StatusCodes.InternalServerError, err.getMessage)
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
              res <- command.addMovie(toDomain(validation, None))
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

  private def updateMovie(): Route =
    path("movies" / Segment) { id =>
      put {
        entity(as[DummyCreateMovieRequestDTO]) { dto =>
          loggerInfo("updateMovie service invoked")
          val result = for {
            validation <- DummyCreateMovieRequestDTO.validateNec(dto)
            res <- command.updateMovie(id, toDomain(validation, Some(id)))
          } yield res

          onComplete(result.value) {
            case Success(eit) =>
              eit match {
                case Left(err) =>
                  loggerError(s"updateMovie service has an error: $err")
                  complete(StatusCodes.BadRequest, err.errorMsg)
                case Right(value) =>
                  loggerInfo("success response in updateMovie service")
                  complete(StatusCodes.OK, value.map(DummyMovieResponseDTO(_)))
              }
            case Failure(err) =>
              loggerError(s"updateMovie service has an error: $err")
              complete(StatusCodes.InternalServerError, err.getMessage)
          }
        }
      }
    }

  private def deleteMovie(): Route = {
    path("movies" / Segment) { id =>
      delete {
        loggerInfo(s"deleteMovie service was invoked")
        val result = for {
          res <- command.deleteMovie(id)
        } yield res

        onComplete(result.value) {
          case Success(either) =>
            either match {
              case Left(err) =>
                loggerError(s"deleteMovie has an error: $err")
                complete(StatusCodes.BadRequest, err.errorMsg)
              case Right(value) =>
                loggerInfo("success response in deleteMovie service")
                complete(StatusCodes.OK, value.map(DummyMovieResponseDTO(_)))
            }
          case Failure(err) =>
            loggerError(s"deleteMovie service has an error: $err")
            complete(StatusCodes.InternalServerError, err.getMessage)
        }

      }
    }
  }
}
