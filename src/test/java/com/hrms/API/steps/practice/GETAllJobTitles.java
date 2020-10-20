package com.hrms.API.steps.practice;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.*;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;

import com.hrms.utils.CommonMethods;
import com.hrms.utils.RestUtils;

public class GETAllJobTitles {
	
	Response response;
	RequestSpecification request;

	@Given("user calls jobTitle API to retrieve all job titles")
	public void user_calls_jobTitle_API_to_retrieve_all_job_titles() {

		request =CommonMethods.headersInRequest();
	
	}

	@When("User retrieves response for jobTitle API to retrieve all Job Titles")
	public void user_retrieves_response_for_jobTitle_API_to_retrieve_all_Job_Titles() {

		response = request.when().get("http://18.232.148.34/syntaxapi/api/jobTitle.php");

		response.prettyPrint();
	
	}

	@Then("status code is {int} for jobTitle API")
	public void status_code_is_for_jobTitle_API(Integer int1) {

		response.then().assertThat().statusCode(int1);
	
	}

	
		@Then("user validates Job Titles")
		public void user_validates_Job_Titles(io.cucumber.datatable.DataTable dataTable ) {
			String  arrayName="Job Title List";
			RestUtils.validateJsonBody_AsArray(dataTable, arrayName);
//		JSONObject json = new JSONObject(response.prettyPrint());
//		JSONArray array = json.getJSONArray("Job Title List");
//		for (int i = 0; i <= array.length() - 1; i++) {
//			System.out.println(array.get(i));
//			if (i == 0) {
//				Assert.assertEquals("Cloud Architect", array.get(i));
//			}else if(i==1) {
//				Assert.assertEquals("Cloud Consultant", array.get(i));
//			}else if(i==2) {
//				Assert.assertEquals("Cloud Product and Project Manager", array.get(i));
//			}else if(i==3) {
//				Assert.assertEquals("IT Analyst", array.get(i));
//			}else if(i==4) {
//				Assert.assertEquals("Network Administrator", array.get(i));
//			}else if(i==5) {
//				Assert.assertEquals("IT Support Manager", array.get(i));
//			}else if(i==6) {
//				Assert.assertEquals("Data Quality Manager", array.get(i));
//			}else if(i==7) {
//				Assert.assertEquals("Database Administrator", array.get(i));
//			}
//		}
//		Assert.assertEquals(23, array.length());
//		System.out.println(array.length());
	
	
	
	
	}
	
	
}
