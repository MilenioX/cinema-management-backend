package com.mundox.management.ports.validations.data

case class ValueHasSpecialCharacters(field: String) extends Validation {
  override def errorMessage: String = s"$field must not contain special characters."
}
