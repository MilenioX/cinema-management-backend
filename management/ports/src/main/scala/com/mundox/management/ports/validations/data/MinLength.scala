package com.mundox.management.ports.validations.data

case class MinLength(field: String) extends Validation {
  override def errorMessage: String = s"$field has less than character allowed"
}
