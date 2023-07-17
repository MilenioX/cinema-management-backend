package com.mundox.management.core.services.data

trait Repository[F[_], A, B] extends DataSource {

  def fetchAll: F[List[A]]

  def fetchOne(id: B): F[Option[A]]

  def insert(a: A): F[Option[A]]

  def update(a: A, id: B): F[Option[A]]

  def delete(id: B): F[Option[A]]
}
