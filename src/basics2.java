import org.testng.annotations.Test;
import files.payload;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import static org.hamcrest.Matchers.equalTo;
import static io.restassured.RestAssured.given;

public class basics2 {
	
	@Test
	public void postData()
	{RestAssured.baseURI="https://216.10.245.66";
	given().
	queryParam("key","qaclick123").
	body(payload.getPostData()).
	when().
	post("/maps/api/place/nearbysearch/json").
	then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
	body("status",equalTo("OK"));
		
	}
	

}
