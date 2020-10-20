package com.hrms.API.improved;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.JSONObject;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hrms.utils.CommonMethods;
import com.hrms.utils.RestUtils;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class APISteps extends CommonMethods {
	
	/**
	 * Will not use log4j
	 */
	//private final static Logger log = LoggerFactory.getLogger(APISteps.class);
	String apiBaseURI = "http://18.232.148.34/syntaxapi/api";
	JSONObject body;
	Map<String, String> header;
	
	
	Response response;
	String generatedToken;
	String employee_id;

	
	@Given("Generate Token Body")
	public void generateTokenBody(DataTable dataTable) {
		List<Map<String, String>> dataMap = dataTable.asMaps(String.class, String.class);
		body = RestUtils.createJSONObject(dataMap.get(0));
	}

	@When("post token request {string}")
	public void post_token_request(String endPoint) {
		RequestSpecBuilder request = RestUtils.createRequest(ContentType.JSON);
		request.setBody(body.toString());
		response = RestUtils.sendPostRequest(request, (apiBaseURI + endPoint));
		if (response.getStatusCode() == 201) {
			generatedToken = "Bearer " + response.jsonPath().getString("token");

			System.out.println("Generated Token is: " + generatedToken);
		} else {
			System.out.println("Request Failed Check the logs");
		}
	}

	@When("generate request header with token")
	public void generate_request_header_with_token() {
		header = new HashMap<String, String>();
		header.put("Authorization", generatedToken);
	}

	@When("create request payload")
	public void create_request_payload(DataTable dataTable) {		
	List<Map<String, String>> dataMap = dataTable.asMaps(String.class, String.class);
	body = RestUtils.createJSONObject(dataMap.get(0));
	}
	
	@When("make POST request call to {string}")
	public void make_POST_request_call_to(String endPoint) { 
		RequestSpecBuilder request = RestUtils.createRequest(ContentType.JSON, header);
		request.setBody(body.toString());
		request.addParam("employee_id", employee_id);
		
		response = RestUtils.sendPostRequest(request, apiBaseURI + endPoint);
	
		employee_id = response.jsonPath().get("Employee[0].employee_id");
	}
	
	@Then("verify created employee response with expected data")
	public void verify_created_employee_response_with_expected_data(DataTable dataTable) {
		
		List<Map<String, String>> expectedData = dataTable.asMaps(String.class, String.class);
		List<Map<String, String>> actualData = response.jsonPath().get("Employee");
		int index = 0 ; 
		for (Map<String, String> map : expectedData) {
			Set<String> keys = map.keySet();
			
			for (String key : keys) {
				String expectedValue = map.get(key);
				String actualValue = actualData.get(index).get(key);
				Assert.assertEquals(expectedValue, actualValue);
			}
			
			index ++; 
		}

	}

}
