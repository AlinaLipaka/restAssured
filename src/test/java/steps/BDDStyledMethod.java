package steps;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsString;

public class BDDStyledMethod {

    public static void SimpleGETPost(String postNumber) {
        given().contentType(ContentType.JSON).
        when().get(String.format("https://my-json-server.typicode.com/typicode/demo/comments/%s", postNumber))
                .then().body("postId", is(1));
    }

    public static void PerformContainCollection(String name) {
        given()
                .contentType(ContentType.JSON)
        .when()
                .get(String.format("https://gorest.co.in/public-api/users?_format=json&access-token=mT8JNMyWCG0D7waCHkyxo0Hm80YBqelv5SBL&first_name=%s", name))
        .then()
                .body("result.last_name[0]", is ("Grant"));

/*
        Response response = get("https://gorest.co.in/public-api/users?_format=json&access-token=mT8JNMyWCG0D7waCHkyxo0Hm80YBqelv5SBL&first_name=Johnson");
        JSONArray jsonResponse = new JSONArray(response.body().asString());
        String surname = jsonResponse.getJSONObject(0).getString("last_name");
        Assert.assertEquals(surname, "Luettgen");
*/

    }

    public static void performPathParameters(){

        given()
                .contentType(ContentType.JSON)
                .with()
                .pathParams("post", 2)
                .when()
                .get("https://my-json-server.typicode.com/typicode/demo/comments/{post}")
                .then()
                .body("body", containsString("some comment"));
    }

    public static void performQueryParameter(){

        given()
            .contentType(ContentType.JSON)
            .queryParam("id", 1)
        .when()
            .get("https://my-json-server.typicode.com/typicode/demo/comments/")
        .then()
            .body("body", hasItem("some comment"));

    }
}
