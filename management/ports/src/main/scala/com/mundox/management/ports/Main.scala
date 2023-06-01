package com.mundox.management.ports

import com.mundox.management.core.env.log.Logger

object Main extends App with Logger {
  loggerInfo("Management service starting...");
  val env = Environment
  env.startEnvironment()
}
