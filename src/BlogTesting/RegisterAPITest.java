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
public class RegisterAPITest {

	static Properties prop= new Properties();
	@BeforeTest
	public void getData() throws IOException
	{
		FileInputStream fis = new FileInputStream("D:\\Selenium_Projects\\Project\\Blog-APITesting\\src\\Files\\enviorment.properties");
		prop.load(fis);
		prop.get("HOST");
	}
	@Test(priority=1,description="Valid credentials register testing")
	public static void  RegisterValidCredentials()
	{
		RestAssured.baseURI="http://127.0.0.1:5000";
		given().
		headers("Content-Type","application/json").body(PayloadData.getPayloadData()).
		when().
		post(Resource.registerresource()).
		then().assertThat().statusCode(202).and().contentType(ContentType.JSON).and().body("message",equalTo("user created"));
	}
	@Test(priority=2,description="register testing with existing username")
	public void RegisterExistingUsername()
	{
		
		given().
		headers("Content-Type","application/json").body(PayloadData.Register_exisxting_username()).
		when().
		post(Resource.registerresource()).
		then().assertThat().statusCode(409).and().contentType(ContentType.JSON).and().body("message",equalTo("Username or Email already exist"));
	}
	@Test(priority=3,description="register testing with existing email")
	public void RegisterExistingEmail()
	{
		RestAssured.baseURI=prop.getProperty("HOST");
		given().
		headers("Content-Type","application/json").body(PayloadData.Register_existing_email()).
		when().
		post(Resource.registerresource()).
		then().assertThat().statusCode(409).and().contentType(ContentType.JSON).and().body("message",equalTo("Username or Email already exist"));
	}
	
}
