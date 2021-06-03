package TestCase;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import Utils.ReusableCode;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class TestCase2Negative1 {
	public static SoftAssert sa = new SoftAssert();

	@BeforeMethod
	public static void baeUri() {
		RestAssured.baseURI = "https://us-central1-qa01-tekarch-accmanager.cloudfunctions.net";
	}
	@BeforeMethod
	public void CreateReport(Method sTestMethod) {
		ReusableCode.test = ReusableCode.extent.createTest(sTestMethod.getName());
	}
	@BeforeSuite
	public static void beforeClass() throws IOException, ParseException {
		ReusableCode.jsonSetup();
	}

	@BeforeTest
	public static void startReport() {
		ReusableCode.initialiseReport();
	}


	@AfterTest
	public static void publishReport() {
		ReusableCode.extent.flush();
	}

	//Helper methods for correct login
	public static int loginAPInegative(String url, String bodyJson1) throws IOException {
		RestAssured.baseURI=url;
		//String bodyJson1="loginwrong";
		ReusableCode.login(ReusableCode.readFromPropertiesFile("login"), ReusableCode.readFromPropertiesFile("contentType"),bodyJson1);
		//System.out.println(ReusableCode.statusCode);
		return ReusableCode.statusCode;
	}

	


	public static Response getAPI(String url) throws IOException {
		Map<String,String>newMap=new HashMap();
		newMap.put("token",ReusableCode.token);
		//System.out.println("ReusableCode.token="+ReusableCode.token);
		//System.out.println("url="+url);

		RestAssured.baseURI=url;
		Response res=RestAssured.given().contentType(ReusableCode.readFromPropertiesFile("contentType")).headers(newMap).get();
		//System.out.println("res="+res.prettyPrint());
		//System.out.println(res.jsonPath().getString("accountno").toString());
		//System.out.println("Get-Data"+res.getStatusCode()+res.statusLine());
		return res;
	}

	public static Response deleteAPI(String url,Object data) throws IOException {
		Map<String,String>newMap=new HashMap();
		newMap.put("token",ReusableCode.token);
		RestAssured.baseURI=url;
		Response res=RestAssured.given().contentType(ReusableCode.readFromPropertiesFile("contentType")).headers(newMap).body(data).delete();
		return res;
	}

	public static Response postAPI(String url, Object data) throws IOException {
		Map<String,String>newMap=new HashMap();
		newMap.put("token",ReusableCode.token);
		//System.out.println("ReusableCode.token="+ReusableCode.token);
		//System.out.println("url="+url);

		RestAssured.baseURI=url;

		Response res=RestAssured.given().contentType(ReusableCode.readFromPropertiesFile("contentType")).headers(newMap).body(data).post();
		return res;
	}

	public static Response putAPI(String url, Object data) throws IOException {
		Map<String,String>newMap=new HashMap();
		newMap.put("token",ReusableCode.token);
		//System.out.println("ReusableCode.token="+ReusableCode.token);
		//System.out.println("url="+url);

		RestAssured.baseURI=url;

		Response res=RestAssured.given().contentType(ReusableCode.readFromPropertiesFile("contentType")).headers(newMap).body(data).put();
		return res;
	}

	public static String getIDForAccount(Response result, String accountNo) {

		String id="";
		List<Object> allObjs = result.jsonPath().getList("$");
		for (Object row: allObjs) {
			Map<String, String> map = (Map<String, String>)row;
			Object currAccountNo = map.get("accountno");

			if (currAccountNo instanceof String && currAccountNo.equals(accountNo)) {
				id=map.get("id");
			}
		}
		return id;
	}

	public static Map<String, String> getMapForAccount(Response result, String accountNo) {

		Map<String, String> ret=new HashMap<>();
		List<Object> allObjs = result.jsonPath().getList("$");
		for (Object row: allObjs) {
			Map<String, String> map = (Map<String, String>)row;
			Object currAccountNo = map.get("accountno");

			if (currAccountNo instanceof String && currAccountNo.equals(accountNo)) {
				ret=map;
			}
		}
		return ret;
	}



	//***********************TESTCASES**********************************************************************************	
	// Testcase 7- logIn with proper username and  wrong password. And response 401 should be received
	//@Test(priority=1)
	public static void tc7LoginNegative7() throws IOException {
		String url=RestAssured.baseURI+ReusableCode.readFromPropertiesFile("login");
		String bodyJson1="loginwrong";
		int statusCode = loginAPInegative(url,bodyJson1);
		System.out.println("satuscode"+statusCode);	
		if (statusCode==400||statusCode ==401) {
			ReusableCode.test.info("Application cannot be launched due to wrong credentials");

		}
		else {
			ReusableCode.test.info("Other Errors");	
			System.out.println("failed as othercode received");	
		}
		Assert.assertEquals(statusCode, 401);
		//sa.assertAll();
	}

	// Testcase 8- logIn with proper username and  blank password. And response 401 should be received
	//@Test(priority=2)
	public static void tc8LoginNegative2() throws IOException {
		String url=RestAssured.baseURI+ReusableCode.readFromPropertiesFile("login");
		String bodyJson1="loginblankpwd";
		int statusCode = loginAPInegative(url,bodyJson1);
		System.out.println("satuscode"+statusCode);	
		if (statusCode==400||statusCode ==401) {
			ReusableCode.test.info("Application cannot be launched due to wrong credentials");

		}
		else {
			ReusableCode.test.info("Other Errors");	
			System.out.println("failed as othercode received");	
		}
		Assert.assertEquals(statusCode, 401);
		//sa.assertAll();
	}

	// Testcase 9- logIn with wrong username and  wrong password. And response 401 should be received
	//@Test(priority=2)
	public static void tc9Login3WrongUserIDPwd() throws IOException {
		String url=RestAssured.baseURI+ReusableCode.readFromPropertiesFile("login");
		String bodyJson1="loginwronguseridpwd";
		int statusCode = loginAPInegative(url,bodyJson1);
		System.out.println("satuscode"+statusCode);	
		if (statusCode==400||statusCode ==401) {
			ReusableCode.test.info("Application cannot be launched due to wrong credentials");

		}
		else {
			ReusableCode.test.info("Other Errors");	
			System.out.println("failed as othercode received");	
		}
		Assert.assertEquals(statusCode, 401);
		//sa.assertAll();
	}	
	// test case 10- add without token
	
	
		@Test(priority=3)
		public static void tc10AddDataNegative() throws IOException {

			// login validation
			String baseURI = RestAssured.baseURI;
			String url=baseURI+ReusableCode.readFromPropertiesFile("login");
			String bodyJson1="login";
			int statusCode = loginAPInegative(url,bodyJson1);
			sa.assertEquals(statusCode, 201);
			if (statusCode==201||statusCode ==200) {
				ReusableCode.test.info("App launched");
			} else {
				ReusableCode.test.info("Appnot launched");	
			}
			
			// GET validation
			url=baseURI+ReusableCode.readFromPropertiesFile("getdata");
			Response result=getAPI(url);
			statusCode = result.getStatusCode();

			sa.assertEquals(statusCode, 201);
			if ((statusCode==201||statusCode ==200)&& result.toString() != "") {
				ReusableCode.test.info("Data got");
			} else {
				ReusableCode.test.info("Data not displayed");	
			}
			
			
			//Adddata validation sending wrong endpoint
			url=baseURI+ReusableCode.readFromPropertiesFile("adddata");
			
			System.out.println("data="+ReusableCode.getValueFromJson("data"));
			JSONArray dataArr = (JSONArray)(ReusableCode.getValueFromJson("data"));
			JSONObject data = (JSONObject) dataArr.get(0);

			Response res = postAPI(url, data);
			int retCode = res.getStatusCode();
			System.out.println("responsePOST="+res.asPrettyString());
			System.out.println("responsecode="+retCode);
			Assert.assertEquals(retCode, 401);
			if (retCode==401||retCode ==400) {
				ReusableCode.test.info("Data added");
			} else {
				ReusableCode.test.info("Data not added");	
			}
			Assert.assertEquals(retCode, 401);
		}

}
