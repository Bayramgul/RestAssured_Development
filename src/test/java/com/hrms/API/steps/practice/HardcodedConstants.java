package com.hrms.API.steps.practice;

public class HardcodedConstants {

	public static String creatEmployeeBody() {

		String createEmployeeBody = "{\r\n" + "  \"emp_firstname\": \"testingAPI\",\r\n"
				+ "  \"emp_lastname\": \"testingAPI\",\r\n" + "  \"emp_middle_name\": \"testingAPI\",\r\n"
				+ "  \"emp_gender\": \"M\",\r\n" + "  \"emp_birthday\": \"1990-06-10\",\r\n"
				+ "  \"emp_status\": \"Worker\",\r\n" + "  \"emp_job_title\": \"Accountant\"\r\n" + "}";

		return createEmployeeBody;

	}

	public static String updateEmployeeBody() {

		String updateEmployeeBody = "{\r\n" + "  \"employee_id\": \""+HarcodedExamples.employeeID+"\",\r\n"
				+ "  \"emp_firstname\": \"syntaxUpdatedFirstName\",\r\n"
				+ "  \"emp_lastname\": \"syntaxUpdatedLastName\",\r\n"
				+ "  \"emp_middle_name\": \"syntaxUpdatedMiddleName\",\r\n" + "  \"emp_gender\": \"F\",\r\n"
				+ "  \"emp_birthday\": \"2000-07-11\",\r\n" + "  \"emp_status\": \"Employee\",\r\n"
				+ "  \"emp_job_title\": \"Cloud Consultant\"\r\n" + "}";
		return updateEmployeeBody;

	}
	

}
