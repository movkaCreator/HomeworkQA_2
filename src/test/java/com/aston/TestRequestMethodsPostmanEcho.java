package com.aston;

import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


public class TestRequestMethodsPostmanEcho {
    @Test
    @DisplayName("Тестирование запроса \"GET Request\"")
    public void testGetRequest() {
        given()
                .baseUri("https://postman-echo.com/")
                .queryParam("foo1", "bar1")
                .queryParam("foo2", "bar2")
                .when()
                .get("/get")
                .then()
                .assertThat().statusCode(200)
                .body("args.foo1", equalTo("bar1"))
                .body("args.foo2", equalTo("bar2"));
    }

    @Test
    @DisplayName("Тестирование запроса \"POST Raw Text\"")
    public void testPostRawText() {
        String response = "This is expected to be sent back as part of response body.";
        given()
                .baseUri("https://postman-echo.com/")
                .contentType("text/plain")
                .body(response)
                .when()
                .post("/post")
                .then()
                .assertThat().statusCode(200)
                .body("data", equalTo(response));
    }

    @Test
    @DisplayName("Тестирование запроса \"POST From Data\"")
    public void testPostFormData() {
        given()
                .baseUri("https://postman-echo.com/")
                .contentType("application/x-www-form-urlencoded; charset=utf-8")
                .formParam("foo1", "bar1")
                .formParam("foo2", "bar2")
                .when()
                .post("/post")
                .then()
                .assertThat().statusCode(200)
                .body("form.foo1", equalTo("bar1"))
                .body("form.foo2", equalTo("bar2"));
    }

    @Test
    @DisplayName("Тестирование запроса \"Put Request\"")
    public void testPutRequest() {
        String response = "This is expected to be sent back as part of response body.";
        given()
                .baseUri("https://postman-echo.com/")
                .contentType("text/plain")
                .body(response)
                .when()
                .put("/put")
                .then()
                .assertThat().statusCode(200)
                .body("data", equalTo(response));
    }

    @Test
    @DisplayName("Тестирование запроса \"Patch Request\"")
    public void testPatchRequest() {
        String response = "This is expected to be sent back as part of response body.";
        given()
                .baseUri("https://postman-echo.com/")
                .contentType("text/plain")
                .body(response)
                .when()
                .patch("/patch")
                .then()
                .assertThat().statusCode(200)
                .body("data", equalTo(response));
    }

    @Test
    @DisplayName("Тестирование запроса \"Delete Request\"")
    public void testDeleteRequest() {
        given()
                .baseUri("https://postman-echo.com/")
                .contentType("text/plain")
                .body("This is expected to be sent back as part of response body.")
                .when()
                .delete("/delete")
                .then()
                .statusCode(200);
    }
}