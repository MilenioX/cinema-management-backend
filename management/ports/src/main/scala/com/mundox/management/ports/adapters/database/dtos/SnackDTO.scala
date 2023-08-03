package com.mundox.management.ports.adapters.database.dtos

import com.mundox.management.core.domain.snacks.{Snack, SnackType}
import com.mundox.management.core.typeclasses.Transformer
import com.mundox.management.core.typeclasses.TransformerSyntax._


case class SnackDTO(
                   id: Int,
                   name: String,
                   snackType: String,
                   quantity: Int,
                   price: Double
                   )

object SnackDTO {

  implicit val snackTypeDBTransformer: Transformer[String, SnackType] = {
    case "Sweet Snacks" => SnackType.Sweet
    case "Drinks" => SnackType.Drinks
    case _ => SnackType.Salty
  }

  implicit val snackDBTransformer: Transformer[SnackDTO, Snack] =
    (origin: SnackDTO) => Snack(
      origin.id,
      origin.name,
      origin.snackType.asType[SnackType],
      origin.quantity,
      origin.price)
}