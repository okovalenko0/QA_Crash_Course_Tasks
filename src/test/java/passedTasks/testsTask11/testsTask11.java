package passedTasks.testsTask11;

import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class testsTask11 {

    @Test
    public void getScenarioWithout200() {
        given()
                .baseUri("https://reqres.in/")
                .pathParam("resource_id", 99)
                .log().all()
                .get("api/unknown/{resource_id}")
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_NOT_FOUND);
    }

    @Test
    public void getScenarioWith200v1() {
        given()
                .baseUri("https://reqres.in/")
                .pathParam("user_id", 2)
                .log().all()
                .get("api/users/{user_id}")
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void getScenarioWith200v2() {
        given()
                .baseUri("https://reqres.in/")
                .pathParam("page_id", 1)
                .log().all()
                .get("api/users?page={page_id}")
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void postScenario() {
        given()
                .baseUri("https://reqres.in/")
                .queryParams("name", "Alexey Kovalenko", "job", "IT Developer")
                .log().all()
                .contentType(ContentType.JSON)
                .post("api/users")
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_CREATED);
    }
}
