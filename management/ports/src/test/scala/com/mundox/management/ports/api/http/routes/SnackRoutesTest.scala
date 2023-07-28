package com.mundox.management.ports.api.http.routes

import akka.http.scaladsl.model.MediaTypes
import akka.http.scaladsl.model.StatusCodes.{InternalServerError, NotFound, OK}
import akka.http.scaladsl.model.headers.`Content-Type`
import com.mundox.management.ports.api.http.responses.SnackResponseDTO
import com.mundox.management.ports.{TestEnvironment, TestSpec}
import com.mundox.management.ports.api.http.{JsonSupport, ManagementAPI}

class SnackRoutesTest extends TestSpec with JsonSupport {

  val apiRoutes: ManagementAPI = new ManagementAPI(TestEnvironment)

  "The get snacks service" should "return a list of snacks" in {
    Get("/management/snacks") ~> apiRoutes.routes ~> check {
      status shouldEqual OK
      header("Content-Type").getOrElse("Empty") shouldEqual `Content-Type`(MediaTypes.`application/json`)
      responseAs[List[SnackResponseDTO]] shouldEqual List(SnackResponseDTO(123, "Chocolate", 10.20), SnackResponseDTO(432, "Potato chips", 4.50))
    }
  }

  "The get snacks by id service" should "return a snack" in {
    Get("/management/snacks/123") ~> apiRoutes.routes ~> check {
      status shouldEqual OK
      header("Content-Type").getOrElse("Empty") shouldEqual `Content-Type`(MediaTypes.`application/json`)
      responseAs[SnackResponseDTO] shouldEqual SnackResponseDTO(123, "Chocolate", 10.20)
    }
  }

  "The get snacks by id service" should "return a validation error" in {
    Get("/management/snacks/abc") ~> apiRoutes.routes ~> check {
      status shouldEqual InternalServerError
      header("Content-Type").getOrElse("Empty") shouldEqual `Content-Type`(MediaTypes.`application/json`)
      responseAs[String] shouldEqual "id has a non valid value."
    }
  }
}
