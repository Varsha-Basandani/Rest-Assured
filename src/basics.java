import io.restassured.RestAssured;
import files.ReusebleMethods;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.equalTo;

import java.io.FileInputStream;

import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import files.resources;

import static io.restassured.RestAssured.given;

public class basics {
	Properties prop=new Properties();
	
	@BeforeTest
	public void getData() throws IOException
	{
	
		FileInputStream fis=new FileInputStream("/Users/varshabasandani/eclipse-workspace/DemoProject/src/files/env.properties");
	    prop.load(fis);
	   // prop.getProperty("HOST");
		
	}
	@Test
	public void test1()
	{
		//Base url or Host
		RestAssured.baseURI=prop.getProperty("HOST");
		Response res=given().
		   param("location","-33.8670522,151.1957362").
		   param("radius","500").
		   param("key",prop.getProperty("KEY")).log().all().
		   when().
		   get(resources.placePostData()).
		   then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
		   //body("results[0].geometry.location.lat",equalTo("-33.86882")).and().
		   body("results[0].place_id",equalTo("ChIJP3Sa8ziYEmsRUKgyFmh9AQM")).and().
		   header("Server","scaffolding on HTTPServer2").log().all().
		   extract().response();
		
		JsonPath js=ReusebleMethods.rawToJSON(res);
		int count=js.get("results.size()");
		System.out.println(count);
		
		for(int i=0;i<20;i++)
		{
			js.get("results["+i+"].name");
			System.out.println(js.get("results["+i+"].name").toString());
		}
		
		String placeid=js.get("results[0].place_id");
		System.out.println( placeid+" placeid");
		  }

}
