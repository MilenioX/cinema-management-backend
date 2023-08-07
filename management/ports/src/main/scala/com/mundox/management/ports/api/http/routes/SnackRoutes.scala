package com.mundox.management.ports.api.http.routes

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Route
import com.mundox.management.core.env.log.Logger
import com.mundox.management.ports.api.http.JsonSupport
import akka.http.scaladsl.server.Directives._
import cats.data.EitherT
import com.mundox.management.core.domain.snacks.Snack
import com.mundox.management.core.typeclasses.TransformerSyntax._
import com.mundox.management.core.queries.SnacksQuery
import com.mundox.management.ports.api.http.requests.{CommonValidations, SnackRequestDTO}
import com.mundox.management.ports.api.http.responses.SnackResponseDTO
import monix.eval.Task
import monix.execution.Scheduler.Implicits.global

import scala.util.{Failure, Success}

class SnackRoutes(query: SnacksQuery[Task]) extends Logger with JsonSupport {

  def getRoutes: Route = getSnackById ~ getAllSnacks ~ addSnack

  private def getAllSnacks: Route =
    path("snacks") {
      get {
        loggerInfo(s"getSnacks service invoked")
        val result = for {
          snacks <- query.getSnacks
        } yield snacks

        onComplete(result.value.runToFuture) {
          case Success(eitherResp) =>
            eitherResp match {
              case Left(err) =>
                loggerInfo(s"There was an error getting the records $err")
                complete(StatusCodes.InternalServerError)
              case Right(snacks) =>
                loggerInfo(s"Success response in getAllSnacks service")
                complete(StatusCodes.OK -> snacks.map(_.asType[SnackResponseDTO]))
            }
          case Failure(exception) =>
            loggerInfo(s"There was an error getting the records $exception")
            complete(StatusCodes.InternalServerError)
        }
      }
    }

  private def getSnackById: Route = {
    path("snacks" / Segment) { id =>
      get {
        loggerInfo(s"getSnacksById service invoked with id $id")
        val result: EitherT[Task, String, Option[Snack]] = for {
          validId <- CommonValidations.validateId(id)
          snack <- query.getSnackById(validId)
        } yield snack

        onComplete(result.value.runToFuture) {
          case Success(eitherResp) => eitherResp match {
            case Left(err) =>
              loggerInfo(s"There was an error getting the records $err")
              complete(StatusCodes.InternalServerError -> err)
            case Right(value) => value match {
              case Some(snack) =>
                loggerInfo(s"Success response in getAllSnacksById service")
                complete(StatusCodes.OK -> snack.asType[SnackResponseDTO])
              case None =>
                loggerInfo(s"Empty response in getAllSnacksById service")
                complete(StatusCodes.NotFound)
            }
          }

          case Failure(exception) =>
            loggerInfo(s"There was an error getting the records $exception")
            complete(StatusCodes.InternalServerError)
        }
      }
    }
  }

  private def addSnack(): Route =
    path("snacks") {
      post {
        entity(as[SnackRequestDTO]) { snack =>
          loggerInfo(s"Add Snack service invoked")
          val result = for {
            validSnack <- SnackRequestDTO.validateSnackRequest(snack)
            snack = validSnack.asType[Snack]
          } yield snack

          onComplete(result.value.runToFuture) {
            case Success(eitherResp) => eitherResp match {
              case Right(snack) =>
                loggerInfo(s"Success response in add Snack service")
                complete(StatusCodes.OK -> snack.asType[SnackResponseDTO])
              case Left(err) =>
                loggerInfo(s"There was an error adding the snack records $err")
                complete(StatusCodes.InternalServerError -> err)
            }
            case Failure(exception) =>
              loggerInfo(s"There was an error adding the snack record $exception")
              complete(StatusCodes.InternalServerError)
          }
        }
      }
    }
}
