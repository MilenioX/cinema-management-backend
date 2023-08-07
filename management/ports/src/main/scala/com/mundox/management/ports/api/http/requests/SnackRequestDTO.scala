package com.mundox.management.ports.api.http.requests

import cats.data.EitherT
import cats.implicits._
import com.mundox.management.core.domain.snacks.{Snack, SnackType}
import com.mundox.management.core.typeclasses.Transformer
import com.mundox.management.core.typeclasses.TransformerSyntax._
import com.mundox.management.core.validations.validators.ValidatorNec._
import monix.eval.Task

case class SnackRequestDTO(
                            id: Option[Int],
                            name: String,
                            snackType: String,
                            quantity: Int,
                            price: Double
                          )

object SnackRequestDTO {

  val nameField: String = "Name"
  val snackTypeField: String = "Snack Type"
  val quantityField: String = "Quantity"
  val priceField: String = "Price"

  implicit val snackTypeRequestTransformer: Transformer[String, SnackType] =
    {
      case "Sweet Snacks" => SnackType.Sweet
      case "Drinks" => SnackType.Drinks
      case _ => SnackType.Salty
    }

  implicit val snackRequestTransformer: Transformer[SnackRequestDTO, Snack] =
    (request: SnackRequestDTO) => Snack(0, request.name, request.snackType.asType[SnackType], request.quantity, request.price)

  def validateSnackRequest(input: SnackRequestDTO): EitherT[Task, String, SnackRequestDTO] = {
    EitherT.fromEither[Task] {
      (
        validateName(input.name),
        validateSnackType(input.snackType),
        validateNumberGreaterThanZero(quantityField, input.quantity),
        validateNumberGreaterOrEqualThanZero(priceField, input.price)
      ).mapN((_, _, _, _) => input).leftMap(v => v.toList.map(validation => validation.errorMessage).mkString(",")).toEither
    }
  }

  private def validateName(name: String): ValidationResult[String] =
    (validateLettersAndNumbersWithSpaces(nameField, name),
      validateMinLength(nameField, name, 5))
      .mapN((_,nameValidated) => nameValidated)

  private def validateSnackType(snackType: String): ValidationResult[String] =
    (validateLettersAndNumbersWithSpaces(snackTypeField, snackType),
      validateMinLength(snackTypeField, snackType, 5),
      validateMaxLength(snackTypeField, snackType, 15))
      .mapN((_, _, snackTypeValidated) => snackTypeValidated)

}
