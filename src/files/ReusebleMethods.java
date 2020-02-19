package files;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ReusebleMethods {
	
	public static JsonPath rawToJSON(Response res)
	
	{String responseString=res.asString();
	//System.out.println(responseString);
	
	JsonPath js=new JsonPath(responseString);
	return js;
		
	}
}
