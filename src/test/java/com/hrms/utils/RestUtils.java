package com.hrms.utils;

import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.cucumber.datatable.DataTable;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class RestUtils extends CommonMethods {
	private final static Logger log = LoggerFactory.getLogger(RestUtils.class);

	/**
	 * @author Mo Shokriyan
	 * @param map<String, Object>
	 * @return JSONObject
	 * 
	 *         Use this to create JSONObject for Request Body
	 * 
	 */

	public static JSONObject createJSONObject(Map<String, String> map) {
		JSONObject object = new JSONObject(map);
		log.info("JSON Object ::: " + object.toString());
		return object;
	}

	/**
	 * @author Mo Shokriyan
	 * @param map<String, Object>
	 * @return JSONObject
	 * 
	 *         Use this to create JSONObject for Request Body
	 * 
	 */
	public static JSONArray createJSONArray(List<Map<String, String>> list) {
		JSONArray object = new JSONArray(list);
		log.info("JSON Array ::: " + object.toString());
		return object;
	}

	/**
	 * This Method is To Create request with a header
	 * 
	 * @author Mo shokriyan
	 * @param String      contentType
	 * @param Map<String, String> header
	 * @return RequestSpecBuilder
	 * 
	 */
	public static RequestSpecBuilder createRequest(ContentType contentType) {
		RequestSpecBuilder requestBuilder = new RequestSpecBuilder();
		requestBuilder.setContentType(contentType);
		return requestBuilder;

	}

	/**
	 * This Method is To Create request with a header
	 * 
	 * @author Mo shokriyan
	 * @param String      contentType
	 * @param Map<String, String> header
	 * @return RequestSpecBuilder
	 * 
	 */
	public static RequestSpecBuilder createRequest(ContentType contentType, Map<String, String> header) {
		RequestSpecBuilder requestBuilder = new RequestSpecBuilder();
		requestBuilder.setContentType(contentType);
		if (!header.isEmpty())
			requestBuilder.addHeaders(header);
		else
			throw new RuntimeException("Header is Empty Execution Fail");
		return requestBuilder;

	}

	/**
	 * This Method is to create request with a header and parameter
	 * 
	 * @author Mo shokriyan
	 * @param String      contentType
	 * @param Map<String, String> header
	 * @param Map<String, String> parameter
	 * @return RequestSpecBuilder
	 * 
	 */

	public static RequestSpecBuilder createRequestWithParameter(String contentType, Map<String, String> header,
			Map<String, String> parameter) {
		RequestSpecBuilder requestBuilder = new RequestSpecBuilder();
		requestBuilder.setContentType(contentType);
		// adding Parameters
		if (!parameter.isEmpty())
			for (String key : parameter.keySet()) {
				requestBuilder.addQueryParam(key, parameter.get(key));
			}
		else
			throw new RuntimeException("Header is Empty Execution Fail");
		// Adding header
		if (!header.isEmpty())
			requestBuilder.addHeaders(header);
		else
			throw new RuntimeException("Header is Empty Execution Fail");
		return requestBuilder;

	}

	/**
	 * This Method to send a get request to any endPoint
	 * 
	 * @author Mo shokriyan
	 * @param RequestSpecBuilder request
	 * @param String             endPoint
	 * @return Response
	 */

	public static Response sendGetRequest(RequestSpecBuilder request, String endPoint) {
		Response response = null;
		log.info("Send get request ::: " + endPoint);
		log.info("Request Body ::: " + request.log(LogDetail.BODY));
		log.info("Request Headers ::: " + request.log(LogDetail.HEADERS));
		if (request != null && !endPoint.isEmpty()) {
			request.setBaseUri(endPoint);
			log.info("Request URI ::: " + request.log(LogDetail.URI));
			response = given().spec(request.build()).relaxedHTTPSValidation().when().get();
		}
		log.info("Get request send");
		log.info("Response ::: " + response.asString());
		return response;
	}

	/**
	 * This Method to send a post request to any endPoint
	 * 
	 * @author Mo shokriyan
	 * @param RequestSpecBuilder request
	 * @param String             endPoint
	 * @return Response
	 */

	public static Response sendPostRequest(RequestSpecBuilder request, String endPoint) {
		Response response = null;
		log.info("Request Body ::: " + request.log(LogDetail.BODY));
		log.info("Request Headers ::: " + request.log(LogDetail.HEADERS));
		// System.out.println(request.log(LogDetail.BODY));
		log.info("Send post request ::: " + endPoint);
		if (request != null && !endPoint.isEmpty()) {
			request.setBaseUri(endPoint);
			log.info("Request URI ::: " + request.log(LogDetail.URI));
			response = given().spec(request.build()).relaxedHTTPSValidation().when().post();
		}

		log.info("Post request send");
		log.info("Response ::: " + response.asString());
		return response;
	}
//=======================================Updated With RequestSpecification====================================================================//
//===========================================================================================================================================//
	/**
	 * Declaring Private variable to initialize inside methods
	 */
	private Response responseVariable = null;
	private RequestSpecification requestHeaders = null;

	// this is Base URL not Base URI
	static String BaseUrl = RestAssured.baseURI = "http://18.232.148.34/syntaxapi/api";

	/**
	 * JWT for HRMS API
	 */
	private String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE1OTUxNDMwNzksImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTU5NTE4NjI3OSwidXNlcklkIjoiMTA4MSJ9.sWhTRMjX_DFTakxH988NQiSnEzSLL1rMKdi6J3R7Ctg";

	/**
	 * @author Md. Saifuzzaman (Saif) 
	 * this Setters is to update Token Every 12 hours
	 */
	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * @author Md. Saifuzzaman (Saif) 
	 * This Method will taken token and query
	 * parameter details as parameter And return Request Specification
	 * Object with content type json.
	 * @param token
	 * @param qParamName
	 * @param qParamValue
	 * @return
	 */
	public RequestSpecification requestSpecificationDetails(String qParamName, String qParamValue, String body) {

		if (qParamName.isEmpty() && qParamValue.isEmpty() && body.isEmpty()) {

			requestHeaders = given().headers("Content-type", ContentType.JSON)
					.header("Authorization", "Bearer " + this.token).log().all();

		} else if (qParamName.isEmpty() && qParamValue.isEmpty() && !(body.isEmpty())) {

			requestHeaders = given().headers("Content-type", ContentType.JSON)
					.header("Authorization", "Bearer " + this.token).body(body).log().all();

		} else if (!(qParamName.isEmpty() && qParamValue.isEmpty()) && body.isEmpty()) {

			requestHeaders = given().headers("Content-type", ContentType.JSON)
					.header("Authorization", "Bearer " + this.token).queryParams(qParamName, qParamValue).log().all();

		}
		return requestHeaders;
	}

	/**
	 * @author Md Saifuzzaman (Saif) 
	 * This Method will show resonseResult based on
	 * endpointName,parameter and body. FYI, for HRMS application we have to
	 * set token before running this method if token has expired. Note that
	 * when We are not providing any parameter we have write this[""] for
	 * both parameter name & value. We also have to write [""] if we are not
	 * providing any body
	 * 
	 * @param endPointName of the request as String
	 * @param Query parameter name as string
	 * @param Query parameter value as string
	 * @param Request body of Json Format passed as String
	 * @return
	 */
	public Response executeResponse(String endPointName, String qParamName, String qParamValue, String body) {

		if (qParamName.isEmpty() && body.isEmpty()) {

			if (endPointName.equalsIgnoreCase("/getAllEmployees.php") || endPointName.equalsIgnoreCase("/jobTitle.php")
					|| endPointName.equalsIgnoreCase("/employeeStatus.php"))
				responseVariable = requestSpecificationDetails("", "", "").when().get(endPointName);

		} else if (qParamName.isEmpty() && !body.isEmpty()) {

			if (endPointName.equalsIgnoreCase("/createUser.php") || endPointName.equalsIgnoreCase("/generateToken.php")
					|| endPointName.equalsIgnoreCase("/createEmployee.php")) {

				responseVariable = requestSpecificationDetails("", "", body).when().post(endPointName);

			} else if (endPointName.equalsIgnoreCase("/updateEmployee.php")) {

				responseVariable = requestSpecificationDetails("", "", body).when().put(endPointName);

			} else if (endPointName.equalsIgnoreCase("/updatePartialEmplyeesDetails.php")) {

				responseVariable = requestSpecificationDetails("", "", body).when().patch(endPointName);
			}
		} else {
			if (endPointName.equalsIgnoreCase("/getOneEmployee.php")) {

				responseVariable = requestSpecificationDetails(qParamName, qParamValue, "").when().get(endPointName);

			} else if (endPointName.equalsIgnoreCase("/deleteEmployee.php")) {

				responseVariable = requestSpecificationDetails(qParamName, qParamValue, "").when().delete(endPointName);
			}

		}
		return responseVariable;
	}
	/**
	 * @author Bayramgul
	 * This method validates Json response Body content which appears as an Array of
	 * Strings by comparing it to the values we pass in feature file as a list of
	 * Strings Can be used for getJobtiles and getEmploymentStatus requests
	 * 
	 * @param dataTable this is from feature file as a List
	 * @param arrayName this is from Json response body as an array of Strings
	 * 
	 */
	public static void validateJsonBody_AsArray(DataTable dataTable, String arrayName) {
//		Retrieve dataTable from feature file as a List and store it in jobtitles
		List<String> jobtitles = dataTable.asList();
//Calling headersInRequest() method to retrieve request and use GET_JOBTITLES_ENDPOINT from APIConstants to get response
		Response response = headersInRequest().when().get(APIConstants.GET_JOBTITLES_ENDPOINT);
//	Creating instance of JSONObject class to use it for getting JSon body as an array in next line
		JSONObject json = new JSONObject(response.prettyPrint());
		JSONArray array = json.getJSONArray(arrayName);
		for (int i = 0; i < array.length(); i++) {
//	Asserting JObTitle from response body toward feature file values
			Assert.assertEquals(jobtitles.get(i), array.get(i));
		}
	}
}
