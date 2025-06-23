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

import resources.TestDataBuild;
import resources.Utils;


public class StepDefinition extends Utils{
	
	RequestSpecification res;
	ResponseSpecification resspec;
	Response response;
	
	TestDataBuild data = new TestDataBuild();
	
	@Given("Add Place Payload")
	public void add_place_payload() throws IOException {
		
		res = given()
				.spec(requestSpecification())
				.body(data.addPlacePayLoad());
	}
	
	@When("user calls {string} with Post HTTP request")
	public void user_calls_with_post_http_request(String string) {		
		resspec = new ResponseSpecBuilder()
				.expectStatusCode(200)
				.expectContentType(ContentType.JSON)
				.build();

		response = res
				.when().post("/maps/api/place/add/json")
				.then().spec(resspec).extract().response();
	}
	
	@Then("the API call got success with status code {int}")
	public void the_api_call_got_success_with_status_code(Integer int1) {
			assertEquals(response.getStatusCode(),200);
	}
	
	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String expectedValue) {
		String responseString = response.asString();
		JsonPath js = new JsonPath(responseString);
		assertEquals(js.get(keyValue).toString(), expectedValue);
	}

}
