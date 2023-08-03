package com.mundox.management.ports.adapters.database.dtos

import com.mundox.management.core.typeclasses.TransformerSyntax._
import com.mundox.management.core.domain.snacks.{Snack, SnackType}
import com.mundox.management.ports.TestSpec

class SnackDTOTests extends TestSpec {

  "SnackDTO instance" should "return a Salty Snack domain model with the same information" in {
    val snackDomain = Snack(1,"Snack Name", SnackType.Salty, 100, 10)
    val transformedInstance = SnackDTO(1,"Snack Name", "Salty Snack", 100, 10).asType[Snack]
    transformedInstance == snackDomain
  }

  "SnackDTO instance" should "return a Sweet Snack domain model with the same information" in {
    val snackDomain = Snack(1000, "Snack N??$ame", SnackType.Sweet, 0, 1.99)
    val transformedInstance = SnackDTO(1000, "Snack N??$ame", "Sweet Snack", 0, 1.99).asType[Snack]
    transformedInstance == snackDomain
  }

  "SnackDTO instance" should "return a Snack of type Drinks domain model with the same information" in {
    val snackDomain = Snack(999, "Snack Name", SnackType.Drinks, 10000, 5.86)
    val transformedInstance = SnackDTO(1, "Snack name", "Drinks", 10000, 5.86).asType[Snack]
    transformedInstance == snackDomain
  }

  "SnackDTO instance" should "return a Salty Snack domain model as default type" in {
    val snackDomain = Snack(1, "Snack Name", SnackType.Salty, 56, 0.001)
    val transformedInstance = SnackDTO(1, "Snack name", "Unknown", 56, 0.001).asType[Snack]
    transformedInstance == snackDomain
  }

}
