package com.mundox.management.ports.adapters

import com.mundox.management.core.domain.snacks.Snack
import com.mundox.management.core.services.SnacksService
import com.mundox.management.core.services.data.Repository

import scala.concurrent.Future

class SnacksAdapter(repository: Repository[Future, Snack, Int]) extends SnacksService[Future] {

  override def getSnacks: Future[List[Snack]] = {
    repository.fetchAll
  }

  override def getSnacksById(id: Int): Future[Option[Snack]] =
    repository.fetchOne(id)
}
