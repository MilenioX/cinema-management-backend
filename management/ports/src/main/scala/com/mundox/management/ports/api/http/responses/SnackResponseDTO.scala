package com.mundox.management.ports.api.http.responses

import com.mundox.management.core.domain.snacks.Snack

case class SnackDTO(
                     id: Int,
                     name: String,
                     price: Double
                   )

object SnackDTO {
  def apply(snack: Snack): SnackDTO =
    SnackDTO(snack.id, snack.name, snack.price)
}