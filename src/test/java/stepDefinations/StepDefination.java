package stepDefinations;

import static io.restassured.RestAssured.given;

import static org.junit.Assert.*;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestBuildData;
import resources.Utils;


public class StepDefination extends Utils{
	RequestSpecification req;
	RequestSpecification res;
	ResponseSpecification resSpec;
	Response response;
	TestBuildData testData = new TestBuildData();
	static String place_id;
	JsonPath jPath;
	
	
	
	@Given("^Add Place Payload with \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
	public void add_Place_Payload_with(String name, String language, String address) throws Throwable {
		res = given()
					.spec(requestSpecification())
					.body(testData.getGoogleAPIObject(name, language, address));
	    
	}

	@When("^user calls \"([^\"]*)\" with \"([^\"]*)\" http request$")
	public void user_calls_with_http_request(String resource, String method) throws Throwable {
		
		APIResources resourceAPI = APIResources.valueOf(resource);
		String resourceAPIString = resourceAPI.getResource();
		System.out.println(resourceAPIString);
		
		resSpec = new ResponseSpecBuilder()
				.expectStatusCode(200)
				.expectContentType(ContentType.JSON)
				.build();
		if(method.equalsIgnoreCase("post")) {
			response = res	
					.when()
						.post(resourceAPIString);
		} else if (method.equalsIgnoreCase("get")) {
			response = res	
					.when()
						.get(resourceAPIString);
		}
	    
	}

	@Then("^the API call got success with status code (\\d+)$")
	public void the_API_call_got_success_with_status_code(int statusCode) throws Throwable {
		assertEquals(response.getStatusCode(), statusCode);
		
	}

	@Then("^\"([^\"]*)\" in response body is \"([^\"]*)\"$")
	public void in_response_body_is(String key, String value) throws Throwable {
		String checkResponse = getJsonPath(response, key);
		assertEquals(checkResponse, value);
		

	    
	}
	
	@Then("^verify place_id created maps to \"([^\"]*)\" using \"([^\"]*)\"$")
	public String verify_place_id_created_maps_to_using(String expectedName, String resource) throws Throwable {
//		place_id = jPath.getString("place_id");
		place_id = getJsonPath(response, "place_id");
		res = given()
				.spec(requestSpecification())
				.queryParam("place_id", place_id);
		user_calls_with_http_request(resource,"GET");
		
		String actualName = getJsonPath(response, "name");
		assertEquals(expectedName, actualName);
		return place_id;
		
	}

	@Given("^DeletePlace Payload$")
	public void deleteplace_Payload() throws Throwable {
		res = given()
				.spec(requestSpecification())
				.body(testData.deletePayload(place_id));
		
	}

}
