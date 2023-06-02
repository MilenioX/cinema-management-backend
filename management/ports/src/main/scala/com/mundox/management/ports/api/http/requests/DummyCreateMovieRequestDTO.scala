package com.mundox.management.ports.api.http.requests

import cats.implicits._
import com.mundox.management.core.validations.data.Validation
import com.mundox.management.core.validations.validators.{Validator, ValidatorNec}

case class DummyCreateMovieRequestDTO(title: String)

object DummyCreateMovieRequestDTO {
  def validate(input: DummyCreateMovieRequestDTO): Either[Validation, DummyCreateMovieRequestDTO] =
    for {
      _ <- Validator.validateSpecialCharactersWithSpaces("title", input.title)
      _ <- Validator.validateMinLength("title", input.title, 5)
      title <- Validator.validateMaxLength("title", input.title, 20)
    } yield {DummyCreateMovieRequestDTO(title)}

  def validateNec(input: DummyCreateMovieRequestDTO): Either[List[Validation], DummyCreateMovieRequestDTO] =
    (ValidatorNec.validateSpecialCharactersWithSpaces("title", input.title),
      ValidatorNec.validateMinLength("title", input.title, 5),
      ValidatorNec.validateMaxLength("title", input.title, 20))
      .mapN((_, _, _) => DummyCreateMovieRequestDTO(input.title)).toEither.leftMap(_.toList)

}