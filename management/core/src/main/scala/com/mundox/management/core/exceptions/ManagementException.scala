package com.mundox.management.core.exceptions

import com.mundox.management.core.validations.data.Validation

sealed trait ManagementException extends Exception {
  def errorMsg: String
}

case class AdapterError(errorMsg: String) extends ManagementException
case class ValidationError(errors: List[Validation]) extends ManagementException {
  override def errorMsg: String = errors.map(_.errorMessage).mkString(",")
}
