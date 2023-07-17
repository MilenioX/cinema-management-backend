package com.mundox.management.core.domain.snacks

trait SnackType

object SnackType {
  case object Salty extends SnackType
  case object Sweet extends SnackType
  case object Drinks extends SnackType
}
