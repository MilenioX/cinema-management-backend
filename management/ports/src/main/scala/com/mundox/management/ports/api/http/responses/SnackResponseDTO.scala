package com.mundox.management.ports.api.http.responses

import com.mundox.management.core.domain.snacks.Snack

case class SnackResponseDTO(
                     id: Int,
                     name: String,
                     price: Double
                   )

object SnackResponseDTO {
  def apply(snack: Snack): SnackResponseDTO =
    SnackResponseDTO(snack.id, snack.name, snack.price)
}