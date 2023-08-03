package com.mundox.management.ports.api.http.responses

import com.mundox.management.core.domain.snacks.Snack
import com.mundox.management.core.typeclasses.Transformer

case class SnackResponseDTO(
                             id: Int,
                             name: String,
                             price: Double
                           )

object SnackResponseDTO {

  implicit val snackResponseTransformer: Transformer[Snack, SnackResponseDTO] =
    (snack: Snack) => SnackResponseDTO(snack.id, snack.name, snack.price)
}