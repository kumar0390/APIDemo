package stepDefinations;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import apiPojo.AddPlaces;
import apiPojo.Locations;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResource;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefination extends Utils {
	
	RequestSpecification res;
	ResponseSpecification resspec;
	Response response;
	TestDataBuild data = new TestDataBuild();
	// By using static in any variable the all the test cases in that particular run, will refer to same variable.
	//If we don't put static then it will throw null pointer exception.
	static String place_id;
	
	
	
	
	@Given("Add place payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String language, String address)  throws IOException {
		 res = given().spec(resquestSpecification()).body(data.addplacespayload(name, language, address));
	}
	@When("User call {string} with {string} HTTP")
	public void user_call_with_http(String resource, String method) {
		APIResource resure = APIResource.valueOf(resource);
		System.out.println(resure.getResource());
		 resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		 if(method.equalsIgnoreCase("post"))
			 response =res.when().post(resure.getResource());
		 else if(method.equalsIgnoreCase("get"))
			 response =res.when().get(resure.getResource());
		 else if(method.equalsIgnoreCase("delete"))
			 response =res.when().delete(resure.getResource());
			 
}
	@Then("the API call got success with status code {int}")
	public void the_api_call_got_success_with_status_code(Integer int1) {
		assertEquals(response.statusCode(), 200);

	
	}
	@Then("{string} in response body is {string}")
	public void in_response_body_is(String key, String value) {
	    // Write code here that turns the phrase above into concrete actions
	   //String resp = response.asString();
	   //JsonPath js = new JsonPath(resp); //move this code to utility class as we are using this code multiple time 
	  // String str = js.get(key).toString();
		//created a getJsonpath method in utility class to extract value and calling that method.
		String str = getJsonPath(response, key);
	   assertEquals(str, value);
	   System.out.println(str);
	}
	
	@Then("verify the {string} and placeid using {string}")
	public void verify_the_and_placeid_using(String Actualname, String resource) throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		//created a getJsonpath method in utility class to extract value and calling that method.
		place_id = getJsonPath(response, "place_id");
		res = given().spec(resquestSpecification()).queryParam("place_id", place_id);
	// as we have have already define method Http method for post , just we are calling that method for bget also
		user_call_with_http(resource, "Get");
		String Expectedname = getJsonPath(response, "name");
		assertEquals(Actualname, Expectedname);
	   
	}
	
	
	@Given("DeletePlace payload")
	public void delete_place_payload() throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		// to build that request we have to push all our request detail, so that 
		//it will be used further to hit using the same varibale i.e. when().post().
	    res = given().spec(resquestSpecification()).body(data.deletePayload(place_id));
	    
	}
	

	
	

}
