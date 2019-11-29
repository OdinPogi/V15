package example

import org.http4s.server.{Server, ServerApp}
import org.http4s.server.blaze._
import org.http4s.server.syntax._

import scalaz.concurrent.Task




object Main extends ServerApp {
  override def server(args: List[String]): Task[Server] = {

    val services =  applicationServices.helloWorldService 
    val swagService = applicationServices.swaggerService

    BlazeBuilder
      .bindHttp(8080, "localhost")
      .mountService(services, "/api")
      .mountService(swagService)
      .start
  }
}