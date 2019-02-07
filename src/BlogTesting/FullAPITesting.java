package BlogTesting;
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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class FullAPITesting {

	String token_id[];
	String blog_id[];
	@Test(description="registering user")
	public void registering()
	{
		RegisterAPITest.RegisterValidCredentials();
	}
	@Test(dependsOnMethods="registering",description="logging the registered user")
	public void login()
	{
		token_id=Resource.getToken_USerid();
		System.out.println(token_id[0]+" "+token_id[1]);
	}
	@Test(dependsOnMethods="login",description="getting all blogs")
		public void gettingAllBlogs()
		{
			Resource.getPosts(token_id);
		}
	
	@Test(dependsOnMethods="gettingAllBlogs",description="posting blogs correctly")
	public void postBlogs()
	{
		blog_id=Resource.getBlogID(token_id);
		System.out.println(blog_id[0]+" "+blog_id[1]);
	}
	@Test(dependsOnMethods="postBlogs",description="getting a blog with post id")
	public void gettingdefinedBlogs()
	{
		Resource.getParticularblogAthor(token_id, blog_id[0]);
	}
	@Test(dependsOnMethods="gettingdefinedBlogs",description="updating blogs correctly")
	public void updateBlogs()
	{
			given().headers("Content-Type","application/json").headers("token",token_id[0]).body(PayloadData.UpdateBlogs()).
			when().put(Resource.getParticularBlogs(token_id[1], blog_id[0])).
			then().assertThat().statusCode(200).and().body("message", equalTo("Post updated succesfully"));

	}
	@Test(dependsOnMethods="updateBlogs",description="updating blogs correctly")
	public void deleteBlogs()
	{
		given().headers("Content-Type","application/json").headers("token",token_id[0]).
		when().delete(Resource.getParticularBlogs(token_id[1], blog_id[0])).
		then().assertThat().statusCode(200).body("message", equalTo("Post deleted succesfully"));

	}
}
