package com.mundox.management.ports.api.http.requests

import cats.data.EitherT
import cats.implicits._
import com.mundox.management.core.domain.DummyMovie
import com.mundox.management.core.exceptions.{ManagementException, ValidationError}
import com.mundox.management.core.validations.data.Validation
import com.mundox.management.core.validations.validators.{Validator, ValidatorNec}

import java.util.UUID
import scala.concurrent.Future

case class DummyCreateMovieRequestDTO(title: String)

object DummyCreateMovieRequestDTO {

  def toDomain(dto: DummyCreateMovieRequestDTO, id: Option[String]): DummyMovie =
    DummyMovie(id.getOrElse(UUID.randomUUID().toString), dto.title)

  def validate(input: DummyCreateMovieRequestDTO): Either[ManagementException, DummyCreateMovieRequestDTO] =
    (for {
      _ <- Validator.validateLettersAndNumbersWithSpaces("title", input.title)
      _ <- Validator.validateMinLength("title", input.title, 5)
      title <- Validator.validateMaxLength("title", input.title, 20)
    } yield {
      DummyCreateMovieRequestDTO(title)
    }).leftMap(v => ValidationError(List(v)))

  def validateNec(input: DummyCreateMovieRequestDTO): EitherT[Future,ManagementException, DummyCreateMovieRequestDTO] =
    EitherT {
      Future.successful(
        (ValidatorNec.validateLettersAndNumbersWithSpaces("title", input.title),
          ValidatorNec.validateMinLength("title", input.title, 5),
          ValidatorNec.validateMaxLength("title", input.title, 20))
          .mapN((_, _, _) => DummyCreateMovieRequestDTO(input.title)).toEither.leftMap(validation => ValidationError(validation.toList))
      )
    }
}