import APITesting.com.org.classes.Info;
import APITesting.com.org.classes.Posts;
import APITesting.com.org.classes._Posts;
import advancedExample._Info;
import advancedExample.__Posts;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.http.ContentType;

import static org.hamcrest.Matchers.lessThan;

import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.*;

public class JasonServerRequests {

    String baseQAURLserver = "http://localhost:3000";


    //GET requests on /posts
    @Test
    public void Test_01() {
        Response resp = given()
                .when()
                .get(baseQAURLserver + "/posts");
        System.out.println("GET response is: " + resp.asString());
    }

    //POST request on /posts
    @Test
    public void Test_02() {

        Response resp = given()
                .body("  {\"id\":\"3\", \"title\":\"post title\", \"author\":\"Vasja\"}  ")
                .when()
                .contentType(ContentType.JSON)
                .post(baseQAURLserver + "/posts");

        System.out.println(resp.asString());
    }

    //POST request using Objects on /posts
    @Test
    public void Test_03() {
        Posts posts = new Posts();
        posts.setId("4");
        posts.setTitle("posts request by objects");
        posts.setAuthor("Linka");

        Response resp = given()
                .when()
                .contentType(ContentType.JSON)
                .body(posts)
                .post(baseQAURLserver + "/posts");

        System.out.println("Response through post object: " + resp.asString());
    }


    //GET
    @Test
    public void Test_04() {

        Response resp = given()
                .when()
                .get(baseQAURLserver + "/posts/3");

        System.out.println(resp.asString());
    }

    //PUT request on posts/3
    @Test
    public void Test_05() {
        Posts posts = new Posts();
        posts.setId("3");
        posts.setTitle("updated title");
        posts.setAuthor("updated author");

        Response resp = given()
                .when()
                .contentType(ContentType.JSON)
                .body(posts)
                .put(baseQAURLserver + "/posts/3");

        System.out.println("PUT API response: " + resp.asString());
    }

    //PATCH request on posts/1
    @Test
    public void Test_06() {

        Response resp = given()
                .body("  {\"title\":\"updated by PATCH request\"}  ")
                .when()
                .contentType(ContentType.JSON)
                .patch(baseQAURLserver + "/posts/1");

        System.out.println("PATCH request: " + resp.asString());
    }

    //DELETE request
    @Test
    public void Test_07() {

        Response resp = given()
                .when()
                .delete(baseQAURLserver + "/posts/4");

        System.out.println("DELETE response: " + resp);
    }

    //complex POST request
    //POST /posts
    @Test
    public void Test_08() {
        Info info = new Info();
        info.setEmail("test15@testing.lv");
        info.setPhone("123456789000");
        info.setAddress("some additional new address");

        _Posts posts = new _Posts();
        posts.setId("15");
        posts.setTitle("complex POST request");
        posts.setAuthor("same author");
        posts.setInfo(info);

        Response resp = given()
                .when()
                .contentType(ContentType.JSON)
                .body(posts)
                .post(baseQAURLserver + "/posts");

        System.out.println("Response for complex request: " + resp.asString());
    }

    //Complex post
    //POST /posts
    @Test
    public void Test_09() {

        _Info info1 = new _Info();
        info1.setEmail("test Email1");
        info1.setPhone("Test phone1");
        info1.setAddress("test address 1");

        _Info info2 = new _Info();
        info2.setEmail("test Email2");
        info2.setPhone("test Phone2");
        info2.setAddress("test address 2");

        __Posts posts = new __Posts();
        posts.setId("100");
        posts.setTitle("title");
        posts.setAuthor("author");
        posts.setInfo(new _Info[]{info1, info2});

        Response resp = given()
                .when()
                .contentType(ContentType.JSON)
                .body(posts)
                .post(baseQAURLserver + "/posts");

        System.out.println("Response is: " + resp.asString());

    }

    //Get request calculate response time
    //GET /posts
    @Test
    public void Test_10() {

        Response resp = given()
                .when()
                .get(baseQAURLserver + "/posts");

        Long time = resp
                .then()
                .extract()
                .time();

        System.out.println("Response time is: " + time);

        given()
                .when()
                .get(baseQAURLserver + "/posts")
                .then()
                .and()
                .time(lessThan(800L));

    }


}
