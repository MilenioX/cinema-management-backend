package com.mundox.management.ports.validations.data

case class MaxLength(field: String) extends Validation {
  override def errorMessage: String = s"$field has more than maximum characters allowed."
}
