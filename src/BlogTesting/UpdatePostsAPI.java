//updating the psts of a user
//Testcases
//1 : invalid blog ud
//2: invalid user id
//3: comparing the user and blog author is same or not
package BlogTesting;
import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.equalTo;

import Files.PayloadData;
import Files.Resource;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
public class UpdatePostsAPI {
	
	static String token_id[] = Resource.getToken_USerid();
	static String blog_data[]=Resource.getBlogID(token_id);
	String blog_author=Resource.getParticularblogAthor(token_id, blog_data[0]);
	@Test(priority=1,description="invalid blog_id")
	public void InvalidPostId()
	{
		given().headers("Content-Type","application/json").headers("token",token_id[0]).
		when().get(Resource.getParticularBlogs(token_id[1], "45")).
		then().assertThat().statusCode(204);
	}
	@Test(priority=2,description="invalid user_id")
	public void InvalidUserId()
	{
		given().headers("Content-Type","application/json").headers("token",token_id[0]).
		when().get(Resource.getBlogs("156")).
		then().assertThat().statusCode(204);
	}
	@Test(priority=3,description="matching that whetherthe user is author of the given post or not")
	public void MatchingBlogAuthor()
	{
		String user_author=blog_data[1];
		Assert.assertEquals(user_author, blog_author);

	}
	

	@Test(priority=4,description="updating")
	public void UpdatingPosts()
	{
		given().headers("Content-Type","application/json").headers("token",token_id[0]).body(PayloadData.UpdateBlogs()).
		when().put(Resource.getParticularBlogs(token_id[1], blog_data[0])).
		then().assertThat().statusCode(200).and().body("message", equalTo("Post updated succesfully"));

	}
	
}
