package com.mundox.management.ports.api.http.requests

import cats.implicits._
import cats.data.Validated
import com.mundox.management.core.exceptions.{ManagementException, ValidationError}
import com.mundox.management.core.validations.data.ValueIsNotValid
import monix.eval.Task

object CommonValidations {

  def validateId(id: String): Task[Either[ManagementException, Int]] =
    Task {
      for {
        idInt <- validateNumber(id)
        _ <- validateIdGT0(idInt)
      } yield idInt
    }

  def validateIdGT0(id: Int): Either[ManagementException, Int] =
    Validated.condNec(id > 0, id, ValueIsNotValid("Error with the id"))
        .toEither
        .leftMap(v => ValidationError(v.toList))

  def validateNumber(id: String): Either[ManagementException, Int] =
      try {
        Right(id.toInt)
      } catch {
        case _: Exception =>
          Left(ValidationError(List(ValueIsNotValid("Error with the id"))))
      }
}
