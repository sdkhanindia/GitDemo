package resources;

import java.util.ArrayList;
import java.util.List;

import pojo1.GoogleAPI;
import pojo1.Location;

public class TestBuildData {
	public GoogleAPI getGoogleAPIObject(String name, String language, String address) {
		GoogleAPI googleAPI = new GoogleAPI();
		Location location = new Location();
		
		
		location.setLat(-38.383494);
		location.setLng(33.427362);
		googleAPI.setLocation(location);
		
		googleAPI.setAccuracy(50);
		googleAPI.setName(name);
		googleAPI.setPhone_number("1231231");
		googleAPI.setAddress(address);
		googleAPI.setLanguage(language);
		
		
		
		
		List<String> myList = new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");
		googleAPI.setTypes(myList);
		
		return googleAPI;
	}
	
	public String deletePayload(String placeId) {
		return "{\"place_id\":\""+placeId+"\"}";
	}
}
