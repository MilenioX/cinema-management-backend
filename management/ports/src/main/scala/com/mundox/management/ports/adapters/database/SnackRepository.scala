package com.mundox.management.ports.adapters.database

import com.mundox.management.core.services.data.Repository
import com.mundox.management.ports.adapters.database.daos.SnacksDAO
import com.mundox.management.ports.adapters.database.dtos.SnackDTO
import com.mundox.management.ports.config.properties.Database
import doobie.implicits._
import doobie.util.fragment.Fragment
import monix.eval.Task


class SnackRepository(config: Database) extends Repository[Task, SnackDTO, Int] with DatabaseConnection {

  private var snacks: List[SnackDTO] = List(
    SnackDTO(1, "Nachos", "SnackType.Salty", 50, 6.75),
    SnackDTO(2, "Chocolate", "SnackType.Sweet", 80, 1),
    SnackDTO(3, "Candy", "SnackType.Sweet", 150, 1),
    SnackDTO(4, "Coca Cola", "SnackType.Drinks", 200, 4.30),
  )

  override def fetchAll: Task[List[SnackDTO]] = {
    Fragment.const(SnacksDAO.getAllElements)
        .query[SnackDTO]
        .stream.compile.toList
        .transact(getTransactor(config))
  }

  override def fetchOne(id: Int): Task[Option[SnackDTO]] =
    Fragment.const(SnacksDAO.getSnacksById(id.toString))
      .query[SnackDTO]
      .stream.take(1)
      .compile
      .last
      .transact(getTransactor(config))

  override def insert(snack: SnackDTO): Task[Option[SnackDTO]] = Task.pure {
    snacks = snack +: snacks
    Some(snack)
  }

  override def update(newSnack: SnackDTO, id: Int): Task[Option[SnackDTO]] = Task {
    snacks = snacks.map(snack => if (snack.id == id) newSnack else snack)
    snacks.find(_.id == id)
  }

  override def delete(id: Int): Task[Option[SnackDTO]] = Task.pure {
    val deleted = snacks.find(_.id == id)
    snacks = snacks.filter(_.id != id)
    deleted
  }
}
