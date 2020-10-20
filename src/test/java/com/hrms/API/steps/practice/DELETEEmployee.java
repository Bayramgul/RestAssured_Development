package com.hrms.API.steps.practice;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static org.hamcrest.Matchers.equalTo;
import static io.restassured.RestAssured.*;

import org.junit.Assert;

import com.hrms.utils.CommonMethods;
public class DELETEEmployee {
	
	private static RequestSpecification request;
	private Response response;
	@Given("user calls deleteEmployee API to retrieve updated employee")
	public void user_calls_deleteEmployee_API_to_retrieve_updated_employee() {
		request = CommonMethods.headersInRequest()
				.param("employee_id", POSTCreateEmployeeAPI.employee_ID);
	
	}

	@When("User retrieves response for deleteEmployee API to retrieve updated employee")
	public void user_retrieves_response_for_deleteEmployee_API_to_retrieve_updated_employee() {

		response = request.when().delete("http://18.232.148.34/syntaxapi/api/deleteEmployee.php");
		response.prettyPrint();
	
	}

	@Then("status code is {int} for deleteEmployee API")
	public void status_code_is_for_deleteEmployee_API(int int1) {

		Assert.assertEquals(int1, response.getStatusCode());
		response.then().assertThat().statusCode(int1);

	
	}

	@Then("user validates employee is successfully deleted")
	public void user_validates_employee_is_successfully_deleted() {

		response.then().body("employee[0].employee_id", equalTo(POSTCreateEmployeeAPI.employee_ID));

		response.then().body("message", equalTo("Entry deleted"));
	
		boolean responeMessage = response.asString().contains("Entry deleted");
		
		if(responeMessage) {
			
			System.out.println("Message is correct");
		}else {
			
			System.out.println("------INVALID MESSAGE--------");
		}
	
	}
	

}
