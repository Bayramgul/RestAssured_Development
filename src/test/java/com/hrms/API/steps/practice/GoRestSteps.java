package com.hrms.API.steps.practice;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.json.JSONObject;
import org.junit.Assert;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GoRestSteps {

	private static RequestSpecification request;
	private Response response;

	@When("I provide the request data")
	public void i_provide_the_request_data() {
		//
		request = given()
				// Content-Type is not mandatory if you're not sending body/payload
				// .header("Content-Type", "application/json")

				// I got this Bearer Token when I logged in https://gorest.co.in/
				// you can get your Bearer Token
				.header("Authorization", "Bearer 059MHCzH0kLQS_wTwCMeniTFjKrLvtSTE7fM");

		// This statement will log the request information when an API call is made
		// request.log().all();
	}

	@When("I make a call to albums API")
	public void i_make_a_call_to_albums_API() {
		int albumId = 4;
		// At this moment we're making an API call
		// albumId is a path parameter, not query parameter
		response = request.when().get("https://gorest.co.in/public-api/albums/" + albumId);
	}

	@Then("I validate that {int} is the status code")
	public void i_validate_that_is_the_status_code(int expectedStatus) {
		// This statement prints the response body in a pretty format
		// response.prettyPrint();

		// 1st way, using RestAssure assert
		response.then().assertThat().statusCode(expectedStatus);

		// 2nd way, using JUnit Assert
		int actualStatus = response.statusCode();
		Assert.assertEquals(expectedStatus, actualStatus);
	}

	@Then("I validate the response body")
	public void i_validate_the_response_body() {
		// 1st way, using RestAssure assert
		String expectedTitle = "Quos culpa est nemo aspernatur.";
		response.then().body("_meta.message", equalTo("OK. Everything worked as expected."));
		response.then().body("result.title", equalTo(expectedTitle));
		response.then().body("result._links.self.href", equalTo("https://gorest.co.in/public-api/albums/4"));

		// 2nd way, using JsonPath and JUnit Assert
		JsonPath jsonPath = response.jsonPath();
		String result = jsonPath.prettify(); // prettify doesn't print. prettyPrint does print
		System.out.println(result);

		System.out.println();

		// This is how you get the "title" which is child of "result"
		String actualTitle = jsonPath.getString("result.title");
		System.out.println("result.title is: " + actualTitle);
		Assert.assertEquals(expectedTitle, actualTitle);

		// This is how you get the "code" which is child of "_meta"
		// Please note that code is an int
		int code = jsonPath.get("_meta.code");
		System.out.println("_meta.code is: " + code);

		// This is when you have a list, which we don't have...
		// JsonPath jsonPath = response.jsonPath();
		// List<Object> albums = jsonPath.get("result");
		// Object album = albums.get(0);
		// System.out.println(album);

		System.out.println("\n---------------- 3rd way ----------------");
		// 3rd way, using JSONObject and JUnit Assert
		JSONObject jsonObject = new JSONObject(response.asString());
		JSONObject meta = jsonObject.getJSONObject("_meta");
		JSONObject rateLimit = meta.getJSONObject("rateLimit");
		System.out.println("rateLimit json object is: " + rateLimit);

		// 3.a You can get the limit using getInt
		int limitInt = rateLimit.getInt("limit");
		System.out.println(limitInt);

		// 3.b You can get the limit using get and casting into integer
		int limitInt2 = (int) rateLimit.get("limit");
		System.out.println(limitInt2);

		// 3.c will not work because limit is not a String type
		// String limitString = (String) rateLimit.get("limit");
	}

}