package Files;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Resource {
	public static String registerresource()
	{
		String s="/api/v2/register";
		return s;
	}
	public static String loginResource()
	{
		String login="api/v2/login";
		return login;
		
	}
	public static String postBlogs(String id)
	{
		String s="http://127.0.0.1:5000/api/v2/user/"+id+"/blogs";
		System.out.println(s);
		return s;
	}
	
	public static String getBlogs(String id)
	{
		String s="api/v2/user/"+id+"/blogs";
		return s;
	}
	public static int getPosts()
	{
		 String token_id[] = Resource.getToken_USerid();
		
		RestAssured.baseURI="http://127.0.0.1:5000";
		
		Response res = given().headers("Content-Type","application/json").headers("token",token_id[0]).
		when().get(Resource.getBlogs(token_id[1])).
		then().assertThat().statusCode(200).extract().response();
	
		String response = res.asString();
		JsonPath path = new JsonPath(response);
		int total_posts=path.get("total_posts");
		return total_posts;
	}
	public static String getSessionKey()
	{
		String s="{ \"username\": \"mansisahu1480\", \"password\": \"Mansi@123\" }";
		RestAssured.baseURI="http://localhost:8080";
		Response res =given().headers("Content-Type","application/json").body(s).
		when().post("rest/auth/1/session").
		then().assertThat().statusCode(200).extract().response();
		String REsponsE = res.asString();
		JsonPath js=new JsonPath(REsponsE);
		String seesoion_id= js.get("session.value");
		System.out.println(seesoion_id);
		return seesoion_id;
	}
	
	public static String[] getToken_USerid()
	{

		RestAssured.baseURI="http://127.0.0.1:5000";
		Response resp=given().headers("Content-Type","application/json").body(PayloadData.Login_Correct()).
		when().post(Resource.loginResource()).
		then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().body("message", equalTo("login succesfull")).
		extract().response();
		
		String response=resp.asString();
		JsonPath js=new JsonPath(response);
		int id = js.get("id");
		String token=js.get("token");
		System.out.println(id);
		System.out.println(token);
		String data[]= {token,String.valueOf(id)};
		
		return data;
		
	}
	}
