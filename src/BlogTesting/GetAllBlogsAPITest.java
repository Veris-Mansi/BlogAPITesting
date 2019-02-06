package BlogTesting;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import Files.Resource;

public class GetAllBlogsAPITest {

	static String token_id[] = Resource.getToken_USerid();
	
	@Test(priority=2,description="getting blog posts")
	public void getBlogs()
	{
		Resource.getPosts();
	}
	@Test(priority=1,description="getting blog posts")
	public void getInvalidUser()
	{
		given().headers("Content-Type","application/json").headers("token",token_id[0]).
		when().get(Resource.getBlogs("156")).
		then().assertThat().statusCode(204);
	}
}
