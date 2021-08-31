package stepDefinations;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	
	@Before("@DeletePlace")
	public void beforeScenario() throws IOException
	{
		StepDefination m = new StepDefination();
		if(m.place_id == null)
		{
		
		m.add_place_payload_with("Kumar", "India", "Hinglish");
		m.user_call_with_http("AddPlaceAPI", "POST");
		m.verify_the_and_placeid_using("Kumar", "GetPlaceAPI");
		}
	}

}
