package com.mundox.management.ports.api.http.responses

import com.mundox.management.core.domain.snacks.{Snack, SnackType}
import com.mundox.management.core.typeclasses.TransformerSyntax._
import com.mundox.management.ports.TestSpec

class SnackResponseDTOTest extends TestSpec {
  "Snack Response DTO instance" should "return a transformed Snack instance" in {
    val snackResponse = SnackResponseDTO(1, "Snack Name", 10)
    val transformedInstance = Snack(1, "Snack Name", SnackType.Salty, 100, 10).asType[SnackResponseDTO]
    transformedInstance == snackResponse
  }

  "Snack Response DTO instance" should "return a transformed Snack instance with special characters" in {
    val snackResponse = SnackResponseDTO(100, "Snack %$# Name", 0.99)
    val transformedInstance = Snack(100, "Snack %$# Name", SnackType.Sweet, 100, 0.99).asType[SnackResponseDTO]
    transformedInstance == snackResponse
  }

  "Snack Response DTO instance" should "return a transformed Snack instance when price is lower" in {
    val snackResponse = SnackResponseDTO(999, "Snack Name", 0.0009)
    val transformedInstance = Snack(999, "Snack Name", SnackType.Salty, 100, 0.0009).asType[SnackResponseDTO]
    transformedInstance == snackResponse
  }
}
