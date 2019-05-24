import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ApiTest {
    @Test()
    public void testGet() {
        Response response1 =
                given()
                        .log().all()
                        .when()
                        .get("http://httpbin.org/get?a=1")
                        .then()
                        .log().all()
                        .statusCode(200)
                        .body("headers.Host", equalTo("httpbin.org"))
                        .body("args.a", equalTo("1"))
                        .extract()
                        .response();
        response1.getBody().print();
    }

    @Test()
    public void testPost() {
        long threadId = Thread.currentThread().getId();
        System.out.println("Thread post: " + threadId);

        Map<String, String> data = new HashMap<>();
        data.put("orderId", "2");

        given()
                .contentType(ContentType.JSON)
                .body(data)
                .when()
                .post("http://httpbin.org/post")
                .then()
                .statusCode(200)
                .body("json.orderId", equalTo("2"));
    }

}
