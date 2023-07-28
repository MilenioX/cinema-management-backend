package com.mundox.management.core.queries

import cats.data.EitherT
import com.mundox.management.core.domain.snacks.Snack
import com.mundox.management.core.env.log.Logger
import com.mundox.management.core.exceptions.ManagementException
import com.mundox.management.core.services.SnacksService

class SnacksQuery[F[_]](service: SnacksService[F]) extends Logger {

  def getSnacks:EitherT[F, String, List[Snack]] = {
    logger.info(s"Get Snacks query called")
    service.getSnacks
  }

  def getSnackById(id: Int): EitherT[F, String, Option[Snack]] = {
    logger.info(s"Get Snacks By Id query called with id: $id")
    service.getSnacksById(id)
  }

}
