package com.mundox.management.ports.validations.validators

import com.mundox.management.ports.validations.data.{MaxLength, MinLength, Validation, ValueHasSpecialCharacters}

sealed trait Validator {

  type ValidationResult[A] = Either[Validation, A]

  private val specialCharactersPattern = "^[a-zA-Z0-9]"

  def validateSpecialCharacters(field: String, value: String): ValidationResult[String] =
    Either.cond(
      value.matches(specialCharactersPattern),
      value,
      ValueHasSpecialCharacters(field)
    )

  def validateMinLength(field: String, value: String, minLength: Int): ValidationResult[String] =
    Either.cond(
      value.length >= minLength,
      value,
      MinLength(field)
    )

  def validateMaxLength(field: String, value: String, maxLength: Int): ValidationResult[String] =
    Either.cond(
      value.length <= maxLength,
      value,
      MaxLength(field)
    )
}

object Validator extends Validator