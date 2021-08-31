package resources;

import java.util.ArrayList;
import java.util.List;

import apiPojo.AddPlaces;
import apiPojo.Locations;

public class TestDataBuild {
	
	public AddPlaces addplacespayload(String name, String language, String address) {
	
	AddPlaces p =new AddPlaces();
	 p.setAccuracy(50);
	 p.setName(name);
	 p.setAddress(address);
	 p.setPhone_number("(+91) 983 893 3937");
	 p.setWebsite("http://google.com");
	 p.setLanguage(language);
	 Locations l = new Locations();
	 l.setLat(-38.383494);
	 l.setLng(33.427362);
	 p.setLocation(l);
	 List<String> myList = new ArrayList<String>();
	 myList.add("shoe park");
	 myList.add("shop");
	 p.setTypes(myList);
	 return p;
	}
 
	
	public static String deletePayload(String placeid) {
		String DelPayload = "{\r\n \"place_id\":\""+placeid+"\"\r\n}";
		return DelPayload;
	}
}
	
	
