package com.mundox.management.ports.mocks.adapters

import cats.data.EitherT
import com.mundox.management.core.services.SnacksService
import monix.eval.Task
import com.mundox.management.core.domain.snacks.{Snack, SnackType}

class SnackServiceMock extends SnacksService[Task] {

  override def getSnacks: EitherT[Task, String, List[Snack]] =
    EitherT.cond[Task](
      true,
      List(Snack(123, "Chocolate", SnackType.Sweet, 1, 10.20), Snack(432, "Potato chips", SnackType.Salty, 100, 4.50)),
      "Error retrieving the snacks information"
    )

  override def getSnacksById(id: Int): EitherT[Task, String, Option[Snack]] = EitherT.fromEither[Task] {
    Right(Option(Snack(123, "Chocolate", SnackType.Sweet, 1, 10.20)))
  }
}
