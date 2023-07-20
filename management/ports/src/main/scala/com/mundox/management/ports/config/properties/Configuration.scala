package com.mundox.management.ports.config.properties

import pureconfig.ConfigSource
import pureconfig.generic.auto._

case class Configuration(
                        server: Server,
                        database: Database
                        )

object Configuration {
  def loadConfiguration: Option[Configuration] =
    ConfigSource.default.load[Configuration].toOption
}
