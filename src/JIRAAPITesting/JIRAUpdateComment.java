package JIRAAPITesting;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import Files.*;
import static org.hamcrest.Matchers.equalTo;
public class JIRAUpdateComment {

	@Test
	public void JIRAAPI()
	{
		String s= "{\"body\":\"this is for updating comment\"}";
		RestAssured.baseURI="http://localhost:8080";
		Response res = given().headers("Content-Type","application/json").
		headers("Cookie","JSESSIONID=272FEF4FC96AF640588EAEC877A8816A").
		body(s).
		when().put("/rest/api/2/issue/10104/comment/10103").then().assertThat().statusCode(200).
		extract().response();
		String response = res.asString();
		JsonPath js= new JsonPath(response);
		String updatecomment_id = js.get("id");
		System.out.println( updatecomment_id);//10103
	}
}