//TESTING THE BLOG POST API
package BlogTesting;

import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.equalTo;

import Files.PayloadData;
import Files.Resource;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
//case 1:posting with correct credentials
//case2: invalid user whose token is not existing
//case 3:Actually blog is being added or not
public class PostBlogsAPITest {

	Properties prop= new Properties();
	static String token_id[] = Resource.getToken_USerid();
	int posts_before=Resource.getPosts(token_id);
	static int post_id;
	
	@BeforeTest
	public void getData() throws IOException
	{
		FileInputStream fis = new FileInputStream("D:\\Selenium_Projects\\Project\\Blog-APITesting\\src\\Files\\enviorment.properties");
		prop.load(fis);
		prop.get("HOST");
	}

	
	@Test(priority=2,description="invalid User")
	public void PostingBlogInvalidUser()
	{
		given().headers("Content-Type","application/json").headers("token",token_id[0]).body(PayloadData.PostBlogs()).
		when().post(Resource.postBlogs("150")).
		then().assertThat().statusCode(204);
	}
	@Test(priority=1,description="posting blog with correct user_id and token")
	public static void PostingBlogs()
	{
		Resource.getBlogID(token_id);
	}
	
	@Test(priority=3,description="checking whether the blogs are added or not")
	public void CheckingPostingBlogs()
	{
		System.out.println("post before "+posts_before);
		int post_after=Resource.getPosts(token_id);
		System.out.println("post after are "+post_after);
	
		Assert.assertEquals(post_after, posts_before+1);
		
	}
	
}
