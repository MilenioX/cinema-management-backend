package com.mundox.management.ports.api.http.routes

import akka.http.scaladsl.model.{ContentTypes, MediaTypes}
import akka.http.scaladsl.model.StatusCodes.{BadRequest, OK}
import akka.http.scaladsl.model.headers.`Content-Type`
import com.mundox.management.ports.{TestEnvironment, TestSpec}
import com.mundox.management.ports.api.http.requests.DummyCreateMovieRequestDTO
import com.mundox.management.ports.api.http.{JsonSupport, ManagementAPI}
import com.mundox.management.ports.api.http.responses.DummyMovieResponseDTO

class MovieRoutesTest extends TestSpec with JsonSupport {

  val apiRoutes: ManagementAPI = new ManagementAPI(TestEnvironment)

  val movieReq: DummyCreateMovieRequestDTO = DummyCreateMovieRequestDTO("Testing Movie")

  "The get movies service" should "return a list of movies" in {
    Get("/management/movies") ~> apiRoutes.routes ~> check {
      status shouldEqual OK
      header("Content-Type").getOrElse("Empty") shouldEqual `Content-Type`(MediaTypes.`application/json`)
      responseAs[List[DummyMovieResponseDTO]] shouldEqual List(DummyMovieResponseDTO("123", "Movie 1"), DummyMovieResponseDTO("321", "Movie 2"))
    }
  }

  "The get movies by id service" should "return a movie" in {
    Get("/management/movies/123") ~> apiRoutes.routes ~> check {
      status shouldEqual OK
      header("Content-Type").getOrElse("Empty") shouldEqual `Content-Type`(MediaTypes.`application/json`)
      responseAs[DummyMovieResponseDTO] shouldEqual DummyMovieResponseDTO("123", "Movie 1")
    }
  }

  "The movies post service" should "return the movie added" in {
    Post("/management/movies").withEntity(ContentTypes.`application/json`, "{\"title\":\"Testing Movie\"}") ~> apiRoutes.routes ~> check {
      status shouldEqual OK
      header("Content-Type").getOrElse("Empty") shouldEqual `Content-Type`(MediaTypes.`application/json`)
      responseAs[DummyMovieResponseDTO].title shouldEqual "Testing Movie"
      println(responseAs[DummyMovieResponseDTO].id)
      responseAs[DummyMovieResponseDTO].id shouldEqual "912"
    }
  }

  "The movies post service" should "return a list of error in the title movie value" in {
    Post("/management/movies").withEntity(ContentTypes.`application/json`, "{\"title\":\"This is an *&example of error validation for the input user received in the backEnd services\"}") ~> apiRoutes.routes ~> check {
      status shouldEqual BadRequest
      header("Content-Type").getOrElse("Empty") shouldEqual `Content-Type`(MediaTypes.`application/json`)
      responseAs[String] shouldEqual "title must not contain special characters.,title has more than maximum characters allowed."
    }
  }

  "The movies put service" should "return the movie updated" in {
    Put("/management/movies/123").withEntity(ContentTypes.`application/json`, "{\"title\":\"Movie updated\"}") ~> apiRoutes.routes ~> check {
      status shouldEqual OK
      header("Content-Type").getOrElse("Empty") shouldEqual `Content-Type`(MediaTypes.`application/json`)
      responseAs[DummyMovieResponseDTO].title shouldEqual "Movie updated"
      responseAs[DummyMovieResponseDTO].id shouldEqual "123"
    }
  }

  "The movies put service" should "return a list of error in the title movie value" in {
    Put("/management/movies/123").withEntity(ContentTypes.`application/json`, "{\"title\":\"This is an *&example of error validation for the input user received in the backEnd services\"}") ~> apiRoutes.routes ~> check {
      status shouldEqual BadRequest
      header("Content-Type").getOrElse("Empty") shouldEqual `Content-Type`(MediaTypes.`application/json`)
      responseAs[String] shouldEqual "title must not contain special characters.,title has more than maximum characters allowed."
    }
  }

  "The movies delete service" should "return the movie deleted" in {
    Delete("/management/movies/123") ~> apiRoutes.routes ~> check {
      status shouldEqual OK
      header("Content-Type").getOrElse("Empty") shouldEqual `Content-Type`(MediaTypes.`application/json`)
      responseAs[DummyMovieResponseDTO].title shouldEqual "Movie updated"
      responseAs[DummyMovieResponseDTO].id shouldEqual "123"
    }
  }
}


