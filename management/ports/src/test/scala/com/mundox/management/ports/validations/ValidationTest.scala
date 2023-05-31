package com.mundox.management.ports.validations

import com.mundox.management.ports.TestSpec
import com.mundox.management.ports.api.http.requests.DummyCreateMovieRequestDTO
import com.mundox.management.ports.validations.data.{MaxLength, ValueHasSpecialCharacters}

class ValidationTest extends TestSpec {

  "Validations with Either" should "return No special characters error" in {
    val errors = FormValidation.validateForm(DummyCreateMovieRequestDTO("This is an *&example of error validation for the input user received in the backEnd services"))
    errors shouldEqual Left(ValueHasSpecialCharacters("title"))
  }

  "Validations with Cats" should "return No special characters and MaxLength errors" in {
    val errors = FormValidatorNec.validateObject(DummyCreateMovieRequestDTO("This is an *&example of error validation for the input user received in the backEnd services"))
    println(errors)
    errors shouldEqual Left(List(ValueHasSpecialCharacters("title"), MaxLength("title")))

  }

}
