package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

import java.io.IOException;

import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;


public class StepDefinition extends Utils{
	JsonPath js;
	RequestSpecification res;
	ResponseSpecification resspec;
	Response response;
	static String place_id;
	
	TestDataBuild data = new TestDataBuild();
	
	
	@Given("Add Place Payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String language, String address) throws IOException {
		
		res = given()
				.spec(requestSpecification())
				.body(data.addPlacePayLoad(name, language, address));
	}
	
	@When("user calls {string} with {string} HTTP request")
	public void user_calls_with_post_http_request(String resource, String httpMethod) {		
		
		APIResources resourceAPI = APIResources.valueOf(resource);
		System.out.println(resourceAPI.getResource()); 
		
		resspec = new ResponseSpecBuilder()
				.expectStatusCode(200)
				.expectContentType(ContentType.JSON)
				.build();

		if(httpMethod.equalsIgnoreCase("POST")) {
			response = res.when().post(resourceAPI.getResource());
		} else if(httpMethod.equalsIgnoreCase("GET")) { 
			response = res.when().get(resourceAPI.getResource());
		}
			
			
			//				.then().spec(resspec).extract().response();
	}
	
	@Then("the API call got success with status code {int}")
	public void the_api_call_got_success_with_status_code(Integer expectedStatusCode) {
			assertEquals(expectedStatusCode.intValue(), response.getStatusCode() );
	}
	
	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String expectedValue) {
		assertEquals(getJsonPath(response, keyValue), expectedValue);
	}
	
	@When("verify place_id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expectedPlaceName, String resource) throws IOException {
		// Prepare request spec
		place_id = getJsonPath(response, "place_id");
		
		res = given()
				.spec(requestSpecification())
				.queryParam("place_id", place_id);
		
		// hit get API call
		user_calls_with_post_http_request(resource, "GET");
		
		// Extract place name
		String actualPlacename = getJsonPath(response, "name");
		
		// Validation of place name
		assertEquals(expectedPlaceName, actualPlacename);
	}

	@Given("DeletePlace Payload")
	public void delete_place_payload() throws IOException {
	    res = given()
	    .spec(requestSpecification())
	    .body(data.deletePlacePayload(place_id));
	}
	
}
