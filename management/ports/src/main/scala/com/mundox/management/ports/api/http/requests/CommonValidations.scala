package com.mundox.management.ports.api.http.requests

import cats.implicits._
import com.mundox.management.core.exceptions.{ManagementException, ValidationError}
import com.mundox.management.core.validations.validators.Validator._
import monix.eval.Task

object CommonValidations {

  def validateId(id: String): Task[Either[ManagementException, Int]] =
    Task {
      (for {
        idInt <- validateIsNumber("id", id)
        _ <- validateNumberGreaterThanZero("id", idInt)
      } yield idInt).leftMap(v => ValidationError(List(v)))
    }
}
