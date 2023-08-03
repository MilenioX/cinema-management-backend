package com.mundox.management.core.typeclasses

object TransformerSyntax {

  implicit class ToTransformerSyntax[A](value: A) {
    def asType[B](implicit instance: Transformer[A, B]): B = instance.transform(value)
  }

}
