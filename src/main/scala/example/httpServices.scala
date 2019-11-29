package example

import org.http4s._
import org.http4s.dsl._
import scalaz.concurrent.Task
import scala.concurrent.ExecutionContext



object applicationServices {

lazy val   swaggerUiDir = "/swagger"

def swaggerService(implicit executionContext: ExecutionContext = ExecutionContext.global) : HttpService = HttpService {
  case req@GET -> Root / "css" / rest => fetchResource(swaggerUiDir + req.pathInfo, req)
  case req@GET -> Root / "swagger" => fetchResource(swaggerUiDir + "/index.html", req)  

}

def fetchResource(path: String, req : Request) : Task[Response] = {
  StaticFile.fromResource(path, Some(req))
  .map(Task.now)
  .getOrElse(NotFound())
}

val helloWorldService = HttpService {
  case GET -> Root / "hello" / name =>
    Ok(s"Hello, $name.")
}
}