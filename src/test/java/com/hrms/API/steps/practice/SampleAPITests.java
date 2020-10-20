package com.hrms.API.steps.practice;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import org.junit.Assert;
import org.junit.Test;





public class SampleAPITests {
	
	/**
	 * Below are hard coded examples using REST assured
	 * 
	 */

	 @Test
	public void getAllJobTitles() {

		Response response = RestAssured.given().contentType("application/json").header("Authorization",
				"Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE1ODI0NzI4MTksImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTU4MjU4ODAxOSwidXNlcklkIjoiOTUifQ.7A0ZdFAfztoLO9qE-ohb_IFB3OsU6o-HsEn1-25jfOQ")
				.when().get("http://18.232.148.34/syntaxapi/api/jobTitle.php");

		response.prettyPrint();
		int actualResponseCode = response.getStatusCode();
		System.out.println(actualResponseCode);

	}

	// @Test
	public void getOneEmployee() {

		Response response = RestAssured.given().param("employee_id", "4355").contentType("application/json").header(
				"Authorization",
				"Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE1ODI0NzI4MTksImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTU4MjU4ODAxOSwidXNlcklkIjoiOTUifQ.7A0ZdFAfztoLO9qE-ohb_IFB3OsU6o-HsEn1-25jfOQ")
				.when().get("http://18.232.148.34/syntaxapi/api/getOneEmployee.php");

		response.prettyPrint();

	}

	/*
	 * TASK: getAllEmployeeStatuses Pretty print print status code
	 * 
	 */

	// @Test
	public void getALLEmployeeStatuses() {

		Response response = RestAssured.given().contentType("application/json").header("Authorization",
				"Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE1ODI0NzI4MTksImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTU4MjU4ODAxOSwidXNlcklkIjoiOTUifQ.7A0ZdFAfztoLO9qE-ohb_IFB3OsU6o-HsEn1-25jfOQ")
				.when().get("http://18.232.148.34/syntaxapi/api/employeeStatus.php");
		response.prettyPrint();
		int actualStatusCode = response.getStatusCode();

		System.out.println(actualStatusCode);
	}

	@Test
	public void createEmployee() {

		Response response = RestAssured.given().urlEncodingEnabled(true).header("Authorization",
				"Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE1ODI0NzI4MTksImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTU4MjU4ODAxOSwidXNlcklkIjoiOTUifQ.7A0ZdFAfztoLO9qE-ohb_IFB3OsU6o-HsEn1-25jfOQ")

		.param("emp_firstname", "newName")
		.param("emp_lastname", "employee")
		.param("emp_middle_name", "syntax")
		.param("emp_gender", "2")
		.param("emp_birthday", "1991-04-22")
		.param("emp_status", "Employee")
		.param("emp_job_title", "Developer")
		.contentType("application/json")
				.when().post("http://18.232.148.34/syntaxapi/api/createEmployee.php");

		response.prettyPrint();
		int actualStatusCode = response.getStatusCode();
		Assert.assertEquals(200, actualStatusCode);

	}

}
