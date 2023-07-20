package com.mundox.management.ports.api.http.requests

import cats.implicits._
import cats.data.Validated
import com.mundox.management.core.exceptions.{ManagementException, ValidationError}
import com.mundox.management.core.validations.data.ValueIsNotValid
import monix.eval.Task

object CommonValidations {

  def validateId(id: Int): Task[Either[ManagementException, Int]] =
    Task {
      Validated.condNec(id > 0, id, ValueIsNotValid("Error with the id"))
        .toEither
        .leftMap(v => ValidationError(v.toList))
    }
}
