package com.mundox.management.core.services

import cats.data.EitherT
import com.mundox.management.core.domain.snacks.Snack

trait SnacksService[F[_]] {

  def getSnacks: EitherT[F, String, List[Snack]]

  def getSnacksById(id: Int): F[Option[Snack]]
}
