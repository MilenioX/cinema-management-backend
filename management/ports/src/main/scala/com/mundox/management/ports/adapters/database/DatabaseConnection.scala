package com.mundox.management.ports.adapters.database

import com.mundox.management.ports.config.properties.Database
import doobie.Transactor
import monix.eval.Task

trait DatabaseConnection {

  protected def getTransactor(config: Database): Transactor[Task] =
    Transactor.fromDriverManager[Task](
      config.driver,
      config.getDatabaseUrl,
      config.user,
      config.password
    )
}
