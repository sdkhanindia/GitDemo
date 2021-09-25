package stepDefinations;

import cucumber.api.java.Before;

public class Hooks {
	String placeId;
	
	
	@Before("@DeletePlace")
	public void beforeScenario() throws Throwable {
//		Write a code to give place_id. Execute this code only when place_id is null.
		StepDefination stepDefination = new StepDefination();
		if(StepDefination.place_id == null) {
			
			stepDefination.add_Place_Payload_with("Aaliya", "Basic Children", "Al Masoudi");
			stepDefination.user_calls_with_http_request("addPlaceAPI", "POST");
			placeId = stepDefination.verify_place_id_created_maps_to_using("Aaliya", "getPlaceAPI");
			
		} 
	}
}
