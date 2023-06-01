package com.mundox.management.ports.api.http.responses

import com.mundox.management.core.domain.DummyMovie

case class DummyMovieResponseDTO(id: String, title: String)

object DummyMovieResponseDTO {
  def apply(domain: DummyMovie): DummyMovieResponseDTO =
    DummyMovieResponseDTO(domain.id, domain.title)
}