package com.mundox.management.core.validations.validators

import com.mundox.management.core.validations.data.{MaxLength, MinLength, Validation, ValueHasSpecialCharacters, ValueIsNotValid}
import cats.data.ValidatedNec
import cats.implicits._

trait ValidatorNec {

  type ValidationResult[A] = ValidatedNec[Validation, A]

  private val lettersAndNumbersWithSpacesPattern = "^[a-zA-Z0-9 ]*"

  def validateLettersAndNumbersWithSpaces(field: String, value: String): ValidationResult[String] =
    if (value.matches(lettersAndNumbersWithSpacesPattern))
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

  def validateIsNumber(field: String, value: String): ValidationResult[Int] =
    try {
      value.toInt.validNec[Validation]
    } catch {
      case _: NumberFormatException =>
        ValueIsNotValid(field).invalidNec[Int]
    }

  def validateIsDouble(field: String, value: String): ValidationResult[Double] =
    try {
      value.toDouble.validNec[Validation]
    } catch {
      case _: NumberFormatException =>
        ValueIsNotValid(field).invalidNec[Double]
    }

  def validateNumberGreaterThanZero(field: String, value: Double): ValidationResult[Double] =
    if (value > 0)
      value.validNec[Validation]
    else
      ValueIsNotValid(field).invalidNec[Double]

  def validateNumberGreaterOrEqualThanZero(field: String, value: Double): ValidationResult[Double] =
    if (value >= 0)
      value.validNec[Validation]
    else
      ValueIsNotValid(field).invalidNec[Double]
}

object ValidatorNec extends ValidatorNec