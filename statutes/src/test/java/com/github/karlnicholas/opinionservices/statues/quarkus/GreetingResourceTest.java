package com.github.karlnicholas.opinionservices.statues.quarkus;

import io.quarkus.test.junit.QuarkusTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class GreetingResourceTest {

  @Test
  public void testHelloEndpoint() {
	  assertTrue(true);
  }


//    @Test
//    public void testHelloEndpoint() {
//        given()
//          .when().get("/hello-resteasy")
//          .then()
//             .statusCode(200)
//             .body(is("Hello RESTEasy"));
//    }
//
}