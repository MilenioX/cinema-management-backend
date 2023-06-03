package com.mundox.management.ports.validations.validators

import cats.data.Chain
import cats.data.Validated.{Invalid, Valid}
import com.mundox.management.core.validations.data.{MaxLength, MinLength, ValueHasSpecialCharacters}
import com.mundox.management.core.validations.validators.ValidatorNec
import com.mundox.management.ports.TestSpec

class ValidatorNecTest extends TestSpec {

  "Validate special characters with space" should "return Invalid when the input is not valid" in {
    ValidatorNec.validateSpecialCharactersWithSpaces("field", "ab$#c -.ajs,") shouldEqual Invalid(Chain(ValueHasSpecialCharacters("field")))
  }

  "Validate special characters with space" should "return the value when the value is correct" in {
    ValidatorNec.validateSpecialCharactersWithSpaces("field", "Testing Movie") shouldEqual Valid("Testing Movie")
  }

  "Validate minLength" should "return Invalid when the input is not valid" in {
    ValidatorNec.validateMinLength("field", "abc", 5) shouldEqual Invalid(Chain(MinLength("field")))
  }

  "Validate minLength" should "return the value when the value is correct" in {
    ValidatorNec.validateMinLength("field", "abcde", 5) shouldEqual Valid("abcde")
  }

  "Validate maxLength" should "return Invalid when the input is not valid" in {
    ValidatorNec.validateMaxLength("field", "this is a testing text", 5) shouldEqual Invalid(Chain(MaxLength("field")))
  }

  "Validate maxLength" should "return the value when the value is correct" in {
    ValidatorNec.validateMaxLength("field", "Testing", 10) shouldEqual Valid("Testing")
  }
}
