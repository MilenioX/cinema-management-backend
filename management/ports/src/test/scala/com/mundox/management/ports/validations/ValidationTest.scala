package com.mundox.management.ports.validations

import cats.data.Validated.Invalid
import com.mundox.management.ports.TestSpec
import com.mundox.management.ports.api.http.requests.DummyCreateMovieRequestDTO

class ValidationTest extends TestSpec {

  "Validations with Either" should "return No special characters error" in {
    val errors = FormValidation.validateForm(DummyCreateMovieRequestDTO("This is an *&example of error validation for the input user received in the backEnd services"))
    errors shouldEqual Left(StringHasSpecialCharacters("title"))
  }

  "Validations with Cats" should "return No special characters and MaxLength errors" in {
    val errors = FormValidatorNec.validateObject(DummyCreateMovieRequestDTO("This is an *&example of error validation for the input user received in the backEnd services"))
    println(errors)
    errors shouldEqual Left(List(StringHasSpecialCharacters("title"), StringMaxLength("title")))

  }

}
