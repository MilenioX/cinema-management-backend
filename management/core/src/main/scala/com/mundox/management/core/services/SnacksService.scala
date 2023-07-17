package com.mundox.management.core.services

import com.mundox.management.core.domain.snacks.Snack

trait SnacksService[F[_]] {

  def getSnacks: F[List[Snack]]

  def getSnacksById(id: Int): F[Option[Snack]]
}
