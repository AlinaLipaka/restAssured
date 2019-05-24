package steps;

import io.restassured.http.ContentType;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.*;

public class Methods {

    // not working
    public static void GetForCollection() {
        given().contentType(ContentType.JSON)
                .when().get("https://gorest.co.in/public-api/users?_format=json&access-token=mT8JNMyWCG0D7waCHkyxo0Hm80YBqelv5SBL")
                .then().body("result.email[0]", containsInAnyOrder("tommie73@example.net", "test@testing4.lv", "test@testing3.lv"));
    }

    public static void methodGet(String name, String surname) {
        given()
                .contentType(ContentType.JSON)
                .when()
                .get(String.format("https://gorest.co.in/public-api/users?_format=json&access-token=mT8JNMyWCG0D7waCHkyxo0Hm80YBqelv5SBL&first_name=%s", name))
                .then()
                .statusCode(200)
                .body("result.last_name[0]", is(surname));
    }

    public static void methodPost(String email, String name, String surname, String gender) {
        HashMap<String, String> person = new HashMap<String, String>();

        person.put("email", email);
        person.put("first_name", name);
        person.put("last_name", surname);
        person.put("gender", gender);

        given()
                .contentType(ContentType.JSON)
                .with().body(person)
                .when()
                .post("https://gorest.co.in/public-api/users?access-token=mT8JNMyWCG0D7waCHkyxo0Hm80YBqelv5SBL");
    }

    public static void methodPatch(String email, String newEmail, String newName, String newSurname, String newGender){
        HashMap<String, String> person = new HashMap<String, String>();

        person.put("email", email);
        person.put("first_name", newName);
        person.put("last_name", newSurname);
        person.put("gender", newGender);

        given()
                .contentType(ContentType.JSON)
                .with().body(person)
                .when()
                .patch("https://gorest.co.in/public-api/users?access-token=mT8JNMyWCG0D7waCHkyxo0Hm80YBqelv5SBL");


    }

    public static void methodPostArray(Map<String, String> valuesToEnter) {

        given()
            .contentType(ContentType.JSON)
            .with()
            .body(valuesToEnter)
        .when()
            .post("https://gorest.co.in/public-api/users?access-token=mT8JNMyWCG0D7waCHkyxo0Hm80YBqelv5SBL");
    }

    public static void methodDelete(String id){
        given()
                .contentType(ContentType.JSON)
                .when()
                .delete(String.format("https://gorest.co.in/public-api/users?_format=json&access-token=mT8JNMyWCG0D7waCHkyxo0Hm80YBqelv5SBL&id=%s", id));
    }

    public static void verifyDelete(String id){
        given()
                .contentType(ContentType.JSON)
                .when()
                .get(String.format("https://gorest.co.in/public-api/users?_format=json&access-token=mT8JNMyWCG0D7waCHkyxo0Hm80YBqelv5SBL&id=%s", id))
                .getBody().print();
    }

    public static void methodPut(String email, Map<String, String> valuesToEnter){
        given()
                .contentType(ContentType.JSON)
                .with()
                .body(valuesToEnter)
                .when()
                .put(String.format("https://gorest.co.in/public-api/users?_format=json&access-token=mT8JNMyWCG0D7waCHkyxo0Hm80YBqelv5SBL&email=%s", email));

    }

}
