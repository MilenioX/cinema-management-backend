package com.mundox.management.ports.validations

sealed trait Validation {
  def errorMessage: String
}

case class StringHasSpecialCharacters(field: String) extends Validation {
  override def errorMessage: String = s"$field must not contain special characters."
}

case class StringMaxLength(field: String) extends Validation {
  override def errorMessage: String = s"$field has many characters."
}