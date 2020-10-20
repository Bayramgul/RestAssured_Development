package com.hrms.API.steps.practice;

import com.hrms.utils.APIConstants;
import com.hrms.utils.CommonMethods;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Assert;

public class GETVerifyingUpdatedEmployee {
	private static RequestSpecification request;
	public Response response;

	@Given("user calls getOneEmployee API to verify updated employee")
	public void user_calls_getOneEmployee_API_to_verify_updated_employee() {

		request = CommonMethods.headersInRequest()
				.param("employee_id", POSTCreateEmployeeAPI.employee_ID);

	}

	@When("User retrieves response for getOneEmployeeAPI to verify updated employee")
	public void user_retrieves_response_for_getOneEmployeeAPI_to_verify_updated_employee() {

		response = request.when().get(APIConstants.GET_ONE_EMPLOYEE_URI);

		response.prettyPrint();

	}

	@Then("status code is {int} for getOneEmployeeAPI to verify updated employee")
	public void status_code_is_for_getOneEmployeeAPI_to_verify_updated_employee(Integer int1) {
		int statusCode = response.getStatusCode();
		response.then().assertThat().statusCode(int1);
		System.out.println("Status code is: " + statusCode);

	}

	@Then("user validates updated employee exists")
	public void user_validates_updated_employee_exists() {

		response.then().body("employee[0].emp_firstname", equalTo("student1"));

		response.then().body("employee[0].employee_id", equalTo(POSTCreateEmployeeAPI.employee_ID));
		JsonPath jsonPathEvaluator = response.jsonPath();
		String actualEmployeeID = jsonPathEvaluator.get("employee[0].employee_id");

		try {
			Assert.assertEquals("Verifying Employee ID's Match", actualEmployeeID, POSTCreateEmployeeAPI.employee_ID);

		} catch (AssertionError e) {
			System.out.println("Employee ID's DO NOT MATCH");
		}
		System.out.println("Employee ID's Match");

	}

}
