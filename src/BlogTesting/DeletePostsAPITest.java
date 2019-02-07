package BlogTesting;
import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.equalTo;

import Files.PayloadData;
import Files.Resource;
import io.restassured.http.ContentType;
public class DeletePostsAPITest {

	static String token_id[] = Resource.getToken_USerid();	//login
	static String blog_data[]=Resource.getBlogID(token_id);	//postblogs-{postid,username}
	String blog_author=Resource.getParticularblogAthor(token_id, blog_data[0]);//will give you blog author of the given post
	int posts_before=Resource.getPosts(token_id);
	
	@Test(priority=1,description="checking invalid blog_id")
	
	public void InvalidPostId()
	{
		given().headers("Content-Type","application/json").headers("token",token_id[0]).
		when().get(Resource.getParticularBlogs(token_id[1], "45")).
		then().assertThat().statusCode(204);
	}
	@Test(priority=2,description="checking invalid user_id")
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
	@Test(priority=4,description="deleting the post")
	
		public void deletePosts()
		{
		given().headers("Content-Type","application/json").headers("token",token_id[0]).
		when().delete(Resource.getParticularBlogs(token_id[1], blog_data[0])).
		then().assertThat().statusCode(200).body("message", equalTo("Post deleted succesfully"));
		}
	@Test(priority=5,description="whether the posts are deleted or not")
	public void checkIfDeleted()
	{
		System.out.println("post before "+posts_before);
		int post_after=Resource.getPosts(token_id);
		System.out.println("post after are "+post_after);
	
		Assert.assertEquals(post_after, posts_before-1);
	}
	}
	

