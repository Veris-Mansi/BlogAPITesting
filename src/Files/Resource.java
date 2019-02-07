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
		String s="api/v2/user/"+id+"/blogs";
		System.out.println(s);
		return s;
	}
	
	public static String getBlogs(String id)
	{
		String s="api/v2/user/"+id+"/blogs";
		return s;
	}
	
	public static String getParticularBlogs(String id,String blog_id)
	{
		String s="api/v2/user/"+id+"/blogs/"+blog_id+"";
		System.out.println(s);
		return s;
	}
	public static int getPosts(String token_id[])
	{
		 //String token_id[] = Resource.getToken_USerid();
		
		RestAssured.baseURI="http://127.0.0.1:5000";
		
		Response res = given().headers("Content-Type","application/json").headers("token",token_id[0]).
		when().get(Resource.getBlogs(token_id[1])).
		then().assertThat().statusCode(200).extract().response();
	
		String response = res.asString();
		JsonPath path = new JsonPath(response);
		System.out.println("Response "+response);
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
	public static String getParticularblogAthor(String token_id[],String blogid)
		{
			Response res = given().headers("Content-Type","application/json").headers("token",token_id[0]).
					when().get(Resource.getParticularBlogs(token_id[1],blogid)).
					then().assertThat().statusCode(200).contentType(ContentType.JSON).extract().response();
					System.out.println("response is "+res.asString());
					String response=res.asString();
					JsonPath path = new JsonPath(response);
					String blog_author=path.get("Author");
					System.out.println("Blog author "+blog_author);
					return blog_author;
		}
		public static String[] getBlogID(String token_id[])
		{
					Response res =given().headers("Content-Type","application/json").headers("token",token_id[0]).body(PayloadData.PostBlogs()).
					when().post(Resource.postBlogs(token_id[1])).
					then().assertThat().statusCode(201).contentType(ContentType.JSON).and().
					extract().response();
					String response = res.asString();
					JsonPath path = new JsonPath(response);
				   int  post_id=path.get("post_id");
				    String msg=path.get("message");
				    String array[]=msg.split("\\s");
				    String user_author=array[array.length-1];
				    System.out.println("user author "+user_author);
					System.out.println("post id "+post_id);
					String data[]= {String.valueOf(post_id),user_author};
					return data;
		}
	}
