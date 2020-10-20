package com.hrms.API.steps.practice;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

import org.json.JSONArray;
import org.json.JSONObject;

import com.hrms.utils.CommonMethods;

public class GetAllEmployeesEndPoint {

	private static RequestSpecification request;
	private Response response;

	@Given("user calls getAllEmployees to retrieve all employee")
	public void user_calls_getAllEmployees_to_retrieve_all_employee() {
		request=CommonMethods.headersInRequest();
	}

	@When("User retrieves response for getAllEmployees to retrieve List of employees")
	public void user_retrieves_response_for_getAllEmployees_to_retrieve_List_of_employees() {
		System.out.println(request.log().all());
		response=request.get("http://18.232.148.34/syntaxapi/api/getAllEmployees.php");
	}

	@Then("status code is {int} for getAllEmployees")
	public void status_code_is_for_getAllEmployees(Integer int1) {
		response.then().assertThat().statusCode(int1);
	}

	@Then("user validates List of Employees is successfully Returned")
	public void user_validates_List_of_Employees_is_successfully_Returned() {
		JSONObject json = new JSONObject(response.prettyPrint());
		JSONArray array = json.getJSONArray("Employee");
		System.out.println("Size of list for returned Employee is: "+array.length());
		
		for(int i=0;i<=array.length()-1;i++) {
			 //"emp_firstname": "SyntaxAPIInstructor",
			  String createdEmployee=response.jsonPath().getString("Employee["+i+"].emp_firstname");
			  if(createdEmployee.equals("SyntaxAPIInstructor")) {
				  response.then().body("Employee["+i+"].emp_lastname", equalTo("employee"));
				  response.then().body("Employee["+i+"].emp_middle_name", equalTo("syntax"));
				  response.then().body("Employee["+i+"].emp_gender", equalTo("2"));
				  response.then().body("Employee["+i+"].emp_birthday", equalTo("1991-04-22"));
				  response.then().body("Employee["+i+"].emp_status", equalTo("Employee"));
				  response.then().body("Employee["+i+"].emp_job_title", equalTo("Developer"));
				  System.out.println("Employee is found!!!!!");
				  break;
			  }else {
				  //System.out.println("Employee is NOT found!!!!!");
			  }

		}
	}

}
