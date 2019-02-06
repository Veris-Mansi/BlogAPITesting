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
public class JIRACreateIssue {

	@Test
	public void JIRAAPI()
	{
		String s= "{\r\n" + 
				"    \"fields\": {\r\n" + 
				"       \"project\":\r\n" + 
				"       {\r\n" + 
				"          \"key\": \"RES\"\r\n" + 
				"       },\r\n" + 
				"       \"summary\": \"Issue 5 \",\r\n" + 
				"       \"description\": \"Creating my first BUG\",\r\n" + 
				"       \"issuetype\": {\r\n" + 
				"          \"name\": \"Bug\"\r\n" + 
				"       }\r\n" + 
				"   }\r\n" + 
				"}";
		RestAssured.baseURI="http://localhost:8080";
		Response res = given().headers("Content-Type","application/json").
		headers("Cookie","JSESSIONID=272FEF4FC96AF640588EAEC877A8816A").
		body(s).
		when().post("/rest/api/2/issue").then().assertThat().statusCode(201).
		extract().response();
		String response = res.asString();
		JsonPath js= new JsonPath(response);
		String issue_id = js.get("id");
		System.out.println(issue_id);
	}
}