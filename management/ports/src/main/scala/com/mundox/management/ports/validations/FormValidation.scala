package com.mundox.management.ports.validations

import com.mundox.management.ports.api.http.requests.DummyCreateMovieRequestDTO
import cats.data._
import cats.data.Validated._
import cats.syntax.all._

sealed trait FormValidation {
  def validateSpecialCharacters(field: String, value: String): Either[Validation, String] =
    Either.cond(
      value.matches("^[a-zA-Z0-9]"),
      value,
      StringHasSpecialCharacters(field)
    )

  def validateMaxLength(field: String, value: String) : Either[Validation, String] =
    Either.cond(
      value.length < 20,
      value,
      StringMaxLength(field)
    )

  def validateForm(domain: DummyCreateMovieRequestDTO): Either[Validation, DummyCreateMovieRequestDTO] = {
    for {
      _ <- validateSpecialCharacters("title", domain.title)
      _ <- validateMaxLength("title", domain.title)
    } yield domain
  }
}

object FormValidation extends FormValidation