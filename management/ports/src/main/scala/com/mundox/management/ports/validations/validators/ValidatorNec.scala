package com.mundox.management.ports.validations.validators

import cats.data.ValidatedNec
import cats.implicits._
import com.mundox.management.ports.validations.data.{MaxLength, MinLength, Validation, ValueHasSpecialCharacters}

trait ValidatorNec {

  type ValidationResult[A] = ValidatedNec[Validation, A]

  private val specialCharactersPattern = "^[a-zA-Z0-9]"

  def validateSpecialCharacters(field: String, value: String): ValidationResult[String] =
    if (value.matches(specialCharactersPattern))
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