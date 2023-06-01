package com.mundox.management.ports.api.http.routes

import com.mundox.management.ports.TestSpec
import com.mundox.management.ports.api.http.requests.DummyCreateMovieRequestDTO
import com.mundox.management.ports.validations.data.{MaxLength, ValueHasSpecialCharacters}

class DummyCreateMovieRequestDTOTest extends TestSpec {

  "Validation" should "return a domain object when all the fields are ok" in {
    val response = DummyCreateMovieRequestDTO.validateNec(DummyCreateMovieRequestDTO("Testing movie"))
    response.fold(_ => fail(), value => value.title shouldEqual "Testing movie")
  }

  "Validation" should "return a the first error found when the input is not valid" in {
    val response = DummyCreateMovieRequestDTO.validate(DummyCreateMovieRequestDTO("Th$s is a t3st*ng value of multiple $$$ errors returned by th@ validat@r"))
    response.fold(
      error => error shouldEqual ValueHasSpecialCharacters("title"),
      _ => fail())
  }

  "Validation" should "return a list of multiple errors when the input is not valid" in {
    val response = DummyCreateMovieRequestDTO.validateNec(DummyCreateMovieRequestDTO("Th$s is a t3st*ng value of multiple $$$ errors returned by th@ validat@r"))
    response.fold(
      errors => {
        errors.length shouldEqual 2
        errors.contains(ValueHasSpecialCharacters("title")) shouldBe true
        errors.contains(MaxLength("title")) shouldBe true
      },
      _ => fail())
  }

}
