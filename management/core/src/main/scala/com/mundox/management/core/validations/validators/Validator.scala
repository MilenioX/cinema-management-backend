package com.mundox.management.core.validations.validators

import com.mundox.management.core.validations.data.{MaxLength, MinLength, Validation, ValueHasSpecialCharacters, ValueIsNotValid}

sealed trait Validator {

  type ValidationResult[A] = Either[Validation, A]

  private val lettersAndNumbersWithSpacesPattern = "^[a-zA-Z0-9 ]*"

  def validateLettersAndNumbersWithSpaces(field: String, value: String): ValidationResult[String] =
    Either.cond(
      value.matches(lettersAndNumbersWithSpacesPattern),
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

  def validateIsNumber(field: String, value: String): ValidationResult[Int] =
    try {
      Right(value.toInt)
    } catch {
      case _: NumberFormatException =>
        Left(ValueIsNotValid(field))
    }

  def validateIsDouble(field: String, value: String): ValidationResult[Double] =
    try {
      Right(value.toDouble)
    } catch {
      case _: NumberFormatException =>
        Left(ValueIsNotValid(field))
    }

  def validateNumberGreaterThanZero(field: String, value: Int): ValidationResult[Int] =
    Either.cond(
      value > 0,
      value,
      ValueIsNotValid(field)
    )

  def validateNumberGreaterOrEqualThanZero(field: String, value: Int): ValidationResult[Int] =
    Either.cond(
      value >= 0,
      value,
      ValueIsNotValid(field)
    )
}

object Validator extends Validator