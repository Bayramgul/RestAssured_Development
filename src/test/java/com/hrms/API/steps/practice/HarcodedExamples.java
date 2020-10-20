package com.hrms.API.steps.practice;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
/**
 * manually import below static methods
 * There are some packages in Rest Assured that are static 
 * 
 */
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.apache.hc.core5.http.ContentType;
import org.junit.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class HarcodedExamples {

	/**
	 * Rest Assured 
	 * Given - Preparing your request 
	 * When - What action will you perform, what type of call are you making? When you actually make the call
	 * Then - Verification
	 */

	/*
	 * Stored employeeID from created employee
	 */
	static String employeeID;
	/**
	 * BaseURI for all endpoints
	 */
	String baseURI = RestAssured.baseURI = "http://18.232.148.34/syntaxapi/api";

	/**
	 * JWT for all endpoints stored as String
	 */
	String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE1OTUwMDU2ODcsImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTU5NTA0ODg4NywidXNlcklkIjoiNjQ1In0.ef-PWga_6lMI6Pi8lSY6gm1z2giuQDmoShQJHMtgUKU";

	// @Test
	public void sampleTest() {

		/**
		 * BaseURI for all endpoints
		 */
		// RestAssured.baseURI = "http://18.232.148.34/syntaxapi/api";

		/**
		 * JWT for all endpoints stored as String
		 */

		/**
		 * Preparing request for /getOneEmployee also using log().all() to log (print)
		 * all details being sent with request
		 */
		RequestSpecification preparingGetOneEmployeeRequest = given().header("Authorization", token)
				.header("Content-Type", "application/json").queryParam("employee_id", "9823A").log().all();

		/**
		 * making call to /getOneEmpployee and storing response into
		 * getOneEmployeeResponse object
		 */
		Response getOneEmployeeResponse = preparingGetOneEmployeeRequest.when().get("/getOneEmployee.php");

		/**
		 * Optional to print out response object as a String or you can simply use
		 * prettyPrint() method
		 */
		// System.out.println(response.asString());
		// getOneEmployeeResponse.prettyPrint();

		/**
		 * verifying request made to server is successful
		 */
		getOneEmployeeResponse.then().assertThat().statusCode(200);

	}

	@Test
	public void aPOSTcreateEmployee() {
		/**
		 * Preparing /createEmployee POST request also using log().all() to log (print)
		 * all details being sent with request
		 */

		RequestSpecification createEmployeeRequest = given().header("Authorization", token)
				.header("Content-Type", "application/json").body(HardcodedConstants.creatEmployeeBody());
		// .log().all();

		/**
		 * making call to /createEmployee and storing response into
		 * createEmployeeResponse
		 */
		Response createEmployeeResponse = createEmployeeRequest.when().post("/createEmployee.php");
		/**
		 * printing /createEmployee Response body
		 */
		// createEmployeeResponse.prettyPrint();

		/**
		 * Using jsonPath() to view the response body which lets us get the employee ID
		 * We store the employeeID as a global Object so that we may use it with our
		 * other calls
		 * 
		 */
		employeeID = createEmployeeResponse.jsonPath().getString("Employee[0].employee_id");
		/**
		 * Printing employee ID
		 */
		System.out.println(employeeID);

		/**
		 * Verifying request made to server is successful
		 */
		createEmployeeResponse.then().assertThat().statusCode(201);

		/**
		 * verifying response body "message" displays "Entry Created" equalTo() method
		 * comes from static Hamcrest package - need to manually import static
		 * org.hamcrest.Matchers.*;
		 */
		createEmployeeResponse.then().assertThat().body("Message", equalTo("Entry Created"));

		/**
		 * verifying response body employee first name displays "testingAPI"
		 */
		createEmployeeResponse.then().assertThat().body("Employee[0].emp_firstname", equalTo("testingAPI"));

		/**
		 * verifying response header displays response came from Apache/2.4.39 (Win64)
		 * PHP/7.2.18 server
		 */
		createEmployeeResponse.then().header("Server", "Apache/2.4.39 (Win64) PHP/7.2.18");

	}

	@Test
	public void bGETcreatedEmployee() {

		/**
		 * Preparing request for /getOneEmployee.php to get the created employee in
		 * previous call
		 */
		RequestSpecification getCreatedEmployeeRequest = given().header("Content-Type", "application/json")
				.header("Authorization", token).queryParam("employee_id", employeeID); // .log().all();

		/**
		 * Storing response /getOneEmployee.php response into getCreatedEmployeeResponse
		 */
		Response getCreatedEmployeeResponse = getCreatedEmployeeRequest.when().get("/getOneEmployee.php");

		/**
		 * Printing response using prettyPrint() method
		 */
		// getCreatedEmployeeResponse.body().asString();

		/**
		 * Storing response as a String
		 */
		String response = getCreatedEmployeeResponse.body().asString(); // prettyPrint();

		/**
		 * Storing employee ID from response into empID
		 */
		String empID = getCreatedEmployeeResponse.body().jsonPath().getString("employee[0].employee_id");

		/**
		 * comparing empID with stored employeeID from created employee
		 */
		boolean verifyingEmployeeID = empID.equalsIgnoreCase(employeeID);

		/**
		 * Asserting to verify that the above condition is true
		 */
		Assert.assertTrue(verifyingEmployeeID);

		/**
		 * Verifying response status code
		 */
		getCreatedEmployeeResponse.then().statusCode(200);

		/**
		 * There is a class called JsonPath - I will create an object for the class
		 * JsonPath is a class which takes String as an input and converts it into JSON
		 * object it help parse any JSON output
		 */

		JsonPath js = new JsonPath(response);
		String emplID = js.getString("employee[0].employee_id");
		String firstName = js.getString("employee[0].emp_firstname");
		String middleName = js.getString("employee[0].emp_middle_name");
		String lastName = js.getString("employee[0].emp_lastname");
		String birthday = js.getString("employee[0].emp_birthday");
		String gender = js.getString("employee[0].emp_gender");
		String jobTitle = js.getString("employee[0].emp_job_title");
		String status = js.getString("employee[0].emp_status");

		// System.out.println(empID);
		// System.out.println(firstName);
		/** Verifying first name */
		Assert.assertTrue(firstName.equalsIgnoreCase("testingAPI"));

		/** Verifying empID */
		Assert.assertTrue(emplID.equalsIgnoreCase(employeeID));

		/** Verifying middleName */
		Assert.assertTrue(middleName.equalsIgnoreCase("testingAPI"));

		/** Verifying lastName */
		Assert.assertTrue(lastName.equalsIgnoreCase("testingAPI"));

		/** Verifying birthday */
		Assert.assertTrue(birthday.equalsIgnoreCase("1990-06-10"));

		/** Verifying gender */
		Assert.assertTrue(gender.equalsIgnoreCase("Male"));

		/** Verifying jobTitle */
		Assert.assertTrue(jobTitle.equalsIgnoreCase("Accountant"));

		/** Verifying status */
		Assert.assertTrue(status.contentEquals("Worker"));

	}

	@Test
	public void cGETallEmployees() {

		/**
		 * Preparing /getAllEmployees.php request
		 */
		RequestSpecification getAllEmployeesRequest = given().header("Content-Type", "application/json")
				.header("Authorization", token);// .log().all();

		/**
		 * Storing /getAllEmployees.php response into getAllEmployeesResponse
		 */
		Response getAllEmployeesResponse = getAllEmployeesRequest.when().get("/getAllEmployees.php");

		// getAllEmployeesResponse.prettyPrint();
		/**
		 * Storing getAllEmployeesResponse response as a String
		 */
		String response = getAllEmployeesResponse.body().asString();

		/**
		 * Below will not work
		 */
		// boolean verifyingEmployeeID = response.contentEquals(employeeID);
		// Assert.assertTrue(verifyingEmployeeID);

		/**
		 * Creating object of JsonPath class to be able to pass String response as an
		 * argument to be able to retrieve data in String format
		 */
		JsonPath js = new JsonPath(response);

		/**
		 * Retrieving size of Employees Array
		 */
		int count = js.getInt("Employees.size()");
		System.out.println(count);

		/**
		 * for loop to print out all employee ID's
		 */
//		for (int i = 0; i < count; i++) {
//
//			String allEmployeeIDs = js.getString("Employees[" + i + "].employee_id");
//
//			// System.out.println(allEmployeeIDs);
//
//			/**
//			 * If statement inside for loop to find stored employee ID and break the loop
//			 * when found - this works as an assertion
//			 */
//			if (allEmployeeIDs.contentEquals(employeeID)) {
//
//				System.out.println("Employee Id: " + employeeID + " is present in response body");
//				String firstNameOfEmpID = js.getString("Employees[" + i + "].emp_firstname");
//
//				System.out.println(firstNameOfEmpID);
//				break;
//			}
//
//		}

		/**
		 * for loop to print out first names of all employees
		 */
//		for(int i = 0; i<count; i++) {
//			
//			String allEmployees = js.getString("Employees["+i+"].employee_id");
//			
//			if(allEmployees.contains(employeeID)) {
//				/**return first name */
//				String firstNameOfEmpID = js.getString("Employees["+i+"].emp_firstname");
//				System.out.println(firstNameOfEmpID);
//				break;
//			}
//			
//		}

	}

	@Test
	public void dPUTupdateCreatedEmployee() {

		RequestSpecification updateCreatedEmployeeRequest = given().header("Content-Type", "application/json")
				.header("Authorization", token).body(HardcodedConstants.updateEmployeeBody());

		Response updatedCreatedEmployeeResponse = updateCreatedEmployeeRequest.when().put("/updateEmployee.php");

		// updatedCreatedEmployeeResponse.prettyPrint();

		String responseAsString = updatedCreatedEmployeeResponse.body().asString();

		JsonPath js = new JsonPath(responseAsString);

		String verifyEmployeeID = js.getString("employee[0].employee_id");
		Assert.assertTrue(verifyEmployeeID.contentEquals(employeeID));

	}

	@Test
	public void eGETupdatedEmployee() {

		RequestSpecification getUpdatedEmployeeRequest = given().header("Content-Type", ContentType.APPLICATION_JSON)
				.header("Authorization", token).queryParam("employee_id", employeeID);

		Response getUpdatedEmployeeResponse = getUpdatedEmployeeRequest.when().get("/getOneEmployee.php");

		// getUpdatedEmployeeResponse.prettyPrint();

	}

	@Test
	public void fPATCHpartiallyUpdateEmployee() {

		RequestSpecification preparingPartialUpdateEmployeeRequest = given().header("Content-Type", "application/json")
				.header("Authorization", token).body("{\r\n" + "  \"employee_id\": \"" + employeeID + "\",\r\n"
						+ "  \"emp_firstname\": \"syntaxPartiallyUpdatedFirstName\"\r\n" + "}");
		// .log().all();

		Response partialUpdateResponse = preparingPartialUpdateEmployeeRequest.when()
				.patch("/updatePartialEmplyeesDetails.php");

		// partialUpdateResponse.prettyPrint();

	}

	@Test
	public void gGETpartiallyUpdatedEmployee() {

		RequestSpecification getPartialUpdatedEmpRequest = given().header("Content-Type", "application/json")
				.header("Authorization", token).queryParam("employee_id", employeeID);

		Response getPartialUpdatedEmpResponse = getPartialUpdatedEmpRequest.when().get("/getOneEmployee.php");
		// getPartialUpdatedEmpResponse.prettyPrint();

		getPartialUpdatedEmpResponse.then().body("employee[0].employee_id", equalTo(employeeID));

	}

	@Test
	public void hDELETEdeletingEmployee() {

		RequestSpecification deletingEmployeeRequest = given().header("Content-Type", "application/json")
				.header("Authorization", token).queryParam("employee_id", employeeID);// .log().all();

		Response deletingEmployeeResponse = deletingEmployeeRequest.when().delete("/deleteEmployee.php");

		deletingEmployeeResponse.prettyPrint();
		
		deletingEmployeeResponse.then().body("message", equalTo("Entry deleted"));

	}
}
