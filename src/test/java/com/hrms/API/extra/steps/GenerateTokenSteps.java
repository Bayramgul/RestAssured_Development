package com.hrms.API.extra.steps;

import static com.hrms.utils.APIConstants.*;
import static com.hrms.utils.CommonMethods.readJson;
import static io.restassured.RestAssured.given;

import org.json.JSONObject;
import org.junit.Assert;

import com.hrms.utils.ReadWriteFile;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GenerateTokenSteps {
	private RequestSpecification request;
	private Response response;
	String URI = "http://166.62.36.207/syntaxapi/api/generateToken.php";

	@When("I provide the header for Generating Token")
	public void i_provide_the_header_for_Generating_Token() {
		request = given().header("Content-Type", "application/json");
	}

	@When("I provide the body for Generating Token")
	public void i_provide_the_body_for_Generating_Token() {
		// 1st way, just provide the string
//		String bodyText = "{ \"email\": \"syntaxAPI1@gmail.com\", \"password\": \"sampleapi123\" }";
//		request.body(bodyText);

		// 2nd way, read text from the file
		request.body(readJson(GENERATE_TOKEN_JSON));
	}

	@When("I make Generating Token call")
	public void i_make_Generating_Token_call() {
		response = request.when().post(URI);
	}

	@Then("I validate the Generating Token status code")
	public void i_validate_the_Generating_Token_status_code() {
		// 1st way, RestAssured Assertion
		response.then().assertThat().statusCode(200);

		// 2nd way, JUnit Assertion
		int statusCode = response.getStatusCode();
		Assert.assertEquals(200, statusCode);
	}

	@Then("I get the token from the response and save in a file")
	public void i_get_the_token() {
		System.out.println();
		System.out.println("1st way " + response.asString());
		System.out.println("2nd way " + response.body().asString());
		System.out.println("3rd way " + response.getBody().asString());

		// 1st way, using JsonPath
		JsonPath jsonPath = response.jsonPath();
//		jsonPath.prettyPrint();
		String jsonPathToken = jsonPath.getString("token");
		System.out.println("jsonPathToken " + jsonPathToken);

		// 2nd way, using JsonObject
		JSONObject json = new JSONObject(response.asString());
		String jsonObjectToken = json.getString("token");
		System.out.println("jsonObjectToken " + jsonObjectToken);

		ReadWriteFile.writeTextIntoFile(TOKEN_TXT, "Bearer " + jsonPathToken);

	}

}
