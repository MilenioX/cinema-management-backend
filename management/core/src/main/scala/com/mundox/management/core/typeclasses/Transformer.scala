package com.mundox.management.core.typeclasses

trait Transformer[A, B] {
  def transform(origin: A): B
}

