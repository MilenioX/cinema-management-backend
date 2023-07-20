package com.mundox.management.ports.api.http.routes

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Route
import com.mundox.management.core.env.log.Logger
import com.mundox.management.ports.api.http.JsonSupport
import akka.http.scaladsl.server.Directives._
import com.mundox.management.core.queries.SnacksQuery
import com.mundox.management.ports.api.http.requests.CommonValidations
import com.mundox.management.ports.api.http.responses.SnackDTO
import monix.eval.Task
import monix.execution.Scheduler.Implicits.global

import scala.concurrent.ExecutionContext
import scala.util.{Failure, Success}

class SnackRoutes(query: SnacksQuery[Task]) extends Logger with JsonSupport {

  def getRoutes: Route = pathPrefix("snacks") {
    getSnackById ~ getAllSnacks
  }

  private def getAllSnacks: Route =
    get {
      loggerInfo(s"getSnacks service invoked")
      val result = for {
        snacks <- query.getSnacks
      } yield snacks

      onComplete(result.runToFuture) {
        case Success(snacks) =>
          loggerInfo(s"Success response in getAllSnacks service")
          complete(StatusCodes.OK -> snacks.map(SnackDTO(_)))
        case Failure(exception) =>
          loggerInfo(s"There was an error getting the records $exception")
          complete(StatusCodes.InternalServerError)
      }
    }

  private def getSnackById: Route =
    get {
      parameters("id".as[Int]) { id => {
        loggerInfo(s"getSnacksById service invoked with id $id")
        val result = for {
          _ <- CommonValidations.validateId(id)
          snack <- query.getSnackById(id)
        } yield snack

        onComplete(result.runToFuture) {
          case Success(value) =>
            value match {
              case Some(snack) =>
                loggerInfo(s"Success response in getAllSnacksById service")
                complete(StatusCodes.OK -> SnackDTO(snack))
              case None =>
                loggerInfo(s"Empty response in getAllSnacksById service")
                complete(StatusCodes.NotFound)
            }
          case Failure(exception) =>
            loggerInfo(s"There was an error getting the records $exception")
            complete(StatusCodes.InternalServerError)
        }
      }}
    }
}
