package com.mundox.management.ports.validations.validators

import com.mundox.management.core.validations.data.{MaxLength, MinLength, ValueHasSpecialCharacters, ValueIsNotValid}
import com.mundox.management.core.validations.validators.Validator
import com.mundox.management.ports.TestSpec

class ValidatorTest extends TestSpec {

  "Validate special characters with space" should "return Invalid when the input is not valid" in {
    Validator.validateLettersAndNumbersWithSpaces("field", "ab$#c -.ajs,") shouldEqual Left(ValueHasSpecialCharacters("field"))
  }

  "Validate special characters with space" should "return the value when the value is correct" in {
    Validator.validateLettersAndNumbersWithSpaces("field", "Testing Movie") shouldEqual Right("Testing Movie")
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

  "Validate is Number" should "return an Integer when the value is a number" in {
    Validator.validateIsNumber("field", "123") shouldEqual Right(123)
  }

  "Validate is Number" should "return Invalid when the value has letters" in {
    Validator.validateIsNumber("field", "1ABC") shouldEqual Left(ValueIsNotValid("field"))
  }

  "Validate is Number" should "return Invalid when the value has special characters" in {
    Validator.validateIsNumber("field", "#$") shouldEqual Left(ValueIsNotValid("field"))
  }

  "Validate number gt zero" should "return an Integer when the value is greater than zero" in {
    Validator.validateNumberGreaterThanZero("field", 111) shouldEqual Right(111)
  }

  "Validate number gt zero" should "return Invalid when the value is lower or equal than zero" in {
    Validator.validateNumberGreaterThanZero("field", 0) shouldEqual Left(ValueIsNotValid("field"))
    Validator.validateNumberGreaterThanZero("field", -101) shouldEqual Left(ValueIsNotValid("field"))
  }

}
