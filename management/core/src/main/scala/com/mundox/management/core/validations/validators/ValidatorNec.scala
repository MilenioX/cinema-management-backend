package com.mundox.management.core.validations.validators

import com.mundox.management.core.validations.data.{MaxLength, MinLength, Validation, ValueHasSpecialCharacters}
import cats.data.ValidatedNec
import cats.implicits._

trait ValidatorNec {

  type ValidationResult[A] = ValidatedNec[Validation, A]

  private val specialCharactersWithSpacesPattern = "^[a-zA-Z0-9 ]*"

  def validateSpecialCharactersWithSpaces(field: String, value: String): ValidationResult[String] =
    if (value.matches(specialCharactersWithSpacesPattern))
      value.validNec[Validation]
    else
      ValueHasSpecialCharacters(field).invalidNec[String]

  def validateMinLength(field: String, value: String, minLength: Int): ValidationResult[String] =
    if (value.length >= minLength)
      value.validNec[Validation]
    else
      MinLength(field).invalidNec[String]

  def validateMaxLength(field: String, value: String, maxLength: Int): ValidationResult[String] =
    if (value.length <= maxLength)
      value.validNec[Validation]
    else
      MaxLength(field).invalidNec[String]
}

object ValidatorNec extends ValidatorNec