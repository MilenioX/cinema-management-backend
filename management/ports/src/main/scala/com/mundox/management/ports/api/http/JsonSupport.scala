package com.mundox.management.ports.api.http

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import com.mundox.management.ports.api.http.requests.DummyCreateMovieRequestDTO
import com.mundox.management.ports.api.http.responses.{DummyMovieResponseDTO, SnackResponseDTO}
import spray.json.{DefaultJsonProtocol, RootJsonFormat}

trait JsonSupport extends SprayJsonSupport with DefaultJsonProtocol {
  implicit val dummyCreateMovieRequestDTOFormat: RootJsonFormat[DummyCreateMovieRequestDTO] = jsonFormat1(DummyCreateMovieRequestDTO.apply)
  implicit val dummyMovieResponseDTO: RootJsonFormat[DummyMovieResponseDTO] = jsonFormat2(DummyMovieResponseDTO.apply)

  implicit val snackResponseDTO: RootJsonFormat[SnackResponseDTO] = jsonFormat3(SnackResponseDTO.apply)
}