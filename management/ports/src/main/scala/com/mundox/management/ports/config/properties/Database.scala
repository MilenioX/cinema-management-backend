package com.mundox.management.ports.config.properties

case class Database(
                     driver: String,
                     connectionUrl: String,
                     databaseUrl: String,
                     database: String,
                     user: String,
                     password: String
                   ) {

  def getDatabaseUrl: String =
    String.format(connectionUrl, String.format(databaseUrl, database))
}

