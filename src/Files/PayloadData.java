package Files;

public class PayloadData {

	public static String getPayloadData()
	{
			String b="{\r\n" + 
					"	\"username\":\"raamm\",\r\n" + 
					"	\"email\":\"ramam@gmail.com\",\r\n" + 
					"	\"password\":\"San@123\"\r\n" + 
					"}";
			return b ;
	}
	public static String Register_exisxting_username()
	{
		String user="{\r\n" + 
				"	\"username\":\"Saamm\",\r\n" + 
				"	\"email\":\"qwssam@gmail.com\",\r\n" + 
				"	\"password\":\"San@123\"\r\n" + 
				"}";
		return user;
		
	}
	public static String Register_existing_email()
	{
		String user="{\r\n" + 
				"	\"username\":\"Saliwim\",\r\n" + 
				"	\"email\":\"samm@gmail.com\",\r\n" + 
				"	\"password\":\"San@123\"\r\n" + 
				"}";
		return user;
		
	}
	
	public static String Login_Correct()
	{
		String login = "{\"username\":\"raamm\",\r\n" + 
				"	\"password\":\"San@123\"\r\n" + 
				"}";
		return login;
	}
	public static String Login_Emptyusername()
	{
		String login = "{\"username\":null,\r\n" + 
				"	\"password\":\"San@123\"\r\n" + 
				"}";
		return login;
	}
	public static String Login_Emptypswd()
	{
		String p= "{\"username\":\"raamm\",\r\n" + 
				"	\"password\":null}";
		return p;
	}
	
	public static String Login_invalidUsername() {
		// TODO Auto-generated method stub
		String s="{\"username\":\"qqqqq\",\r\n" + 
				"	\"password\":\"hiii\"\r\n" + 
				"}";
		return s;
		
	}
	public static String Login_unauthorizeduser()
	{
		String s="{\"username\":\"raamm\",\r\n" + 
				"	\"password\":\"hiii\"\r\n" + 
				"}";
		return s;
	}
	
	public static String PostBlogs() {
		// TODO Auto-generated method stub
		String p="{\"content\":\"Hi this is my second blog\"}";
		return p;
}}
