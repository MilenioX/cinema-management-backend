package com.mundox.management.core.validations.data

case class ValueHasSpecialCharacters(field: String) extends Validation {
  override def errorMessage: String = s"$field must not contain special characters."
}
