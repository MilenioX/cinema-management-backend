package com.mundox.management.core.validations.data

case class ValueIsNotValid(field: String) extends Validation {
  override def errorMessage: String = s"$field has a non valid value."
}
