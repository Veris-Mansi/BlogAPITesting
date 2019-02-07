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
public class LoginAPITest {
	
	Properties prop= new Properties();
	@BeforeTest
	public void getData() throws IOException
	{
		FileInputStream fis = new FileInputStream("D:\\Selenium_Projects\\Project\\Blog-APITesting\\src\\Files\\enviorment.properties");
		prop.load(fis);
		prop.get("HOST");
	}
	//Test cases defined
	//testcase1:correct login credentials
	//testcase2: empty username
	//testcase3: empty password
	//testcase4: invalid username
	//testcase5: valid username and password don't match
	
	
	@Test(priority=1,description="login tetsing with correct credentials")
	public static void LoginCorrectCredentials()
	{
		String array[]=Resource.getToken_USerid();
		//System.out.println("token "+array[0]);
		//System.out.println("id "+array[1]);
	}
	@Test(priority=2,description="login tetsing with empty username")
	public void LoginEmptyUSername()
	{
		given().headers("Content-Type","application/json").body(PayloadData.Login_Emptyusername()).
		when().post(Resource.loginResource()).
		then().assertThat().statusCode(400).and().contentType(ContentType.JSON).and().body("message", equalTo("Bad Request"));
	}
	@Test(priority=4,description="login tetsing with empty password")
	public void LoginEmptyPAssword()
	{
		given().headers("Content-Type","application/json").body(PayloadData.Login_Emptypswd()).
		when().post(Resource.loginResource()).
		then().assertThat().statusCode(400).and().contentType(ContentType.JSON).and().body("message", equalTo("Bad Request"));
		
	}
	@Test(priority=5,description="login tetsing with invalid username")
	public void Logininvalidusername()
	{
	    given().headers("Content-Type","application/json").body(PayloadData.Login_invalidUsername()).
		when().post(Resource.loginResource()).
		then().assertThat().statusCode(404).and().contentType(ContentType.JSON).and().body("message", equalTo("No account with this username"));
		
	}
	@Test(priority=4,description="login tetsing with unuthorized access")
	public void Login_unuthorized()
	{
	    given().headers("Content-Type","application/json").body(PayloadData.Login_unauthorizeduser()).
		when().post(Resource.loginResource()).
		then().assertThat().statusCode(401).and().contentType(ContentType.JSON).and().body("message", equalTo("Unauthorized Access"));
		
	}

}
