package com.mundox.management.core.exceptions

sealed trait ManagementException extends Exception {
  def errorMsg: String
}

case class ValidationError(errorMsg: String) extends ManagementException
case class AdapterError(errorMsg: String) extends ManagementException