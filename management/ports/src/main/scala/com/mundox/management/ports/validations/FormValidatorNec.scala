package com.mundox.management.ports.validations

import cats.data.ValidatedNec
import com.mundox.management.ports.api.http.requests.DummyCreateMovieRequestDTO
import cats.implicits._
import com.mundox.management.ports.validations.data.{MaxLength, Validation, ValueHasSpecialCharacters}


sealed trait FormValidatorNec {

  type ValidationResult[A] = ValidatedNec[Validation, A]

  private def validateSpecialCharacters(field: String, value: String): ValidationResult[String] =
    if (value.matches("^[a-zA-Z0-9]"))
      value.validNec[Validation]
    else
      ValueHasSpecialCharacters(field).invalidNec[String]

  private def validateMaxLength(field: String, value:String): ValidationResult[String] =
    if (value.length < 20)
      value.validNec
    else
      MaxLength(field).invalidNec

  private def validateTitle(value: String): ValidationResult[String] =
    (validateSpecialCharacters("title", value), validateMaxLength("title", value)).mapN((_,_) => value)

  def validateForm(title: String):ValidationResult[DummyCreateMovieRequestDTO] =
    (validateTitle(title)).map(DummyCreateMovieRequestDTO(_))

  def validateObject(domain: DummyCreateMovieRequestDTO): Either[List[Validation], DummyCreateMovieRequestDTO] =
    validateForm(domain.title).toEither.leftMap(_.toList)
}

object FormValidatorNec extends FormValidatorNec