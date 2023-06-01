package com.mundox.management.ports.api.http.routes

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.Directives._
import com.mundox.management.core.env.log.Logger
import com.mundox.management.ports.api.http.JsonSupport
import com.mundox.management.ports.api.http.requests.DummyCreateMovieRequestDTO
import com.mundox.management.ports.api.http.responses.DummyMovieResponseDTO

import java.util.UUID
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.DurationInt
import scala.concurrent.{Await, Future}
import scala.util.{Failure, Success}

class MovieRoutes extends Logger with JsonSupport {

  def getRoutes: Route =
    getMovies ~ addMovie()

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
            loggerInfo("success response in getMovies service")
            complete(StatusCodes.OK -> value)
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
              validation <- Await.ready (Future{DummyCreateMovieRequestDTO.validateNec(movie)}, 5.seconds)
              res <- Await.ready(Future {
                    Right(DummyMovieResponseDTO(UUID.randomUUID().toString, movie.title))
                  }, 10.seconds)
            } yield validation.fold(v => Left(v), _ => res)

          onComplete(result) {
            case Success(value) =>
              value match {
                case Left(err) =>
                  loggerError(s"addMovie service has an error: $err")
                  complete(StatusCodes.BadRequest, err.map(_.errorMessage).mkString(","))
                case Right(value) =>
                  loggerInfo("success response in addMovie service")
                  complete(StatusCodes.OK -> value)
              }
            case Failure(ex) =>
              loggerError(s"addMovie service has an error: $ex")
              complete(StatusCodes.InternalServerError, ex.getMessage)
          }
        }
      }
    }
}
