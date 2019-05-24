package steps;

import com.jayway.restassured.http.ContentType;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import java.util.Map;


import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.is;

public class NewGetPostSteps {
    @Given("^I perform GET operation for the \"([^\"]*)\"$")
    public void iPerformGETOperationForThe(String url) throws Throwable {
      //  given().contentType(ContentType.JSON);
    }

    @And("^I perform GET the post number \"([^\"]*)\"$")
    public void iPerformGETThePostNumber(String postNumber) throws Throwable {
       NewBDDStyledMethod.simpleGETPost(postNumber);

    }

    @Then("^I should see the author name as \"([^\"]*)\"$")
    public void iShouldSeeTheAuthorNameAs(String arg0) throws Throwable {

    }

    @Then("^I should see the author names$")
    public void iShouldSeeTheAuthorNames() {
        NewBDDStyledMethod.PerformContainsCollection();
    }

    @Then("^I should verify GET parameters$")
    public void iShouldVerifyGETParameters() {
        NewBDDStyledMethod.verifyPathParameter();
    }

    @Then("^I should verify query parameter$")
    public void iShouldVerifyQueryParameter() {
        NewBDDStyledMethod.performQueryParameter();
    }
}
