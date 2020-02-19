import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class basics3 {
	@Test
	public void test1()
	{
		//Base url or Host
		RestAssured.baseURI="https://maps.googleapis.com";
		Response res=given().
		   param("location","-33.8670522,151.1957362").
		   param("radius","500").
		   param("key","AIzaSyBsugIQm7l35hsOrSkbYqkkl9a_bAxo-RA").
		   when().
		   get("/maps/api/place/nearbysearch/json").
		   then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
		   //body("results[0].geometry.location.lat",equalTo("-33.86882")).and().
		   //body("results[0].place_id",equalTo("ChIJP3Sa8ziYEmsRUKgyFmh9AQM")).and().
		   header("Server","scaffolding on HTTPServer2").
		   extract().response();
		
		String responseString=res.asString();
		System.out.println(responseString);
		
		JsonPath js=new JsonPath(responseString);
		String placeid=js.get("results[0].place_id");
		System.out.println( placeid+" placeid");
		  }

}


