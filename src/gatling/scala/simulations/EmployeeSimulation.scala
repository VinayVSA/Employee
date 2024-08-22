package simulations

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class EmployeeSimulation extends Simulation {

  // Define the base URL of your application
  val baseUrl = "http://localhost:8989" // Update port if different

  // Define the HTTP protocol
  val httpProtocol = http
    .baseUrl(baseUrl)
    .acceptHeader("application/json")
    .contentTypeHeader("application/json")

  // Define scenario for fetching all employees
  val scn = scenario("Get All Employees")
    .exec(
      http("Get All Employees")
        .get("/employee")
        .check(status.is(200))
    )
    .pause(2)

  // Define load simulation
  setUp(
    scn.inject(
      atOnceUsers(10),  // Number of users to simulate
      rampUsers(100) during (30.seconds) // Ramp up to 100 users over 30 seconds
    )
  ).protocols(httpProtocol)
}
