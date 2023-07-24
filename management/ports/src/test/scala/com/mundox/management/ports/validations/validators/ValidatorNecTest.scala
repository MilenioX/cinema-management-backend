package com.mundox.management.ports.validations.validators

import cats.data.Chain
import cats.data.Validated.{Invalid, Valid}
import com.mundox.management.core.validations.data.{MaxLength, MinLength, ValueHasSpecialCharacters, ValueIsNotValid}
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

  "Validate is Number" should "return an Integer when the value is a number" in {
    ValidatorNec.validateIsNumber("field", "123") shouldEqual Valid(123)
  }

  "Validate is Number" should "return Invalid when the value has letters" in {
    ValidatorNec.validateIsNumber("field", "1ABC") shouldEqual Invalid(Chain(ValueIsNotValid("field")))
  }

  "Validate is Number" should "return Invalid when the value has special characters" in {
    ValidatorNec.validateIsNumber("field", "#$") shouldEqual Invalid(Chain(ValueIsNotValid("field")))
  }

  "Validate number gt zero" should "return an Integer when the value is greater than zero" in {
    ValidatorNec.validateNumberGreaterThanZero("field", 111) shouldEqual Valid(111)
  }

  "Validate number gt zero" should "return Invalid when the value is lower or equal than zero" in {
    ValidatorNec.validateNumberGreaterThanZero("field", 0) shouldEqual Invalid(Chain(ValueIsNotValid("field")))
    ValidatorNec.validateNumberGreaterThanZero("field", -101) shouldEqual Invalid(Chain(ValueIsNotValid("field")))
  }
}
