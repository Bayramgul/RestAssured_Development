package com.hrms.API.extra.steps;

import static com.hrms.utils.APIConstants.TOKEN_TXT;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.*;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;

import com.hrms.API.steps.practice.POSTCreateEmployeeAPI;
import com.hrms.utils.ReadWriteFile;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetJobTitlesExtraSteps {
	private RequestSpecification request;
	private Response response;
	String URI = "http://166.62.36.207/syntaxapi/api/jobTitle.php";

	@When("I provide the parameters for Job Titles")
	public void i_provide_the_parameters_for_Job_Titles() {
		// I need this one only if I have a body/payload
//		request = given().header("Content-Type", "application/json");

		String token = ReadWriteFile.readTextFromFile(TOKEN_TXT);
		request = given().header("Authorization", token);
	}

	@When("I make a call to Job Titles")
	public void i_make_a_call_to_Job_Titles() {
		response = request.when().get(URI);
	}

	@Then("I validate the Job Titles status code is {int}")
	public void i_validate_the_Job_Titles_status_code_is(int expectedStatusCode) {
		// 1st way
		response.then().assertThat().statusCode(expectedStatusCode);

		// 2nd way
		int actualStatusCode = response.getStatusCode();
		Assert.assertEquals(expectedStatusCode, actualStatusCode);
	}

	@Then("I validate Job Titles response")
	public void i_validate_Job_Titles_response() {
		String expected = "IT Analyst";
		//1st way 
		response.then().body("'Job Title List'[3]", equalTo(expected));
		
		// 2nd way
		JsonPath jsonPath = response.jsonPath();
		List<String> jobTitles = jsonPath.getList("'Job Title List'");
		System.out.println(jobTitles);

		String jobTitle = jsonPath.getString("'Job Title List'[3]");
		System.out.println("3rd job " + jobTitle);
		// Just do some Assertion
		Assert.assertEquals(expected, jobTitle);

		// 3rd way
		JSONObject object = new JSONObject(response.asString());
		JSONArray jobArray = object.getJSONArray("Job Title List");
		System.out.println(jobArray.get(3));
		// Just do some Assertion
		Assert.assertEquals(expected, jobArray.getString(3));

	}

}
