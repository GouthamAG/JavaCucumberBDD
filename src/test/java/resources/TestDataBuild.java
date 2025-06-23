package resources;

import pojogoogle.GetPayload;
import pojogoogle.Location;

public class TestDataBuild {

	public GetPayload addPlacePayLoad(String name, String language, String address) {
		GetPayload gp = new GetPayload(); 
		
		Location lc = new Location();
		lc.setLat(-38.383494);
		lc.setLng(33.427362);
		
		gp.setLocation(lc);
		gp.setAccuracy(50);
		gp.setName(name);
		gp.setPhone_number("(+91) 983 893 3937");
		gp.setAddress(address);
		gp.setTypes(new String[] {"shoe park", "shop"});
		gp.setWebsite("http://google.com");
		gp.setLanguage(language);
		
		return gp;
	}
}