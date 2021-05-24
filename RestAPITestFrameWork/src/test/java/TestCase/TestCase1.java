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

public class TestCase1 {
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
	public static int loginAPI(String url) throws IOException {
		RestAssured.baseURI=url;
		ReusableCode.login(ReusableCode.readFromPropertiesFile("login"), ReusableCode.readFromPropertiesFile("contentType"));
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
	
	//Helper methods for wrong login (wrong password)
	public static int wrongLoginAPI(String url) throws IOException {
		RestAssured.baseURI=url;
		//CreateTest("TC 001 Logging in verification.");
		ReusableCode.loginWrong(ReusableCode.readFromPropertiesFile("login"), ReusableCode.readFromPropertiesFile("contentType"));
		System.out.println(ReusableCode.statusCode);
		return ReusableCode.statusCode;
	}
	
	
//***********************TESTCASES**********************************************************************************	
	// Testcase 1- logIn with proper username and password. And response 201 should be received
	@Test(priority=1)
	public static void testLogin1() throws IOException {
		String url=RestAssured.baseURI+ReusableCode.readFromPropertiesFile("login");
		int statusCode = loginAPI(url);
		sa.assertEquals(statusCode, 201);
		if (statusCode==201||statusCode ==200) {
		ReusableCode.test.info("Application is launched");}
		else {
			ReusableCode.test.info("Application is launch failed");	
		}
		sa.assertAll();
	}
	
	// Testcase 1- logIn with proper username and password.Application to be launched and data got from server > the status code 200 received
	@Test(priority=2)
	public static void testLoginWithGet2() throws IOException {
		
		// login validation
		String baseURI = RestAssured.baseURI;
		String url=baseURI+ReusableCode.readFromPropertiesFile("login");
		int statusCode = loginAPI(url);
		sa.assertEquals(statusCode, 201);
		if (statusCode==201||statusCode ==200) {
			ReusableCode.test.info("App launched");
		} else {
			ReusableCode.test.info("Appnot launched");	
		}
		
		// GET validation
		url=baseURI+ReusableCode.readFromPropertiesFile("getdata");
		Response result = getAPI(url);
		statusCode = result.getStatusCode();
		
		System.out.println("result="+result.asPrettyString());
		
		sa.assertEquals(statusCode, 200);
		if ((statusCode==201||statusCode ==200) && result.toString() != "") {
			ReusableCode.test.info("Data got");
		} else {
			ReusableCode.test.info("Data not displayed");
		}
	
		sa.assertAll();
	}
		
	//test Case to Add data
	@Test(priority=3)
	public static void testAddData3() throws IOException {

		// login validation
		String baseURI = RestAssured.baseURI;
		String url=baseURI+ReusableCode.readFromPropertiesFile("login");
		int statusCode = loginAPI(url);
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
		
		
		//Adddata validation
		url=baseURI+ReusableCode.readFromPropertiesFile("adddata");
		
		System.out.println("data="+ReusableCode.getValueFromJson("data"));
		JSONArray dataArr = (JSONArray)(ReusableCode.getValueFromJson("data"));
		JSONObject data = (JSONObject) dataArr.get(0);

		Response res = postAPI(url, data);
		int retCode = res.getStatusCode();
		System.out.println("responsePOST="+res.asPrettyString());
		
		sa.assertEquals(retCode, 201);
		if (retCode==201||retCode ==200) {
			ReusableCode.test.info("Data added");
		} else {
			ReusableCode.test.info("Data not added");	
		}
	}
		
	//TestCase to Update/edit data
	@Test(priority=4)
	public static void testUpdateAPI4() throws IOException {

		// login validation
		String baseURI = RestAssured.baseURI;
		String url=baseURI+ReusableCode.readFromPropertiesFile("login");
		int statusCode = loginAPI(url);
		sa.assertEquals(statusCode, 201);
		if (statusCode==201||statusCode ==200) {
			ReusableCode.test.info("App launched");
		} else {
			ReusableCode.test.info("Appnot launched");	
		}

		JSONArray dataArr = (JSONArray)(ReusableCode.getValueFromJson("data"));
		JSONObject data = (JSONObject) dataArr.get(0);
		
		String accountNo = (String)(data.get("accountno"));

		// GET validation
		url=baseURI+ReusableCode.readFromPropertiesFile("getdata");
		Response result = getAPI(url);
		statusCode = result.getStatusCode();

		sa.assertEquals(statusCode, 201);
		if ((statusCode==201||statusCode ==200) && result.toString() != "") {
			ReusableCode.test.info("Data got");
		} else {
			ReusableCode.test.info("Data not displayed");	
		}
		String id=getIDForAccount(result, accountNo);
				
		System.out.println("id found="+id);

		data.replace("salary", "6000");
		data.put("userid", ReusableCode.userid);
		data.put("id", id);

		// update validation
		url=baseURI+ReusableCode.readFromPropertiesFile("updatedata");
		Response res = putAPI(url, data);
		int retCode = res.statusCode();
		
		System.out.println("Responsesddd="+res.asPrettyString());
		sa.assertEquals(retCode, 201);
		if (retCode==201||retCode ==200) {
			ReusableCode.test.info("Data added");
		} else {
			ReusableCode.test.info("Data not added");	
		}

		
		if ((statusCode==201||statusCode ==200)&& result.toString()!="") {
			ReusableCode.test.info("Data got");
		} else {
			ReusableCode.test.info("Data not displayed");	
		}

		// check if sal is updated
		Map<String, String> map=getMapForAccount(result,accountNo);
		String veriFySal=map.get("salary");
		System.out.println("salary" +map.get("salary"));
		if (veriFySal.equals("6000") ) {
			
			ReusableCode.test.info("sal updated ,Upadte pass.");
		}else{
		
		
			ReusableCode.test.info("sal not updated.");
		}
	}	
	//test Case to delete data
	@Test(priority=5)
	public static void testDeleteAPI4() throws IOException {
		// login validation
		String baseURI = RestAssured.baseURI;
		String url=baseURI+ReusableCode.readFromPropertiesFile("login");
		int statusCode = loginAPI(url);
		sa.assertEquals(statusCode, 201);
		if (statusCode==201||statusCode ==200) {
			ReusableCode.test.info("App launched");
		} else {	
			ReusableCode.test.info("Appnot launched");	
		}

		JSONArray dataArr = (JSONArray)(ReusableCode.getValueFromJson("data"));
		JSONObject data = (JSONObject) dataArr.get(0);
		
		String accountNo = (String)(data.get("accountno"));

		// GET validation
		url=baseURI+ReusableCode.readFromPropertiesFile("getdata");
		Response result = getAPI(url);
		statusCode = result.getStatusCode();

		sa.assertEquals(statusCode, 201);
		if ((statusCode==201||statusCode ==200) && result.toString() != "") {
			ReusableCode.test.info("Data got");
		} else {
			ReusableCode.test.info("Data not displayed");	
		}
		String id=getIDForAccount(result, accountNo);
		//deleting data with user id and Id
		
		JSONObject data1 =new JSONObject();
		data1.put("userid", ReusableCode.userid);
		data1.put("id", id);		
		System.out.println("data"+ data1);
		Response result1 =deleteAPI(url, data1);
		System.out.println("result"+ result1);
	}
	
	//test Case to logout data
	@Test(priority=6)
	public static void testLogOutAPI5() throws IOException {
		
		String baseURI = RestAssured.baseURI;
		String url=baseURI+ReusableCode.readFromPropertiesFile("login");
		int statusCode = loginAPI(url);
		sa.assertEquals(statusCode, 201);
		if (statusCode==201||statusCode ==200) {
			ReusableCode.test.info("App launched");
		} else {
			ReusableCode.test.info("Appnot launched");	
		}
	
		JSONArray dataArr = (JSONArray)(ReusableCode.getValueFromJson("data"));
		JSONObject data = (JSONObject) dataArr.get(0);
		
		String accountNo = (String)(data.get("accountno"));
	
		// GET validation
		url=baseURI+ReusableCode.readFromPropertiesFile("getdata");
		Response result = getAPI(url);
		statusCode = result.getStatusCode();
	
		sa.assertEquals(statusCode, 201);
		if ((statusCode==201||statusCode ==200) && result.toString() != "") {
			ReusableCode.test.info("Data got");
		} else {
			ReusableCode.test.info("Data not displayed");	
		}

		JSONObject data1 = new JSONObject();
		data1.put("token",null);
		url=baseURI+ReusableCode.readFromPropertiesFile("logout");
		Response res = postAPI(url, data1);
		int retCode = res.getStatusCode();
		System.out.println("responsePOST="+res.asPrettyString());
		
		sa.assertEquals(retCode, 201);
		if (retCode==201||retCode ==200) {
			ReusableCode.test.info("Succeccfully logged out");
		} else {
			ReusableCode.test.info("Logoutfailed");	
		}
	}
}
