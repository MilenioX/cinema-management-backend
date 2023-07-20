package com.mundox.management.ports.api.http

import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import com.mundox.management.core.env.log.Logger
import com.mundox.management.ports.config.properties.Server

import scala.concurrent.{ExecutionContext, Future}
import scala.io.StdIn
import scala.util.{Failure, Success}

class HttpServer(apiRoutes: ManagementAPI, config: Server) extends Logger{

  private implicit val system:ActorSystem[_] = ActorSystem(Behaviors.empty, "ManagementService")
  private implicit val ec: ExecutionContext = system.executionContext

  def startHttpServer(): Unit = {
    val httpBindingFuture: Future[Http.ServerBinding] = Http()
      .newServerAt("localhost", config.port)
      .bind(apiRoutes.routes())

    httpBindingFuture.onComplete {
      case Success(binding) =>
        val address = binding.localAddress
        loggerInfo(s"Server online at http://${address.getHostString}:${address.getPort}\nPress RETURN to stop...")
        StdIn.readLine()
        httpBindingFuture
          .flatMap(_.unbind())
          .onComplete(_ => system.terminate())
      case Failure(ex) =>
        loggerError(s"Failed to bind HTTP Server, because ex: $ex")
        system.terminate()
    }
  }


}
