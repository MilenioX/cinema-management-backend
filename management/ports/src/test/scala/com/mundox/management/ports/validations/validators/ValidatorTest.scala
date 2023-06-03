package com.mundox.management.ports.validations.validators

import com.mundox.management.core.validations.data.{MaxLength, MinLength, ValueHasSpecialCharacters}
import com.mundox.management.core.validations.validators.Validator
import com.mundox.management.ports.TestSpec

class ValidatorTest extends TestSpec {

  "Validate special characters with space" should "return Invalid when the input is not valid" in {
    Validator.validateSpecialCharactersWithSpaces("field", "ab$#c -.ajs,") shouldEqual Left(ValueHasSpecialCharacters("field"))
  }

  "Validate special characters with space" should "return the value when the value is correct" in {
    Validator.validateSpecialCharactersWithSpaces("field", "Testing Movie") shouldEqual Right("Testing Movie")
  }

  "Validate minLength" should "return Invalid when the input is not valid" in {
    Validator.validateMinLength("field", "abc", 5) shouldEqual Left(MinLength("field"))
  }

  "Validate minLength" should "return the value when the value is correct" in {
    Validator.validateMinLength("field", "abcde", 5) shouldEqual Right("abcde")
  }

  "Validate maxLength" should "return Invalid when the input is not valid" in {
    Validator.validateMaxLength("field", "this is a testing text", 5) shouldEqual Left(MaxLength("field"))
  }

  "Validate maxLength" should "return the value when the value is correct" in {
    Validator.validateMaxLength("field", "Testing", 10) shouldEqual Right("Testing")
  }
}
