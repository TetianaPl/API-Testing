package jsonplaceholder;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class ApiTests {

    @Test
    public void verifyStatusCode() {
        RestAssured.given()
                .when()
                .get("http://jsonplaceholder.typicode.com/users")
                .then()
                .statusCode(200)
                .log().status();
    }

    @Test
    public void verifyResponseHeader() {
        RestAssured.given()
                .when()
                .get("http://jsonplaceholder.typicode.com/users")
                .then()
                .header("Content-Type", notNullValue())
                .header("Content-Type", equalTo("application/json; charset=utf-8"))
                .log().headers();
    }

    @Test
    public void verifyResponseBody() {
        RestAssured.given()
                .when()
                .get("http://jsonplaceholder.typicode.com/users")
                .then()
                .body("id.size()", equalTo(10))
                .log().body();
    }
}