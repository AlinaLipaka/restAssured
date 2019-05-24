/*
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import org.junit.Assert;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.*;
*/

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.*;

public class WheatherGetRequests {

    String baseQAURLweather = "https://api.openweathermap.org/data/2.5/weather";
    String baseQAURLgorest = "https://gorest.co.in/public-api/users";
    String baseQAURLserver = "http://localhost:3000";
    String appIdWeather = "c40d5db6986aca8507e9a2663b6fabf8";
    String rigaID = "456172";


    // simple get request
    //@Test
    public void test_01() {

        Response resp = when()
                .get("https://api.openweathermap.org/data/2.5/weather?q=London&appid=c40d5db6986aca8507e9a2663");

        System.out.println(resp.getStatusCode());
        Assert.assertEquals(resp.getStatusCode(), 200);
    }

    @Test
    public void test_01_1() {

        Response resp = when()
                .get("https://api.openweathermap.org/data/2.5/weather?q=London&appid=c40d5db6986aca8507e9a266");

        System.out.println(resp.getStatusCode());
        Assert.assertEquals(resp.getStatusCode(), 401);
        //System.out.println(resp.asString());
    }

    // Status code 500
    @Test
    public void test_02() {

        Response resp = when()
                .get("https://gorest.co.in/public-api/users?_format=json/123");

        System.out.println(resp.getStatusCode());
        Assert.assertEquals(resp.getStatusCode(), 500);
    }

    // parameters with rest-assured
    @Test
    public void test_03() {

        Response resp = given()
                .param("q", "London")
                .param("appId", appIdWeather)
                .when()
                .get(baseQAURLweather);

        System.out.println(resp.getStatusCode());
        Assert.assertEquals(resp.getStatusCode(), 200);

        if (resp.getStatusCode() == 200) {
            System.out.println("API is working fine");
        } else {
            System.out.println("API is not working fine");
        }
    }

    //Assert test case in rest-assured API
    @Test
    public void test_04() {

        ValidatableResponse resp = given()
                .param("q", "London")
                .param("appId", appIdWeather)
                .when()
                .get(baseQAURLweather)
                .then()
                .assertThat().statusCode(200);
    }

    @Test
    public void test_05() {

        Response resp = given()
                .param("q", "Riga")
                .param("appId", appIdWeather)
                .when()
                .get(baseQAURLweather);

        System.out.println(resp.asString());
    }

    @Test
    public void test_06() {
        Response resp = given()
                .param("id", "2643743")
                .param("appId", appIdWeather)
                .when()
                .get(baseQAURLweather);

        Assert.assertEquals(resp.getStatusCode(), 200);
        System.out.println(resp.asString());
    }

    @Test
    public void test_06_1() {
        Response resp = given()
                .param("access-token", "mT8JNMyWCG0D7waCHkyxo0Hm80YBqelv5SBL")
                .param("email", "test@testing.lv")
                .when()
                .get(baseQAURLgorest);

        Assert.assertEquals(resp.getStatusCode(), 200);
        System.out.println(resp.asString());
    }

    @Test
    public void testServer_07() {
        Response resp = given()
                .when()
                .get(baseQAURLserver + "/posts/1");
        System.out.println(resp.asString());
    }

    @Test
    public void test_08() {
        String weatherReport = given()
                .param("id", "2643743")
                .param("appId", appIdWeather)
                .when()
                .get(baseQAURLweather)
                .then()
                .contentType(ContentType.JSON)
                .extract()
                .path("weather[0].description");

        System.out.println("weatherReport: " + weatherReport);
    }

    @Test
    public void test_09() {

        Response resp = given()
                .param("id", "2643743")
                .param("appId", appIdWeather)
                .when()
                .get(baseQAURLweather);

        String actualWeatherReport = resp
                .then()
                .contentType(ContentType.JSON)
                .extract()
                .path("weather[0].description");

        String expectedWeatherReport = "broken clouds";

        if (actualWeatherReport.equalsIgnoreCase(expectedWeatherReport)) {
            System.out.println("Test case pass");
        } else
            System.out.println("Test case fail");
        //System.out.println("weatherReport: " + actualWeatherReport);
    }


    @Test
    public void test_10() {
        Response resp = given()
                .param("id", rigaID)
                .param("appId", appIdWeather)
                .when()
                .get(baseQAURLweather);

        String reportbyID = resp
                .then()
                .contentType(ContentType.JSON)
                .extract()
                .path("weather[0].description");

        System.out.println("Weather description by ID: " + reportbyID);

        String lon = Float.toString(resp
                .then()
                .contentType(ContentType.JSON)
                .extract()
                .path("coord.lon"));

        String lat = Float.toString(resp
                .then()
                .contentType(ContentType.JSON)
                .extract()
                .path("coord.lat"));

        System.out.println(lon + " " + lat);

        String reportByCoord = given()
                .param("lat", lat)
                .param("lon", lon)
                .param("appId", appIdWeather)
                .when()
                .get(baseQAURLweather)
                .then()
                .contentType(ContentType.JSON)
                .extract()
                .path("weather[0].description");

        System.out.println("Weather description by coordinates: " + reportByCoord);

        if (reportByCoord.equalsIgnoreCase(reportbyID)) {
            System.out.println("Test passed");
        } else
            System.out.println("Test failed");
    }

    @Test
    public void test_10_gorest() {

        Response response = given()
                .param("access-token", "13OUOf641bALRDrwbIxZg8veGMi9LfgsZDCR")
                .param("email", "queenie52@example.org")
                .when()
                .get(baseQAURLgorest);
        String newId = response
                .then()
                .contentType(ContentType.JSON)
                .extract()
                .path("result[0].id");

        System.out.println(newId);
    }

}
