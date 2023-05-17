package com.mundox.booking.core.env.log

import org.apache.logging.log4j.scala.Logging

trait Logger extends Logging {

  def loggerInfo(msg: String): Unit =
    logger.info(msg)

  def loggerDebug(msg: String): Unit =
    logger.debug(msg)

  def loggerError(msg: String): Unit =
    logger.error(msg)
}
