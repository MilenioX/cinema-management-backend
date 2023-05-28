package com.mundox.management.ports.api.http.routes

import akka.http.scaladsl.model.ContentTypes
import akka.http.scaladsl.model.StatusCodes.OK
import com.mundox.management.ports.TestSpec
import com.mundox.management.ports.api.http.requests.DummyCreateMovieRequestDTO
import com.mundox.management.ports.api.http.{JsonSupport, ManagementAPI}
import com.mundox.management.ports.api.http.responses.DummyMovieResponseDTO

class MovieRoutesTest extends TestSpec with JsonSupport {

  val apiRoutes: ManagementAPI = new ManagementAPI

  val movieReq: DummyCreateMovieRequestDTO = DummyCreateMovieRequestDTO("Testing Movie")

  "The movies get service" should "return a list of movies" in {
    Get("/management/movies") ~> apiRoutes.routes ~> check {
      status shouldEqual OK
      responseAs[List[DummyMovieResponseDTO]] shouldEqual List(DummyMovieResponseDTO("123","Movie 1"),DummyMovieResponseDTO("321", "Movie 2"))
    }
  }

  "The movies post service" should "return the movie added" in {
    Post("/management/movies").withEntity(ContentTypes.`application/json`,"{\"title\":\"Testing Movie\"}") ~> apiRoutes.routes ~> check {
      status shouldEqual OK
      responseAs[DummyMovieResponseDTO].title shouldEqual "Testing Movie"
    }
  }
}


