package com.mundox.management.ports.adapters.database

import com.mundox.management.core.domain.snacks.{Snack, SnackType}
import com.mundox.management.core.services.data.Repository

import scala.concurrent.{ExecutionContext, Future}

class SnackRepository()(implicit ec: ExecutionContext) extends Repository[Future, Snack, Int] {

  private var snacks: List[Snack] = List(
    Snack(1, "Nachos", SnackType.Salty, 50, 6.75),
    Snack(2, "Chocolate", SnackType.Sweet, 80, 1),
    Snack(3, "Candy", SnackType.Sweet, 150, 1),
    Snack(4, "Coca Cola", SnackType.Drinks, 200, 4.30),
  )

  override def fetchAll: Future[List[Snack]] =
    Future.apply {
      snacks
    }

  override def fetchOne(id: Int): Future[Option[Snack]] = Future {
    snacks.find(_.id == id)
  }

  override def insert(snack: Snack): Future[Option[Snack]] = Future {
    snacks = snack +: snacks
    Some(snack)
  }

  override def update(newSnack: Snack, id: Int): Future[Option[Snack]] = Future {
    snacks = snacks.map(snack => if (snack.id == id) newSnack else snack)
    snacks.find(_.id == id)
  }

  override def delete(id: Int): Future[Option[Snack]] = Future {
    val deleted = snacks.find(_.id == id)
    snacks = snacks.filter(_.id != id)
    deleted
  }
}
