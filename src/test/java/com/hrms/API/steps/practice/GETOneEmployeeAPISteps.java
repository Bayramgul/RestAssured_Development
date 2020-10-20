package com.hrms.API.steps.practice;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Assert;

import com.hrms.utils.APIConstants;
import com.hrms.utils.CommonMethods;

public class GETOneEmployeeAPISteps {
	private Response response;
	private static RequestSpecification request;

	@Given("user calls getOneEmployee API")
	public void user_calls_getOneEmployee_API() {

		request = CommonMethods.headersInRequest()
				.param("employee_id", POSTCreateEmployeeAPI.employee_ID);

	}

	@When("User retrieves response for getOneEmployee API")
	public void user_retrieves_response_for_getOneEmployee_API() {

		response = request.when().get(APIConstants.GET_ONE_EMPLOYEE_URI);
		response.prettyPrint();

	}

	@Then("status code is {int} for getOneEmployee API")
	public void status_code_is_for_getOneEmployee_API(int int1) {

		response.then().assertThat().statusCode(int1);

	}

	@Then("user validates created employee exists")
	public void user_validates_created_employee_exists() {

		// First way to Assert using Matchers
		response.then().body("employee[0].employee_id", equalTo(POSTCreateEmployeeAPI.employee_ID));

		// Second way to assert
		// First create instance of JsonPath - here we called it jsonPathEvaluator
		// We are also storing the jsonPath(); using response.jsonPath();
		JsonPath jsonPathEvaluator = response.jsonPath();

		// Using the jsonPathEvaluator variable we created we can .get(get what you are
		// looking for)
		// in this case we just want to grab employee id from json response - use
		// postman to see
		// - the response for additional help
		String actualyEmployeeID = jsonPathEvaluator.get("employee[0].employee_id");

		// printing out the employee ID so you can see it
		System.out.println("Actual Employee ID is:" + actualyEmployeeID);

		// Then we are using Assert class from JUnit to verify employee ID's match
		Assert.assertEquals("Verifying Employee IDs", POSTCreateEmployeeAPI.employee_ID, actualyEmployeeID);

		// You can throw in try catch block if you want to - optional
		try {
			// Try the code that may throw an exception
			Assert.assertEquals("Verifying Employee IDs", POSTCreateEmployeeAPI.employee_ID, actualyEmployeeID);

		} catch (AssertionError e) {

			// if exception gets thrown what do you want to do with it?
			System.out.println("Employee ID's DO NOT MATCH");
		}
		// this will print if exception was not caught
		System.out.println("EMPLOYEE ID's MATCH");
	}

}
