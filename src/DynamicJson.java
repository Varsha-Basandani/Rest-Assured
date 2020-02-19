import files.ReusebleMethods;
import files.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
public class DynamicJson {
	
	@Test(dataProvider="BooksData")
	public void addBook()
	{
		//Base url or Host
		RestAssured.baseURI="http://216.10.245.166";
		Response res=given().
				header("Content-Type","application/json").
		   body(payload.Addbook("bibuiwc","234")).
		   when().
		   post("Library/Addbook.php").
		   then().assertThat().statusCode(200).
		   extract().response();
		
		JsonPath js=ReusebleMethods.rawToJSON(res);
		String id=js.get("ID");
		System.out.println(id);
		
	}
	
	@DataProvider(name="BooksData")
	public Object[][] getData()
	{return new Object[][] {{"asdede","334"},{"ssdxdc","234"},{"ssqasds","12233"}};
		
	}

}
