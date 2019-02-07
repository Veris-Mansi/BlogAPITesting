package Inventorytesting;

import org.testng.annotations.Test;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import Files.PayloadData;
import Files.Resource;
import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.equalTo;
import io.restassured.RestAssured;

public class GetRequestTest {

	@Test
	public void Getting()
	{
		RestAssured.baseURI="https://inventory.veris.in/";
		Response res = given().headers("token","da39a1fe-cb59-4ae9-a653-2a616452981f").
		when().get("api/v1/products/").
		then().statusCode(200).extract().response();
		System.out.println("response "+res.asString());
	}
}
