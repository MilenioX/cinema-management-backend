package com.mundox.management.ports.adapters

import com.mundox.management.core.domain.snacks.{Snack, SnackType}
import com.mundox.management.core.env.log.Logger
import com.mundox.management.core.services.SnacksService
import com.mundox.management.core.services.data.Repository
import com.mundox.management.ports.adapters.database.dtos.SnackDTO
import monix.eval.Task

class SnacksAdapter(repository: Repository[Task, SnackDTO, Int]) extends SnacksService[Task] with Logger {

  override def getSnacks: Task[List[Snack]] = {
    loggerInfo(s"Get Snacks service called")
    repository.fetchAll.map(_.map(s => Snack(s.id, s.name, SnackType.Sweet, s.quantity, s.price)))
  }

  override def getSnacksById(id: Int): Task[Option[Snack]] =
    repository.fetchOne(id).map(_.map(s => Snack(s.id, s.name, SnackType.Sweet, s.quantity, s.price)))
}
