package steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.restassured.http.ContentType;

import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.is;

public class GetPostSteps {
    @Given("^I perform GET operation for \"([^\"]*)\"$")
    public void iPerformGETOperationFor(String url) throws Throwable {
//        given().contentType(ContentType.JSON);
    }

    @And("^I perform GET for the post number \"([^\"]*)\"$")
    public void iPerformGETForThePostNumber(String postNumber) throws Throwable {
        BDDStyledMethod.SimpleGETPost(postNumber);
    }


    @Then("^I should see the author names \"([^\"]*)\"$")
    public void iShouldSeeTheAuthorNames(String name) {
        BDDStyledMethod.PerformContainCollection(name);
    }

    @Given("^I perform GET using \"([^\"]*)\" and verify if surname is \"([^\"]*)\"$")
    public void iPerformGETForUsingAndVerifyIfSurnameIs(String name, String surname) throws Throwable {
        Methods.methodGet(name, surname);
    }

    @Given("^I perform POST using \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
    public void iPerformPOSTUsing(String email, String name, String surname, String gender) throws Throwable {
        Methods.methodPost(email, name, surname, gender);
    }

    @Given("^I perform POST using:$")
    public void iPerformPOSTUsing(Map<String, String> valuesToEnter) {
        Methods.methodPostArray(valuesToEnter);
    }

    @Given("^I perform GET for users and verify result$")
    public void iPerformGETForUsersAndVerifyResult() {
        Methods.GetForCollection();
    }

    @Then("^I should verify GET parameter$")
    public void iShouldVerifyGETParameter() {
        BDDStyledMethod.performPathParameters();
    }

    @Then("^I should verify GET query parameter$")
    public void iShouldVerifyGETQueryParameter() {
        BDDStyledMethod.performQueryParameter();
    }


    @Given("^I perform DELETE on user with id \"([^\"]*)\"$")
    public void iPerformDELETEOnUserWithId(String id) throws Throwable {
        Methods.methodDelete(id);
    }

    @Given("^I perform PUT on user with email \"([^\"]*)\" using:$")
    public void iPerformPATCHOnUserWithEmailUsing(String email, Map<String, String> valuesToEnter) throws Throwable {
        Methods.methodPut(email, valuesToEnter);

    }

    @Then("^I verify that user with id \"([^\"]*)\" does not exist$")
    public void iVerifyThatUserWithIdDoesNotExist(String id) throws Throwable {
        Methods.verifyDelete(id);
    }

}

