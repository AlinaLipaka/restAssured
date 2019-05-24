package steps;


import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.containsString;

public class NewBDDStyledMethod {

    static String baseURLposts = "http://localhost:3000/posts/";
    static String baseURLgorest = "https://gorest.co.in/public-api/users";

    public static void simpleGETPost(String postNumber) {

        given().contentType(ContentType.JSON)
                .when()
                .get(baseURLposts + postNumber)
                .then()
                .body("author", is("Vasja"));
    }

    public static void PerformContainsCollection() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .log().all()
                .get(baseURLposts)
                .then()
                .body("author", containsInAnyOrder("Linka", "Vasja", "typicode"))
                .statusCode(200)
                .log().all();
    }

    public static void verifyPathParameter(){
        given()
                .contentType(ContentType.JSON)
                .with()
                .pathParams("post", 3)
                .when()
                .get(baseURLposts + "{post}")
                .then()
                .body("author", containsString("Vasja"));
    }

    public static void performQueryParameter(){
        given()
                .contentType(ContentType.JSON)
                .queryParam("id", 3)
                .when()
                .get(baseURLposts)
                .then()
                .body("author", hasItem("Vasja"));
    }








}
