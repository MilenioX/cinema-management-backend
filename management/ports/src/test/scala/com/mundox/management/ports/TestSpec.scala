package com.mundox.management.ports

import akka.http.scaladsl.testkit.ScalatestRouteTest
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

trait TestSpec
  extends AnyFlatSpec
  with Matchers
  with ScalatestRouteTest
