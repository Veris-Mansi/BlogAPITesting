//getting all the blogs and a particular blog
//Testcases
//1:getting all blogs of a user
//getting blogs of invalid user
//getting details of a particular blog of user
//trying to get details of invalid blog
//postupdated succesfuly
package BlogTesting;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import Files.Resource;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.equalTo;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
public class GetAllBlogsAPITest {

	static String token_id[] = Resource.getToken_USerid();
	static String blog_data[]=Resource.getBlogID(token_id);
	
	@Test(priority=1,description="getting all blog posts")
	public void getBlogs()
	{
		Resource.getBlogs(token_id[1]);
	}
	@Test(priority=2,description="getting invalid user")
	public void InvalidUserId()
	{
		given().headers("Content-Type","application/json").headers("token",token_id[0]).
		when().get(Resource.getBlogs("156")).
		then().assertThat().statusCode(204);
	}
	@Test(priority=3,description="getting a particular blog")
	public void getParticularBlog()
	{
		Resource.getParticularblogAthor(token_id, blog_data[0]);
	}
	@Test(priority=4,description="getting a particular blog with invalid blog_id")
	public void InvalidPostId()
	{
		given().headers("Content-Type","application/json").headers("token",token_id[0]).
		when().get(Resource.getParticularBlogs(token_id[1], "45")).
		then().assertThat().statusCode(204);
	}
}
